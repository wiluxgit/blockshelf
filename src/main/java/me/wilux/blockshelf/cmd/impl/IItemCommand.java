package me.wilux.blockshelf.cmd.impl;

import me.wilux.blockshelf.api.item.CustomItem;
import me.wilux.blockshelf.api.store.CustomRegistry;
import me.wilux.blockshelf.cmd.ISubCommand;
import me.wilux.blockshelf.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class IItemCommand implements ISubCommand
{

	@Override
	public String commandName()
	{
		return "item";
	}

	@Override
	public String[] argumentNames()
	{
		return new String[] { "{ITEM_ID}" };
	}

	@Override
	public String description(
			) {
		return "Gives the player the specific custom item.";
	}

	@Override
	public boolean playerOnly()
	{
		return true;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(args.length == 1)
		{
			Utils.sendDetailedList(sender, CustomRegistry.CUSTOM_ITEM_REGISTRY);
			return true;
		}
		
		Player p = (Player) sender;
		for (CustomItem ci : CustomRegistry.CUSTOM_ITEM_REGISTRY)
		{
			if (args[1].equals(ci.getName()))
			{
				if (p.hasPermission(new Permission("cu.item." + ci.getName(), PermissionDefault.OP)))
					p.getInventory().addItem(ci.getItem());
				else
					p.sendMessage("No permission!");
			}
		}

		return false;
	}

}
