package me.wilux.blockshelf.listener;

import me.wilux.blockshelf.api.gui.CustomGUI;
import me.wilux.blockshelf.api.store.CustomRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.PlayerInventory;

public class InventoryClickListener implements Listener
{
	
	@EventHandler
	public void onInteract(InventoryClickEvent e)
	{
		if(CustomRegistry.isCustomGUI(e.getInventory()))
		{
			CustomGUI cg = CustomRegistry.getCustomGUIByInventory(e.getInventory());
			
			if(!(e.getClickedInventory() instanceof PlayerInventory))
				if(
						(e.getSlot() == 0 && e.getCurrentItem().hasItemMeta() ? e.getCurrentItem().getItemMeta().isUnbreakable() ? e.getCurrentItem().getDurability() > 0 : false : false) || (e.getSlot() == e.getInventory().getSize() - 9 && e.getCurrentItem().hasItemMeta() ? e.getCurrentItem().getItemMeta().isUnbreakable() ? e.getCurrentItem().getDurability() > 0 : false : false))
			{
				e.setCancelled(true);
			}
			
			
			
			
			if(!e.isCancelled())
			cg.onClick(e);
		}
	}
	
}
