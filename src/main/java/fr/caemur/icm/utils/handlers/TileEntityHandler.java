package fr.caemur.icm.utils.handlers;

import fr.caemur.icm.blocks.compressor.TileEntityCompressor;
import fr.caemur.icm.blocks.solidifier.TileEntitySolidifier;
import fr.caemur.icm.blocks.solidxp.TileEntitySolidXp;
import fr.caemur.icm.utils.References;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntitySolidXp.class, new ResourceLocation(References.MODID + ":solid_xp"));
		GameRegistry.registerTileEntity(TileEntitySolidifier.class, new ResourceLocation(References.MODID + ":solidifier"));
		GameRegistry.registerTileEntity(TileEntityCompressor.class, new ResourceLocation(References.MODID + ":compressor"));
	}
}
