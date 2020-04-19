package fr.caemur.icm.init;

import fr.caemur.icm.utils.References;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public void init()
	{
		
	}
	
	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event)
	{
		
	}
	
	public void registerModel(Block block)
	{
		ItemBlock ib = new ItemBlock(block);
		ib.setRegistryName(block.getRegistryName());
		GameRegistry.findRegistry(Item.class).register(ib);
		
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(References.MODID, block.getUnlocalizedName().substring(5)), "inventory"));
	}
	
}
