package me.wilux.blockshelf.main;

import me.lucko.commodore.Commodore;
import me.lucko.commodore.CommodoreProvider;
import me.wilux.blockshelf.api.block.CustomBlock;
import me.wilux.blockshelf.api.item.CustomItem;
import me.wilux.blockshelf.api.store.CustomRegistry;
import me.wilux.blockshelf.cmd.CommandRegistry;
import me.wilux.blockshelf.cmd.CustomUtilsCommandExecutor;
import me.wilux.blockshelf.api.block.IGuiAble;
import me.wilux.blockshelf.cmd.BrigaderCompleter;
import me.wilux.blockshelf.example.WateringCan;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.logging.Logger;

public class Blockshelf extends CustomUtils {

    private static Blockshelf instance;
    private static Logger logger;
    private static HashMap<Player, IGuiAble> currentlyOpenInventories;

    @Override
    public void onEnable() {
        instance = this;
        logger = this.getServer().getLogger();
        super.onEnable();

        //TODO: namespace with plugin
        //TODO: fix worldstores (or remove them)
        //TODO: auto find items in assets/item and move around to make the pack (Hard)
        //TODO: fix GUI registry
        //TODO: make armorstands marker and invisible

        CustomBlock exDirt = new CustomBlock("exdirt","pseudoblock/exdirt","Exdirt");
        CustomRegistry.register(exDirt,this);
        CustomItem wireSpool = new CustomItem("wire_spool","pseudoitem/wire_spool","Wire Spool");
        CustomRegistry.register(wireSpool,this);
        CustomItem wateringCan = new WateringCan(
                "watering_can","pseudoitem/watering_can","Watering Can", Material.CARROT_ON_A_STICK, 13
        );
        CustomRegistry.register(wateringCan,this);

        //TODO: THIS MUST RUN AFTER BLOCK REGISTRATION, MAKE SURE TO FIX
        CommandRegistry.initSubCommands();
        PluginCommand command = Bukkit.getPluginCommand("blockshelf");
        command.setExecutor(new CustomUtilsCommandExecutor());
        if (CommodoreProvider.isSupported()) {
            Commodore commodore = CommodoreProvider.getCommodore(this);
            registerCommodoreCompletions(commodore, command, "blockshelf");
        }

    }

    public static Blockshelf getInstance(){return instance;}
    public static Logger getLog(){return logger;}
    public static HashMap<Player, IGuiAble> getCurrentlyOpenInventories(){return currentlyOpenInventories;}

    @Override
    public void onDisable() {

    }

    // You will need to put this method inside another class to prevent classloading
    // errors when your plugin loads on pre 1.13 versions.
    private static void registerCommodoreCompletions(Commodore commodore, PluginCommand command, String name) {
        commodore.register(command, BrigaderCompleter.get(name));
    }

    static final int GUI_CHEST = 2;
    static final int GUI_STONECUTTER = 22;
}
