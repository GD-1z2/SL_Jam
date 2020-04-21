package fr.caemur.icm.armors;

import fr.caemur.icm.icmMain;
import fr.caemur.icm.init.ModItems;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class IcmArmor extends ItemArmor {

	public IcmArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(icmMain.modTab);
	
		ModItems.INSTANCE.getItems().add(this);
	}

}
