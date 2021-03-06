package me.wilux.blockshelf.api.block;

import me.wilux.blockshelf.api.block.CustomBlock;

public interface IBlockInstance
{
	public CustomBlock getCustomBlock();
	public void setMetadataValue(String k, Object v);
	public void removeMetadataValue(String k);
	public boolean hasMetadataValue(String k);
	public Object getMetadataValue(String k);
	public void read();
	public void write();
}
