package fr.caemur.icm.blocks;

import fr.caemur.icm.icmMain;
import fr.caemur.icm.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class IcmBlock extends Block {

	public IcmBlock(String name, Material materialIn)
	{
		super(materialIn);
		setRegistryName(name).setUnlocalizedName(name);
		setCreativeTab(icmMain.modTab);

		ModBlocks.INSTANCE.getBlocks().add(this);
	}

	public IcmBlock(String name, Material materialIn, float hardness, float resistance)
	{
		this(name, materialIn);
		setHardness(hardness);
		setResistance(resistance);
	}
	
	public IcmBlock(String name, Material materialIn, float hardness, float resistance, int harvestLevel, String harvestType)
	{
		this(name, materialIn, hardness, resistance);
		setHarvestLevel(harvestType, harvestLevel);
	}
	
}
