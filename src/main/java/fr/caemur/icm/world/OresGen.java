package fr.caemur.icm.world;

import java.util.Random;

import fr.caemur.icm.init.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class OresGen implements IWorldGenerator
{
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		switch (world.provider.getDimensionType()) {
		case OVERWORLD:
			generateOverworld(world, random, chunkX * 16, chunkZ * 16);
			break;

		case NETHER:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
			break;

		case THE_END:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;

		default:
			break;
		}
	}
	
	private void generateOverworld(World world, Random random, int x, int z)
	{
		generateOre(ModBlocks.lead_ore.getDefaultState(), world, random, x, z, 6, 32, 6, 12, BlockMatcher.forBlock(Blocks.STONE));
		generateOre(ModBlocks.uranium_ore.getDefaultState(), world, random, x, z, 0, 16, 4, 4, BlockMatcher.forBlock(Blocks.STONE));
		generateOre(ModBlocks.solid_xp.getDefaultState(), world, random, x, z, 0, 16, 2, 3, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	private void generateNether(World world, Random random, int x, int z)
	{
		
	}
	
	private void generateEnd(World world, Random random, int x, int z)
	{
		
	}
	
	private void generateOre(IBlockState state, World world, Random random, int x, int z, int minY, int maxY, int maxVeinSize, int chances, BlockMatcher blockChanging)
	{
		int deltaY = maxY - minY;
		int veinSize = random.nextInt(maxVeinSize + 1);
		
		for (int i = 0; i < chances; i++)
		{
			BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));
			
			WorldGenMinable generator = new WorldGenMinable(state, veinSize, blockChanging);
			generator.generate(world, random, pos);
		}
	}
}
