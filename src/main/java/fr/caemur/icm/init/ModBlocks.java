package fr.caemur.icm.init;

import java.util.List;

import com.google.common.collect.Lists;

import fr.caemur.icm.blocks.IcmBlock;
import fr.caemur.icm.blocks.compressor.Compressor;
import fr.caemur.icm.blocks.extractor.Extractor;
import fr.caemur.icm.blocks.solidifier.Solidifier;
import fr.caemur.icm.blocks.solidxp.SolidXp;
import fr.caemur.icm.items.ItemBlockMetadata;
import fr.caemur.icm.utils.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static final ModBlocks INSTANCE = new ModBlocks();

	public static Block lead_ore;
	public static Block uranium_ore;
	public static Block solid_xp;

	public static Block reinforced_obsidian;
	
	public static Block extractor;
	public static Block solidifier;
	public static Block compressor;

	private List<Block> blocks;

	public void init()
	{
		blocks = Lists.newArrayList();

		lead_ore = new IcmBlock("lead_ore", Material.ROCK, 3, 15, 2, "pickaxe");
		uranium_ore = new IcmBlock("uranium_ore", Material.ROCK, 3, 15, 2, "pickaxe");
		solid_xp = new SolidXp("solid_xp");

		reinforced_obsidian = new IcmBlock("reinforced_obsidian", Material.ROCK, 100.0f, 6000.0f, 3, "pickaxe");
		
		extractor = new Extractor("extractor");
		solidifier = new Solidifier();
		compressor = new Compressor();

		for (Block block : blocks)
		{
			ItemBlock ib = new ItemBlock(block);
			ib.setRegistryName(block.getRegistryName());
			GameRegistry.findRegistry(Item.class).register(ib);
		}
	}

	@SubscribeEvent
	public void registerModels(ModelRegistryEvent event)
	{
		for (Block block : blocks) {
			registerModel(block);
		}
	}

	public void registerModel(Block block)
	{
		if (block != null)
		{
			//			ItemBlock ib = new ItemBlock(block);
			//			ib.setRegistryName(block.getRegistryName());
			//			GameRegistry.findRegistry(Item.class).register(ib);

			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(References.MODID, block.getUnlocalizedName().substring(5)), "inventory"));

		}
	}

	public List<Block> getBlocks() {
		return blocks;
	}
}
