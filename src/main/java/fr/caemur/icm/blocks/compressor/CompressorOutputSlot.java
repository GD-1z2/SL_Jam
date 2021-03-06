package fr.caemur.icm.blocks.compressor;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class CompressorOutputSlot extends Slot {

	private final EntityPlayer player;
	private int removeCount;
	
	public CompressorOutputSlot(EntityPlayer player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) 
	{
		super(inventoryIn, slotIndex, xPosition, yPosition);
		this.player = player;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) 
	{
		return false;
	}
	
	@Override
	public ItemStack decrStackSize(int amount) 
	{
		if(this.getHasStack())
		{
			this.removeCount += Math.min(amount, this.getStack().getCount());
		}
		return super.decrStackSize(amount);
	}
	
	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) 
	{
		this.onCrafting(stack);
		super.onTake(thePlayer, stack);
		return stack;
	}
	
	@Override
	protected void onCrafting(ItemStack stack, int amount) 
	{
		this.removeCount += amount;
		this.onCrafting(stack);
	}
	
	protected void onCrafting(ItemStack stack)
    {
        stack.onCrafting(this.player.world, this.player, this.removeCount);

        this.removeCount = 0;
    }

}
