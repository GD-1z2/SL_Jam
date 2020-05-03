package fr.caemur.icm.blocks.solidifier;

import fr.caemur.icm.icmMain;
import fr.caemur.icm.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class Solidifier extends Block implements ITileEntityProvider {

	public static final String NAME = "solidifier";
	public static final PropertyEnum<Solidifier.EnumType> VARIANT = PropertyEnum.<Solidifier.EnumType>create("variant", Solidifier.EnumType.class);

	public Solidifier() {
		super(Material.IRON);
		setRegistryName(this.NAME).setUnlocalizedName(this.NAME);
		setCreativeTab(icmMain.machinesTab);
		setHardness(3.5f);
		setResistance(17.5f);
		setHarvestLevel("pickaxe", 0);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, Solidifier.EnumType.ZERO));

		ModBlocks.INSTANCE.getBlocks().add(this);
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return state.getValue(VARIANT).getMetadata();
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for (Solidifier.EnumType type : Solidifier.EnumType.values()) {
			items.add(new ItemStack(this, 1, type.getMetadata()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, Solidifier.EnumType.byMetadata(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Solidifier.EnumType)state.getValue(VARIANT)).getMetadata();
	}

	@Override
	protected BlockStateContainer createBlockState() {

		return new BlockStateContainer(this, new IProperty[]{VARIANT});
	}


	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntitySolidifier();
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntitySolidifier();
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
//		System.out.println("je suis activé");
//		
//		if (! world.isRemote)
//		{
//			System.out.println("je suis pas remote");
//			
//			TileEntity te1 = world.getTileEntity(pos);
//
//			if (te1 == null)
//			{
//				System.out.println("je suis null");
//			}
//			
//			if (!(te1 instanceof TileEntitySolidifier))
//			{
//				System.out.println("je suis pas la bonne te");
//			}
//			
//			if (te1 != null && te1 instanceof TileEntitySolidifier)
//			{
//				TileEntitySolidifier te = (TileEntitySolidifier)te1;
//				
//				System.out.println("je suis une bonne te");
//				
//				if (! player.isSneaking())
//				{
//					System.out.println("pas sneak");
//					
//					if (player.getHeldItemMainhand().getItem() == Items.WATER_BUCKET)
//					{
//						te.addLiquid(1);
//					} else if (player.getHeldItemMainhand().getItem() == Items.LAVA_BUCKET)
//					{
//						te.addLiquid(2);
//					} else if (player.getHeldItemMainhand().getItem() == Items.BUCKET)
//					{
//						int liquid2 = te.getLiquid();
//						if (te.removeLiquid(liquid2))
//						{
//							switch (liquid2) {
//							case 1:
//								player.getHeldItemMainhand().shrink(1);
//								player.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET, 1));
//								break;
//
//							case 2:
//								player.getHeldItemMainhand().shrink(1);
//								player.addItemStackToInventory(new ItemStack(Items.LAVA_BUCKET, 1));
//								break;
//							}
//						}
//					} else
//					{
//						int resultBlock = te.getResultBlock();
//						switch (resultBlock) {
//						case 1:
//							world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(Blocks.PACKED_ICE, 1)));
//							break;
//
//						case 2:
//							world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(Blocks.OBSIDIAN, 1)));
//							break;
//
//						default:
//							break;
//						}
//					}
//				} else {
//					System.out.println("sneak");
//					player.sendMessage(new TextComponentString("Contient : " + te.getQt() + "/3"));
//				}
//			}
//		}
//		
		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}

	public static enum EnumType implements IStringSerializable
	{
		ZERO(0, "solidifier_zero", "solidifier_zero"),
		ONE(1, "solidifier_one", "solidifier_one"),
		TWO(2, "solidifier_two", "solidifier_two"),
		THREE(3, "solidifier_three", "solidifier_three"),
		WONE(4, "solidifier_wone", "solidifier_wone"),
		WTWO(5, "solidifier_wtwo", "solidifier_wtwo"),
		WTHREE(6, "solidifier_wthree", "solidifier_wthree");

		private static final Solidifier.EnumType[] META_LOOKUP = new Solidifier.EnumType[values().length];
		private final int meta;
		private final String name;
		private final String unlocalizedName;

		private EnumType(int metaIn, String nameIn, String unlocalizedIn)
		{
			this.meta = metaIn;
			this.name = nameIn;
			this.unlocalizedName = unlocalizedIn;

		}

		public static String[] getUnlocalizedNames()
		{
			String[] names = new String[values().length];

			for (int i = 0; i < META_LOOKUP.length; i++) {
				names[i] = META_LOOKUP[i].unlocalizedName;
			}

			return names;
		}

		public int getMetadata()
		{
			return this.meta;
		}

		public static Solidifier.EnumType byMetadata(int meta)
		{
			//			if (meta < 0 || meta >= META_LOOKUP.length)
			//			{
			//				meta = 0;
			//			}

			//			for (int i = 0; i < META_LOOKUP.length; i++) {
			//				System.out.println("nom : " + META_LOOKUP[i].name + " / id : " + META_LOOKUP[i].meta);
			//			}

			//			return META_LOOKUP[meta];
			switch (meta) {
			case 1:
				return EnumType.ONE;

			case 2:
				return EnumType.TWO;

			case 3:
				return EnumType.THREE;

			case 4:
				return EnumType.WONE;

			case 5:
				return EnumType.WTWO;

			case 6:
				return EnumType.WTHREE;
				
			default:
				return EnumType.ZERO;
			}
		}

		public String toString()
		{
			return this.name;
		}

		@Override
		public String getName()
		{
			return this.name;
		}
	}
}
