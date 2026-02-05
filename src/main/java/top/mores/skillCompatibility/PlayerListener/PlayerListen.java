package top.mores.skillCompatibility.PlayerListener;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerListen implements Listener {

    @EventHandler
    public void onPlayerClickInv(InventoryClickEvent event){
        HumanEntity player= event.getWhoClicked();

    }
}
