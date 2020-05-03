package fr.caemur.icm.utils.handlers;

import java.io.File;

import fr.caemur.icm.icmMain;
import fr.caemur.icm.utils.References;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler
{

    public static Configuration config;
    
    public static int GUI_COMPRESSOR = 0;
    
    public static void init(File file)
    {
        config = new Configuration(file);
        String category;
        
        category = "GUI IDs";
        config.addCustomCategoryComment(category, "Set id for GUI");
        GUI_COMPRESSOR = config.getInt("Compressor GUI", category, 4, 0, 500, "GUI ID for the compressor");
        
        config.save();
    }
    
    public static void registerConfig(FMLPreInitializationEvent event)
    {
        icmMain.config = new File(event.getModConfigurationDirectory() + "/" + References.MODID);
        icmMain.config.mkdirs();
        init(new File(icmMain.config.getPath(), References.MODID + ".cfg"));
    }
}
