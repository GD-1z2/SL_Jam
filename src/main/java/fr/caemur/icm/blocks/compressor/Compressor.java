package fr.caemur.icm.blocks.compressor;

import fr.caemur.icm.icmMain;
import fr.caemur.icm.init.ModBlocks;
import fr.caemur.icm.utils.handlers.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Compressor extends Block implements ITileEntityProvider
{
	private String name = "compressor";
	
	public Compressor()
	{
		super(Material.ROCK);
		setUnlocalizedName(name).setRegistryName(name);
		setCreativeTab(icmMain.machinesTab);
		setHardness(3.5f);
		setResistance(17.5f);
		setHarvestLevel("pickaxe", 1);
		
		ModBlocks.INSTANCE.getBlocks().add(this);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityCompressor();
	}
	
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
    {
        playerIn.openGui(icmMain.instance, ConfigHandler.GUI_COMPRESSOR, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
