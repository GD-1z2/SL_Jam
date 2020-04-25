package fr.caemur.icm.armors;

import fr.caemur.icm.icmMain;
import fr.caemur.icm.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class IcmArmor extends ItemArmor {

	public IcmArmor(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(icmMain.modTab);
	
		ModItems.INSTANCE.getItems().add(this);
	}

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        super.onArmorTick(world, player, itemStack);

        if (player.inventory.armorInventory.get(3) != null && player.inventory.armorInventory.get(2) != null && player.inventory.armorInventory.get(1) != null && player.inventory.armorInventory.get(0) != null) {

            ItemStack helmet = player.inventory.armorInventory.get(3);
            ItemStack chestplate = player.inventory.armorInventory.get(2);
            ItemStack leggings = player.inventory.armorInventory.get(1);
            ItemStack boots = player.inventory.armorInventory.get(0);

            if (helmet.getItem() == ModItems.lead_helmet && chestplate.getItem() == ModItems.lead_chestplate && leggings.getItem() == ModItems.lead_leggings && boots.getItem() == ModItems.lead_boots) {
                player.addPotionEffect(new PotionEffect(Potion.getPotionById(2), 60));
            }
        }
    }
}
