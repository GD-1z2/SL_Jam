package fr.caemur.icm.events;

import fr.caemur.icm.blocks.solidifier.Solidifier;
import fr.caemur.icm.blocks.solidifier.TileEntitySolidifier;
import fr.caemur.icm.init.ModBlocks;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemUseEvent
{
	@SubscribeEvent
	public void itemUsed(PlayerInteractEvent.RightClickBlock e)
	{
		if (! e.getWorld().isRemote)
		{
			EntityPlayer player = e.getEntityPlayer();
			ItemStack stack = e.getItemStack();
			BlockPos pos = e.getPos();
			World world = e.getWorld();
			IBlockState state = world.getBlockState(pos);

			if (player != null)
			{
				if (e.getWorld().getBlockState(pos).getBlock() == ModBlocks.solidifier)
				{
					TileEntity te1 = world.getTileEntity(pos);

					if (te1 != null && te1 instanceof TileEntitySolidifier)
					{
						TileEntitySolidifier te = (TileEntitySolidifier) te1;

						if (! player.isSneaking())
						{
							if (stack.getItem() == Items.WATER_BUCKET)
							{
								if (te.addLiquid(1))
								{
									stack.shrink(1);
									player.addItemStackToInventory(new ItemStack(Items.BUCKET));
									setSolidifierMeta(world, pos);
								}
							} else if (stack.getItem() == Items.LAVA_BUCKET)
							{
								if (te.addLiquid(2))
								{
									stack.shrink(1);
									player.addItemStackToInventory(new ItemStack(Items.BUCKET));
									setSolidifierMeta(world, pos);
								}						
							} else if (stack.getItem() == Items.BUCKET)
							{
								int liquid = te.getLiquid();
								if (te.removeLiquid(liquid))
								{
									stack.shrink(1);
									if (liquid == 1)
									{
										player.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET));
									} else if (liquid == 2)
									{
										player.addItemStackToInventory(new ItemStack(Items.LAVA_BUCKET));
									}
									
									setSolidifierMeta(world, pos);
								}
							} else
							{
								switch (te.getResultBlock()) {
								case 1:
									world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), new ItemStack(Blocks.PACKED_ICE)));
									setSolidifierMeta(world, pos);
									world.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
									break;

								case 2:
									world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), new ItemStack(ModBlocks.reinforced_obsidian)));
									setSolidifierMeta(world, pos);
									world.playSound((EntityPlayer)null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
									break;
									
								default:
									break;
								}
							}
						} else
						{
							player.sendMessage(new TextComponentString("Contient : " + te.getQt() + "/3"));
						}
					}
				}
			}
		}
	}
	
	private void setSolidifierMeta(World world, BlockPos pos)
	{
		TileEntitySolidifier te = (TileEntitySolidifier) world.getTileEntity(pos);
		int liquid = te.getLiquid();
		int qt = te.getQt();
		
		int meta = qt;
		if (liquid == 1 && qt > 0)
		{
			meta += 3;
		}
		
		world.setBlockState(pos, ModBlocks.solidifier.getDefaultState().withProperty(Solidifier.VARIANT, Solidifier.EnumType.byMetadata(meta)));
		TileEntitySolidifier te2 = (TileEntitySolidifier) world.getTileEntity(pos);
		te2.setData(liquid, qt);
	}
}
