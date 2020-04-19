package fr.caemur.icm.items;

import fr.caemur.icm.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class IcmItem extends Item {
	
	public IcmItem(String name)
	{
		setRegistryName(name).setUnlocalizedName(name);
		
		ModItems.INSTANCE.getItems().add(this);
	}
	
	public IcmItem(String name, CreativeTabs tab)
	{
		this(name);
		setCreativeTab(tab);
	}
}
