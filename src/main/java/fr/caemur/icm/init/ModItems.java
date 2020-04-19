package fr.caemur.icm.init;

import java.util.List;

import com.google.common.collect.Lists;

import fr.caemur.icm.items.IcmItem;
import fr.caemur.icm.utils.References;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModItems {
	
	public static final ModItems INSTANCE = new ModItems();
	
	public static Item lead_ingot;
	public static Item uranium_ingot;
	
	private List<Item> items;
	
	public void init()
	{
		items = Lists.newArrayList();
		
		lead_ingot = new IcmItem("lead_ingot");
		uranium_ingot = new IcmItem("uranium_ingot");
	}
	
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event)
	{
		for (Item item : items) {
			registerModel(item);
		}
	}
	
	public void registerModel(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(References.MODID, item.getUnlocalizedName().substring(5)), "inventory"));
	}
	
	public List<Item> getItems() {
		return items;
	}
}
