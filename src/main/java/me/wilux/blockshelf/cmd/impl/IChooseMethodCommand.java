package me.wilux.blockshelf.cmd.impl;

import me.wilux.blockshelf.cmd.ISubCommand;
import me.wilux.blockshelf.init.InitializationMethodRegistry;
import me.wilux.blockshelf.util.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class IChooseMethodCommand implements ISubCommand
{

	@Override
	public String commandName()
	{
		return "method";
	}

	@Override
	public String[] argumentNames()
	{
		return new String[] { "{dropbox|minepack}" };
	}

	@Override
	public String description()
	{
		return "Changes the upload method of the resource pack.";
	}

	@Override
	public boolean playerOnly()
	{
		return false;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(sender.hasPermission(new Permission("cu.choosemethod", PermissionDefault.OP)) && InitializationMethodRegistry.getMethod(args[1]) != null && args.length == 2)
		{
			Config.getConfiguration().set("init-method", args[1]);
		}
		return false;
	}

}
