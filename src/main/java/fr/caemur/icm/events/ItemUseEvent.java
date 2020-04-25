package fr.caemur.icm.events;

import fr.caemur.icm.init.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
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

			System.out.println("appelé");

			if (player != null)
			{
				if (stack.getItem() == Items.WATER_BUCKET)
				{
					System.out.println("seau d'eau");

				} else if (stack.getItem() == Items.LAVA_BUCKET)
				{
					System.out.println("seau d'eau");
				}

				if (e.getWorld().getBlockState(pos).getBlock() == ModBlocks.solidifier)
				{
					System.out.println("solidifier !!!");
				}
			}
		}
	}
}
