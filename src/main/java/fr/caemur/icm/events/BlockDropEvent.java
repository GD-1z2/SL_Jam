package fr.caemur.icm.events;

import java.util.List;

import fr.caemur.icm.init.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockDropEvent {

	@SubscribeEvent
	public void dropBlock(HarvestDropsEvent e)
	{
		World world = e.getWorld();
		EntityPlayer player = e.getHarvester();
		IBlockState state = e.getState();
		List<ItemStack> drops = e.getDrops();

		if (! (world.isRemote) && player != null)
		{
			ItemStack held = player.getHeldItemMainhand();

			if (held.getItem() == ModItems.uranium_pickaxe) // PICKAXE
			{
				for (int i = 0; i < drops.size(); i++)
				{
					ItemStack result = FurnaceRecipes.instance().getSmeltingResult(drops.get(i));

					System.out.println(result.getDisplayName());
					
					if (result != null && result.getItem() != Item.getItemFromBlock(Blocks.AIR))
					{
						drops.set(i, new ItemStack(result.getItem(), result.getCount()));
					}
				}
			}
			else if (held.getItem() == ModItems.uranium_axe) // AXE
			{
				if (state.getBlock() == Blocks.LOG)
				{
					for (int i = 0; i < drops.size(); i++)
					{
						if (drops.get(i).getItem() == Item.getItemFromBlock(Blocks.LOG))
						{
							drops.set(i, new ItemStack(Blocks.PLANKS, 5, drops.get(i).getMetadata()));
						}
					}
				}
				else if (state.getBlock() == Blocks.LOG2)
				{
					for (int i = 0; i < drops.size(); i++)
					{
						if (drops.get(i).getItem() == Item.getItemFromBlock(Blocks.LOG2))
						{
							drops.set(i, new ItemStack(Blocks.PLANKS, 5, drops.get(i).getMetadata() + 4));
						}
					}
				}
			}
		}
	}
}
