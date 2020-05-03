package fr.caemur.icm.blocks.compressor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCompressor extends Container {

	private final IInventory tileFurnace;
	
	public ContainerCompressor(InventoryPlayer playerInventory, IInventory compressorInventory)
	{
		this.tileFurnace = compressorInventory;
		this.addSlotToContainer(new Slot(compressorInventory, 0, 12, 18));
		this.addSlotToContainer(new Slot(compressorInventory, 1, 148, 18));
		this.addSlotToContainer(new Slot(compressorInventory, 2, 12, 53));
		this.addSlotToContainer(new Slot(compressorInventory, 3, 148, 53));
		this.addSlotToContainer(new CompressorOutputSlot(playerInventory.player, compressorInventory, 4, 80, 35));
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		
		for(int k = 0; k < 9; k++)
		{
			this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
		}
	}
	
	@Override
	public void addListener(IContainerListener listener) 
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileFurnace);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.tileFurnace.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) 
	{
//		ItemStack itemstack = ItemStack.EMPTY;
//        Slot slot = this.inventorySlots.get(index);
//
//        if (slot != null && slot.getHasStack())
//        {
//            ItemStack itemstack1 = slot.getStack();
//            itemstack = itemstack1.copy();
//
//            if (index == 4) // output
//            {
//                if (!this.mergeItemStack(itemstack1, 3, 39, true))
//                {
//                    return ItemStack.EMPTY;
//                }
//                slot.onSlotChange(itemstack1, itemstack);
//                
//            } else if (index != 1 && index != 0)
//            {
//                if (! CaemiumFurnaceRecipes.instance().getCookingResult(itemstack1).isEmpty())
//                {
//                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
//                    {
//                        return ItemStack.EMPTY;
//                    }
//                }
//                else if (TileEntityCompressor.isItemFuel(itemstack1))
//                {
//                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
//                    {
//                        return ItemStack.EMPTY;
//                    }
//                }
//                else if (index >= 3 && index < 30)
//                {
//                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
//                    {
//                        return ItemStack.EMPTY;
//                    }
//                }
//                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
//                {
//                    return ItemStack.EMPTY;
//                }
//            }
//            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
//            {
//                return ItemStack.EMPTY;
//            }
//
//            if (itemstack1.isEmpty())
//            {
//                slot.putStack(ItemStack.EMPTY);
//            }
//            else
//            {
//                slot.onSlotChanged();
//            }
//
//            if (itemstack1.getCount() == itemstack.getCount())
//            {
//                return ItemStack.EMPTY;
//            }
//
//            slot.onTake(playerIn, itemstack1);
//		
//
//        }
//
//        return itemstack;
		return ItemStack.EMPTY;
    }
}
