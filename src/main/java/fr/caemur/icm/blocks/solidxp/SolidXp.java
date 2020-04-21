package fr.caemur.icm.blocks.solidxp;

import java.util.Random;

import fr.caemur.icm.icmMain;
import fr.caemur.icm.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class SolidXp extends Block implements ITileEntityProvider {

	public SolidXp(String name) {
		super(Material.ROCK);
		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(icmMain.modTab);
		setHardness(5);
		setResistance(20);
		setHarvestLevel("pickaxe", 2);

		ModBlocks.INSTANCE.getBlocks().add(this);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Blocks.COBBLESTONE.getItemDropped(Blocks.COBBLESTONE.getDefaultState(), rand, fortune);
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Blocks.STONE);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		TileEntitySolidXp te = new TileEntitySolidXp();
//		te.setXp(500);
		
		return te;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		
		TileEntitySolidXp te = new TileEntitySolidXp();
		return te;
	}

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {

		if (! world.isRemote)
		{
			TileEntity te = world.getTileEntity(pos);

			if (te != null && te instanceof TileEntitySolidXp)
			{
				TileEntitySolidXp te2 = (TileEntitySolidXp)te;

				player.sendMessage(new TextComponentString("XP : " + te2.getXp() + "/500"));
			}
		}
	}

	//	@Override
	//	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
	//			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	//		
	//		if (! world.isRemote)
	//		{
	//			TileEntity te = world.getTileEntity(pos);
	//			Random random = new Random();
	//
	//			if (te != null && te instanceof TileEntitySolidXp)
	//			{
	//				TileEntitySolidXp te2 = (TileEntitySolidXp)te;
	//
	//				if (te2.getXp() > 0)
	//				{
	//					te2.decreaseXp();
	//					world.spawnEntity(new EntityXPOrb(world, pos.getX(), pos.getY(), pos.getZ(), random.nextInt(3) + 1));
	//				} else {
	//					world.removeTileEntity(pos);
	//					world.setBlockState(pos, Blocks.STONE.getDefaultState());
	//				}
	//
	//			}
	//		}	
	//		
	//		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	//	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if (! world.isRemote)
		{
			if (player.getHeldItemMainhand().getItem() == Items.GLASS_BOTTLE)
			{
				TileEntity te = world.getTileEntity(pos);

				if( te != null && te instanceof TileEntitySolidXp)
				{
					TileEntitySolidXp te2 = (TileEntitySolidXp)te;

					if (te2.getXp() >= 15) {
						for (int i = 0; i < 15; i++) {
							te2.decreaseXp();
						}

						player.getHeldItemMainhand().shrink(1);
						player.addItemStackToInventory(new ItemStack(Items.EXPERIENCE_BOTTLE, 1));
					}
				}
			}
		}

		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}
}
