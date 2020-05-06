package fr.caemur.icm.blocks.compressor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;

public class TileEntityCompressor extends TileEntityLockable implements ISidedInventory, ITickable {

	private static final int[] SLOTS_TOP = new int[] {0, 1, 2, 3};
	private static final int[] SLOTS_BOTTOM = new int[] {4};
	private NonNullList<ItemStack> itemStacks = NonNullList.<ItemStack>withSize(5, ItemStack.EMPTY);

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		this.itemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.itemStacks);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		ItemStackHelper.saveAllItems(compound, this.itemStacks);

		return compound;
	}

	@Override
	public int getSizeInventory()
	{
		return this.itemStacks.size();
	}

	@Override
	public boolean isEmpty()
	{
		for (ItemStack stack : this.itemStacks)
		{
			if (! stack.isEmpty())
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return this.itemStacks.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		return ItemStackHelper.getAndSplit(itemStacks, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		return ItemStackHelper.getAndRemove(itemStacks, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		ItemStack iStack = this.itemStacks.get(index);
		boolean flag = ! stack.isEmpty() && stack.isItemEqual(iStack) && ItemStack.areItemStackShareTagsEqual(stack, iStack);
		this.itemStacks.set(index, stack);

		if (stack.getCount() > this.getInventoryStackLimit())
		{
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == 0 && ! flag)
		{
			this.markDirty();
		}
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		if (this.world.getTileEntity(this.pos) != this)
		{
			return false;
		}
		else
		{
			return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void openInventory(EntityPlayer player)
	{

	}

	@Override
	public void closeInventory(EntityPlayer player)
	{

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		if (index == 4)
		{
			return false;
		}
		else if (stack.getItem() == Item.getItemFromBlock(Blocks.STONE))
		{
			return true;
		}
		return false;
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{

	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		this.itemStacks.clear();
	}

	@Override
	public String getName()
	{
		return "container.compressor";
	}

	@Override
	public boolean hasCustomName()
	{
		return false;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		if (side == EnumFacing.DOWN)
		{
			return SLOTS_BOTTOM;
		} else {
			return SLOTS_TOP;
		}
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
	{
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
	{
		if (direction == EnumFacing.DOWN && index == 4)
		{
			return true;
		}

		return false;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerCompressor(playerInventory, this);
	}

	@Override
	public String getGuiID()
	{
		return "icm:compressor";
	}

	@Override
	@javax.annotation.Nullable
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return super.getCapability(capability, facing);
	}

	@Override
	public void update()
	{
		ItemStack s1 = itemStacks.get(0);
		ItemStack s2 = itemStacks.get(1);
		ItemStack s3 = itemStacks.get(2);
		ItemStack s4 = itemStacks.get(3);
		ItemStack sr = itemStacks.get(4);

		ItemStack[] obsidianRecipe = {new ItemStack(Blocks.STONE), new ItemStack(Blocks.STONE), new ItemStack(Blocks.STONE), new ItemStack(Blocks.STONE)};

		int minCount = s1.getCount();
		if (s2.getCount() < minCount) minCount = s2.getCount();
		if (s3.getCount() < minCount) minCount = s3.getCount();
		if (s4.getCount() < minCount) minCount = s4.getCount();

		if (s1.getItem() == Item.getItemFromBlock(Blocks.STONE) && s2.getItem() == Item.getItemFromBlock(Blocks.STONE) && s3.getItem() == Item.getItemFromBlock(Blocks.STONE) && s4.getItem() == Item.getItemFromBlock(Blocks.STONE))
		{
			if (sr.isEmpty())
			{
				this.itemStacks.set(4, new ItemStack(Blocks.OBSIDIAN, minCount));
				s1.shrink(minCount);
				s2.shrink(minCount);
				s3.shrink(minCount);
				s4.shrink(minCount);

				world.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
			}
			else if (sr.getItem() == Item.getItemFromBlock(Blocks.OBSIDIAN))
			{
				int growCount;

				if ((sr.getCount() + minCount) > 64)
				{
					growCount = 64 - sr.getCount();
				} else
				{
					growCount = minCount;
				}

				sr.grow(growCount);
				s1.shrink(growCount);
				s2.shrink(growCount);
				s3.shrink(growCount);
				s4.shrink(growCount);

				if (growCount != 0)
				{
					world.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
				}
			}
		}
	}
}
