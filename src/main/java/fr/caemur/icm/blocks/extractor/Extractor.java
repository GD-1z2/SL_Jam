package fr.caemur.icm.blocks.extractor;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.google.common.collect.Lists;

import fr.caemur.icm.icmMain;
import fr.caemur.icm.blocks.solidxp.TileEntitySolidXp;
import fr.caemur.icm.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import scala.Array;

public class Extractor extends Block {

	public static final AxisAlignedBB EXTRACTOR_AABB_NORTH = new AxisAlignedBB(0.5938D, 0.125D, 1.D, 0.4062D, 0.625D, 0.3125D);
	public static final AxisAlignedBB EXTRACTOR_AABB_EAST = new AxisAlignedBB(0.0D, 0.125D, 0.4062D, 0.6875D, 0.625D, 0.5938D);
	public static final AxisAlignedBB EXTRACTOR_AABB_SOUTH = new AxisAlignedBB(0.4062D, 0.125D, 0.0D, 0.5938D, 0.625D, 0.6875D);
	public static final AxisAlignedBB EXTRACTOR_AABB_WEST = new AxisAlignedBB(1.0D, 0.125D, 0.5938D, 0.3125D, 0.625D, 0.4062D);

	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public Extractor(String name) {
		super(Material.IRON);
		setRegistryName(name).setUnlocalizedName(name);
		setHardness(3.0f);
		setResistance(5.0f);
		setHarvestLevel("pickaxe", 1);
		setCreativeTab(icmMain.machinesTab);

		ModBlocks.INSTANCE.getBlocks().add(this);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int meta = getMetaFromState(state);

		switch (meta) {
		case 2:
			return EXTRACTOR_AABB_NORTH;

		case 5:
			return EXTRACTOR_AABB_EAST;

		case 3:
			return EXTRACTOR_AABB_SOUTH;

		case 4:
			return EXTRACTOR_AABB_WEST;

		default:
			return EXTRACTOR_AABB_SOUTH;
		}

	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if (! world.isRemote)
		{
			int meta = getMetaFromState(state);
			BlockPos tePos;

			if (meta == 2) {
				tePos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
			} else if (meta == 5) {
				tePos = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
			} else if (meta == 3) {
				tePos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
			} else {
				tePos = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
			}

			TileEntity te = world.getTileEntity(tePos);
			Random random = new Random();

			if (te != null && te instanceof TileEntitySolidXp)
			{
				TileEntitySolidXp te2 = (TileEntitySolidXp)te;

				if (te2.getXp() > 0)
				{
					te2.decreaseXp();
					world.spawnEntity(new EntityXPOrb(world, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, random.nextInt(3) + 1));
				} else {
					world.removeTileEntity(pos);
					world.setBlockState(tePos, Blocks.STONE.getDefaultState());
				}

			}
		}	

		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}

	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
	{
		if (!worldIn.isRemote)
		{
			IBlockState iblockstate = worldIn.getBlockState(pos.north());
			IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
			IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
			IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
			EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

			if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
			{
				enumfacing = EnumFacing.SOUTH;
			}
			else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
			{
				enumfacing = EnumFacing.NORTH;
			}
			else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
			{
				enumfacing = EnumFacing.EAST;
			}
			else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
			{
				enumfacing = EnumFacing.WEST;
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
		}
	}


	public static void setState(boolean active, World worldIn, BlockPos pos)
	{
		IBlockState iblockstate = worldIn.getBlockState(pos);

		worldIn.setBlockState(pos, ModBlocks.extractor.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
		worldIn.setBlockState(pos, ModBlocks.extractor.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		if(enumfacing.getAxis() == EnumFacing.Axis.Y)
		{
			enumfacing = EnumFacing.NORTH;
		}
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state) 
	{
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) 
	{
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) 
	{
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {FACING}); 
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {

		List<String> info = Lists.newArrayList();
		info.add("Permet d'extraire de l'expérience d'un bloc d'expérience solidifée");
		info.add("Clic droit pour utiliser");
		super.addInformation(stack, player, tooltip, advanced);
	}

	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		EnumFacing pFace = placer.getHorizontalFacing();
		worldIn.setBlockState(pos, state.withProperty(FACING, pFace.getOpposite()));

		//		EnumFacing pFace = placer.getHorizontalFacing();
		//		
		//		int meta = getMetaFromState(state);
		//		IBlockState behindBlock;
		//
		//		if (meta == 2) {
		//			behindBlock = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1));
		//		} else if (meta == 5) {
		//			behindBlock = worldIn.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()));
		//		} else if (meta == 3) {
		//			behindBlock = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1));
		//		} else {
		//			behindBlock = worldIn.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()));
		//		}
		//
		//		System.out.println(behindBlock.getBlock().getLocalizedName());
		//		
		//		if (behindBlock != null) {
		//
		//			if (behindBlock.isFullBlock() && behindBlock.isFullCube() && behindBlock != Blocks.AIR.getDefaultState()) {
		//				worldIn.setBlockState(pos, state.withProperty(FACING, pFace.getOpposite()));
		//			}
		//		}

	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) 
	{
		this.setDefaultFacing(worldIn, pos, state);

		//		int meta = getMetaFromState(state);
		//		IBlockState behindBlock;
		//
		//		if (meta == 2) {
		//			behindBlock = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1));
		//		} else if (meta == 5) {
		//			behindBlock = worldIn.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()));
		//		} else if (meta == 3) {
		//			behindBlock = worldIn.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1));
		//		} else {
		//			behindBlock = worldIn.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()));
		//		}
		//
		//		System.out.println(behindBlock.getBlock().getLocalizedName());
		//		
		//		if (behindBlock != null) {
		//
		//			if (behindBlock.isFullBlock() && behindBlock.isFullCube() && behindBlock != Blocks.AIR.getDefaultState()) {
		//				this.setDefaultFacing(worldIn, pos, state);
		//			}
		//		}
	}


}
