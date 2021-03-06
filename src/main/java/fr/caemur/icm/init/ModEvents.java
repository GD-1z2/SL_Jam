package fr.caemur.icm.init;

import fr.caemur.icm.events.BlockDropEvent;
import fr.caemur.icm.events.ItemUseEvent;
import fr.caemur.icm.events.OverrideRecipes;
import net.minecraftforge.common.MinecraftForge;

public class ModEvents
{
	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(new ItemUseEvent());
		MinecraftForge.EVENT_BUS.register(new BlockDropEvent());
		MinecraftForge.EVENT_BUS.register(new OverrideRecipes());
	}
}
