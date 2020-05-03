package fr.caemur.icm.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes
{
	public static ModRecipes instance = new ModRecipes();
	
	public void init()
	{
		GameRegistry.addSmelting(ModBlocks.lead_ore, new ItemStack(ModItems.lead_ingot), 0.6f);
		GameRegistry.addSmelting(ModBlocks.uranium_ore, new ItemStack(ModItems.uranium_ingot), 1.0f);
	}
}
