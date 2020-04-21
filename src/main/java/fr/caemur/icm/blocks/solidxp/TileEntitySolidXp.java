package fr.caemur.icm.blocks.solidxp;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySolidXp extends TileEntity {
	
	private int xp;
	
	public TileEntitySolidXp() {
		this.xp = 500;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
//		if (! compound.hasKey("xp"))
//		{
//			compound.setInteger("xp", 500);
//		}
		
		xp = compound.getInteger("xp");
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		
		super.writeToNBT(compound);
		
//		if (compound.hasKey("xp"))
//		{
//			compound.setInteger("xp", compound.getInteger("xp"));
//		}
		
		compound.setInteger("xp", this.xp);
		
		return compound;
	}
	
//	@Override
//	public void onLoad() {
//		setXp(500);
//		xp = 500;
//		getTileData().setInteger("xp", xp);
//		markDirty();
//	}
	
	public void decreaseXp()
	{
//		setXp(getXp() - 1);
		xp--;
//		getTileData().setInteger("xp", xp);
		markDirty();
	}
	
	public int getXp()
	{
		return xp;
	}
	
	public void setXp(int value)
	{
		xp = value;
//		getTileData().setInteger("xp", xp);
		markDirty();
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
