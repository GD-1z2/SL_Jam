package fr.caemur.icm.utils.handlers;

import fr.caemur.icm.blocks.compressor.ContainerCompressor;
import fr.caemur.icm.blocks.compressor.GuiCompressor;
import fr.caemur.icm.blocks.compressor.TileEntityCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if (ID == ConfigHandler.GUI_COMPRESSOR)
		{
			return new ContainerCompressor(player.inventory, (TileEntityCompressor)world.getTileEntity(new BlockPos(x, y, z)));
		}
		
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{		
		if (ID == ConfigHandler.GUI_COMPRESSOR)
		{
			return new GuiCompressor(player.inventory, (TileEntityCompressor)world.getTileEntity(new BlockPos(x, y, z)));
		}
		
		return null;
	}
}
