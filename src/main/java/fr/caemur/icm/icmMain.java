package fr.caemur.icm;

import java.io.File;

import fr.caemur.icm.events.RegisteringEvent;
import fr.caemur.icm.init.ModEvents;
import fr.caemur.icm.init.ModRecipes;
import fr.caemur.icm.proxy.CommonProxy;
import fr.caemur.icm.tabs.MachinesTab;
import fr.caemur.icm.tabs.MainTab;
import fr.caemur.icm.utils.References;
import fr.caemur.icm.utils.handlers.ConfigHandler;
import fr.caemur.icm.utils.handlers.GuiHandler;
import fr.caemur.icm.utils.handlers.TileEntityHandler;
import fr.caemur.icm.world.OresGen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION)
public class icmMain {
	
	public static File config;
	
	@Mod.Instance(References.MODID)
	public static icmMain instance;
	
	@SidedProxy(clientSide = References.clientProxy, serverSide = References.serverProxy)
	public static CommonProxy proxy;
	
	public static final CreativeTabs modTab = new MainTab("icmMainTab");
	public static final CreativeTabs machinesTab = new MachinesTab("icmMachinesTab");
	
	public icmMain()
	{
		MinecraftForge.EVENT_BUS.register(new RegisteringEvent());
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit();
		TileEntityHandler.registerTileEntities();
		ModEvents.init();
        ConfigHandler.registerConfig(event);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(icmMain.instance, new GuiHandler());
		GameRegistry.registerWorldGenerator(new OresGen(), 0);
		ModRecipes.instance.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
	}
}
