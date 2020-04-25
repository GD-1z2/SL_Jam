package fr.caemur.icm.blocks.solidifier;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntitySolidifier extends TileEntity implements ITickable{

	private int liquid; // 0 nothing, 1 water, 2 lava
	private int qt;
	
	public TileEntitySolidifier() {
		this.liquid = 0;
		this.qt = 0;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		
		this.liquid = compound.getInteger("liquid");
		this.qt = compound.getInteger("qt");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{	
		super.writeToNBT(compound);
		
		compound.setInteger("liquid", this.liquid);
		compound.setInteger("qt", this.qt);
		
		return compound;
	}
	
	public boolean addLiquid(int liquidToAdd)
	{
		if (this.liquid == liquidToAdd || this.liquid == 0)
		{
			if (this.qt < 3)
			{
				this.liquid = liquidToAdd;
				this.qt++;
				return true;
			}
		}
		
		return false;
	}
	
	public boolean removeLiquid(int liquidToRemove)
	{
		if (this.liquid == liquidToRemove)
		{
			if (this.qt > 0)
			{
				this.qt--;
				
				if (this.qt == 0)
				{
					this.liquid = 0;
				}
			}
		}
		
		return false;
	}
	
	public int getLiquid()
	{
		return this.liquid;
	}
	
	public int getQt()
	{
		return this.qt;
	}
	
	public int getResultBlock()
	{
		if (this.qt == 3)
		{
			switch (this.liquid) {
			case 1:
				this.liquid = 0;
				this.qt = 0;
				return 1;

			case 2:
				this.liquid = 0;
				this.qt = 0;
				return 2;

			default:
				return 0;
			}
		}
		
		return 0;
	}

	@Override
	public void update()
	{
		if (getBlockMetadata() != this.qt)
		{
			IBlockState block = this.getWorld().getBlockState(this.pos);
			if (block.getBlock() instanceof Solidifier)
			{
				Solidifier block2 = (Solidifier)block.getBlock();
				
				block2.setState(this.qt, this.getWorld(), this.pos);
			}
		}
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {

		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);
		int meta = getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, meta, compound);		
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.getNbtCompound());
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);
		return compound;
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}
	
	@Override
	public NBTTagCompound getTileData() {
		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);
		return compound;
	}
}
