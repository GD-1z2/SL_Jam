package fr.caemur.icm.tools;

import fr.caemur.icm.icmMain;
import fr.caemur.icm.init.ModItems;
import net.minecraft.item.ItemSword;

public class IcmSword extends ItemSword {

	public IcmSword(String name, ToolMaterial material) {
		super(material);
		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(icmMain.modTab);
		
		ModItems.INSTANCE.getItems().add(this);
	}

}
