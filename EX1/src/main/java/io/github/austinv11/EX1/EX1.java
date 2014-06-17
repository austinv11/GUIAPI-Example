package io.github.austinv11.EX1;

import java.util.ArrayList;
import java.util.List;

import io.github.austinv11.GUIAPI.GUIAPI;
import io.github.austinv11.GUIAPI.Menu;
import io.github.austinv11.GUIAPI.MenuInteractEvent;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EX1 extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Implementing GUIAPI v"+Menu.getAPIVersion());
        if (!Menu.getAPIVersion().contains("1.0.")){
        	getLogger().severe("This sample plugin was designed with GUIAPI v1.0.X in mind");
        }
    }
	@Override
    public void onDisable() {
        //Insert logic to be performed when the plugin is disabled
    }
	//Event Handler for GUIAPI event:
	@EventHandler
	public void onMenuInteract(MenuInteractEvent event){
		//If the player clicks on a button with a name that contains 'Keeps the Doctor Away', give the player an apple
		if (event.getButtonName().contains("Keeps the Doctor Away")){
			event.getPlayer().getInventory().addItem(new ItemStack(Material.APPLE));
			event.getPlayer().sendMessage("Here's an apple!");
		}
	}
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	//Code for command "/opengui":
		if (cmd.getName().equalsIgnoreCase("opengui")){
			//Debug message if /toggletestmode is true
			GUIAPI.debugMessage(sender, sender.getName());
			//Debug message if /toggletestmode is true
			GUIAPI.debugMessage("Initiating Menu now");
    		Player player = (Player) sender;
    		//Creating a new Menu object:
    		Menu exampleMenu = new Menu(player, ChatColor.GOLD+"Select an Item", 9);
    		//Creating a description string list:
    		List<String> description = new ArrayList<String>();
    		description.add("Cost: "+ChatColor.AQUA+"Free");
    		//Adding a button to the menu:
    		exampleMenu.addButton(Material.APPLE, "Keeps the Doctor Away", description);
    		//Another description string list:
    		List<String> description2 = new ArrayList<String>();
    		description2.add("Click to toggle");
    		//Adding a button with a specific slot in mind (slot 9 in this case):
    		exampleMenu.setButton(8, Material.REDSTONE_LAMP_OFF, ChatColor.RED+"Off", description2);
    		//Allowing the button to toggle like a T-Flip-Flop (its either on or off):
    		exampleMenu.setButtonToggle(8, true);
    		//Setting what the button will change into when clicked:
    		exampleMenu.setButtonToggleItem(8, Material.REDSTONE_LAMP_ON, ChatColor.GREEN+"On", description2);
    		//Opening the menu for the player:
    		exampleMenu.openMenu();
    		return true;
    	}
    	return false; 
    }
}
