package fr.caemur.icm.tools;

import fr.caemur.icm.icmMain;
import fr.caemur.icm.init.ModItems;
import net.minecraft.item.ItemAxe;

public class IcmAxe extends ItemAxe {

	public IcmAxe(String name, ToolMaterial material, float damage, float speed) {
		super(material, damage, speed);
		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(icmMain.modTab);
		
		ModItems.INSTANCE.getItems().add(this);	}
	
}
