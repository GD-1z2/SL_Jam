package fr.caemur.icm.tabs;

import fr.caemur.icm.init.ModBlocks;
import fr.caemur.icm.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MachinesTab extends CreativeTabs {

	public MachinesTab(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModBlocks.solidifier);
	}
//	
//	@Override
//	public ItemStack getIconItemStack() {
//		// TODO Auto-generated method stub
//		return super.getIconItemStack();
//	}
}
