package net.GravityNetwork.Servers;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Jesse on 18-12-2015.
 */
public class Main extends JavaPlugin implements Listener{

    private static Plugin plugin;
    public static Plugin getPlugin() {
        return plugin;
    }

    public void onEnable(){
        Main.plugin= this;
        initConfig();
        initClasses();

    }
    public void onDisable(){
        Main.plugin=null;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args){
        if(cmd.getName().equalsIgnoreCase("server")){
            if(args.length ==0) {
                Player p = (Player) sender;
                p.openInventory(new Menus().server());
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getPlugin().getConfig().getString("open.prefix") + Main.getPlugin().getConfig().getString("open.servergui")));
            }
            else if(args.length==1){
                if(args[0].equalsIgnoreCase("hub")){
                    Player p =(Player) sender;
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF("hub");
                    p.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", out.toByteArray());
                }
                else if(args[0].equalsIgnoreCase("factions")){
                    Player p =(Player) sender;
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF("opfactions");
                    p.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", out.toByteArray());
                }
                else if(args[0].equalsIgnoreCase("prison")){
                    Player p =(Player) sender;
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF("prison");
                    p.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", out.toByteArray());
                }
                else if(args[0].equalsIgnoreCase("creative")){
                    Player p =(Player) sender;
                    p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Connect");
                    out.writeUTF("opprison");
                    p.sendPluginMessage(Main.getPlugin(Main.class), "BungeeCord", out.toByteArray());
                }
            }
        }


        return true;

    }


private void initClasses(){
    PluginManager pm = Bukkit.getPluginManager();
    pm.registerEvents(new ClickEvent(), this);
    pm.registerEvents(this, this);
}
    private void initConfig(){
        /**
         * Basic plugin stuff
         */
        getConfig().addDefault("Menus.ServerSelector.Name", "&5Join a server.");
        getConfig().addDefault("Menus.ServerSelector.servers.lore", "'&6>> Click here to join!");
        getConfig().addDefault("Menus.ServerSelector.MSG", "&aConnecting you to ");
        /**
         * Hub
         */
        getConfig().addDefault("Menus.ServerSelector.Hub.Name", "&bHub");
        getConfig().addDefault("Menus.ServerSelector.Hub.Server", "hub");
        /**
         * OPFactions
         */
        getConfig().addDefault("Menus.ServerSelector.OPFactions.Name", "&5OP Factions");
        getConfig().addDefault("Menus.ServerSelector.OPFactions.Server", "factions");

        /**
         * OP Prison
         */
        getConfig().addDefault("Menus.ServerSelector.OPPrison.Name", "&8OP Prison");
        getConfig().addDefault("Menus.ServerSelector.OPPrison.Server", "prison");

        /**
         * Creative
         */
        getConfig().addDefault("Menus.ServerSelector.Creative.Name", "&aCreative");
        getConfig().addDefault("Menus.ServerSelector.Creative.Server", "creative");

        /**
         * Open messages
         */
        getConfig().addDefault("open.prefix", "&a&lOPENING&7: ");

        getConfig().addDefault("open.servergui", "&bServer inventory");

    }
    @EventHandler
    public void commands(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().startsWith("/creative") || e.getMessage().startsWith("creative")) {
            e.setCancelled(true);
            e.getPlayer().performCommand("server creative");
        }
        if (e.getMessage().startsWith("/server opprison") || e.getMessage().startsWith("server opprison")) {
            e.setCancelled(true);
            e.getPlayer().performCommand("server prison");
        }
        if (e.getMessage().startsWith("/prison") || e.getMessage().startsWith("prison")) {
            e.setCancelled(true);
            e.getPlayer().performCommand("server prison");
        }
        if (e.getMessage().startsWith("/hub") || e.getMessage().startsWith("hub")) {
            e.setCancelled(true);
            e.getPlayer().performCommand("server hub");
        }
        if (e.getMessage().startsWith("/factions") || e.getMessage().startsWith("factions")) {
            e.setCancelled(true);
            e.getPlayer().performCommand("server factions");
        }
        if (e.getMessage().startsWith("/opfactions") || e.getMessage().startsWith("opfactions")) {
            e.setCancelled(true);
            e.getPlayer().performCommand("server factions");
        }
    }
}

