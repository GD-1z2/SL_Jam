package fr.caemur.icm.init;

import java.util.List;

import com.google.common.collect.Lists;

import fr.caemur.icm.armors.IcmArmor;
import fr.caemur.icm.blocks.solidifier.Solidifier;
import fr.caemur.icm.items.IcmItem;
import fr.caemur.icm.items.ItemBlockMetadata;
import fr.caemur.icm.tools.IcmAxe;
import fr.caemur.icm.tools.IcmHoe;
import fr.caemur.icm.tools.IcmPickaxe;
import fr.caemur.icm.tools.IcmShovel;
import fr.caemur.icm.tools.IcmSword;
import fr.caemur.icm.utils.References;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModItems {
	
	public static final ModItems INSTANCE = new ModItems();
	
	public static Item lead_ingot;
	public static Item uranium_ingot;
	
	public static ArmorMaterial lead_armor_mat;
	
	public static ItemArmor lead_helmet;
	public static ItemArmor lead_chestplate;
	public static ItemArmor lead_leggings;
	public static ItemArmor lead_boots;

	public static ToolMaterial lead_tool_mat;
	public static ToolMaterial uranium_tool_mat;
	
	public static ItemSword lead_sword;
	public static ItemPickaxe lead_pickaxe;
	public static ItemAxe lead_axe;
	public static ItemSpade lead_shovel;
	public static ItemHoe lead_hoe;
	
	public static ItemSword uranium_sword;
	public static ItemPickaxe uranium_pickaxe;
	public static ItemAxe uranium_axe;
	public static ItemSpade uranium_shovel;
	public static ItemHoe uranium_hoe;
	
	public static Item iron_stick;
	
//	public static final Item SOLIDIFIER_ITEM = new ItemBlockMetadata(ModBlocks.solidifier, new String[] {"solidifier_zero", "solidifier_one", "solidifier_two", "solidifier_three"}).setRegistryName(ModBlocks.solidifier.getRegistryName());
	
	private List<Item> items;
	
	public void init()
	{
		items = Lists.newArrayList();
		
		lead_ingot = new IcmItem("lead_ingot");
		uranium_ingot = new IcmItem("uranium_ingot");
		
		lead_armor_mat = EnumHelper.addArmorMaterial("lead_armor_mat", References.MODID + ":lead_armor", 100, new int[] {2, 4, 3, 1}, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1);
		
		lead_helmet = new IcmArmor("lead_helmet", lead_armor_mat, 2, EntityEquipmentSlot.HEAD);
		lead_chestplate = new IcmArmor("lead_chestplate", lead_armor_mat, 2, EntityEquipmentSlot.CHEST);
		lead_leggings = new IcmArmor("lead_leggings", lead_armor_mat, 2, EntityEquipmentSlot.LEGS);
		lead_boots = new IcmArmor("lead_boots", lead_armor_mat, 2, EntityEquipmentSlot.FEET);
		
		lead_tool_mat = EnumHelper.addToolMaterial("lead_tool_mat", 2, 2000, 7.0f, 2.0f, 15);
		uranium_tool_mat = EnumHelper.addToolMaterial("uranium_tool_mat", 3, 2500, 8.0f, 3.5f, 12);
		
		lead_sword = new IcmSword("lead_sword", lead_tool_mat);
		lead_pickaxe = new IcmPickaxe("lead_pickaxe", lead_tool_mat);
		lead_axe = new IcmAxe("lead_axe", lead_tool_mat, 7.0f, 0.8f);
		lead_shovel = new IcmShovel("lead_shovel", lead_tool_mat);
		lead_hoe = new IcmHoe("lead_hoe", lead_tool_mat);
		
		uranium_sword = new IcmSword("uranium_sword", uranium_tool_mat);
		uranium_pickaxe = new IcmPickaxe("uranium_pickaxe", uranium_tool_mat);
		uranium_axe = new IcmAxe("uranium_axe", uranium_tool_mat, 7.0f, 0.8f);
		uranium_shovel = new IcmShovel("uranium_shovel", uranium_tool_mat);
		uranium_hoe = new IcmHoe("uranium_hoe", uranium_tool_mat);
		
		iron_stick = new IcmItem("iron_stick");
		
		// 2f5a31 + 336a35
	}
	
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event)
	{
		for (Item item : items) {
			registerModel(item, 0);
		}
//		
//		for (int i = 0; i < Solidifier.EnumType.values().length; i++) {
//			registerModel(SOLIDIFIER_ITEM, i);
//		}
	}
	
	public void registerModel(Item item, int meta)
	{
		System.out.println("register item model name : " + item.getUnlocalizedName());
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(References.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
	}
	
	public List<Item> getItems() {
		return items;
	}
}
