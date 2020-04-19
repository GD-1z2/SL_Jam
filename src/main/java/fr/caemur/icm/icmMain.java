package fr.caemur.icm;

import fr.caemur.icm.events.RegisteringEvent;
import fr.caemur.icm.proxy.CommonProxy;
import fr.caemur.icm.utils.References;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION)
public class icmMain {
	
	@Mod.Instance(References.MODID)
	public static icmMain instance;
	
	@SidedProxy(clientSide = References.clientProxy, serverSide = References.serverProxy)
	public static CommonProxy proxy;
	
	public icmMain()
	{
		MinecraftForge.EVENT_BUS.register(new RegisteringEvent());
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit();
	}
}
