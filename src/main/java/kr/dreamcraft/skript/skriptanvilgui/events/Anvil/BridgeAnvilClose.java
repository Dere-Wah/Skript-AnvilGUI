package kr.dreamcraft.skript.skriptanvilgui.events.Anvil;

import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import kr.dreamcraft.skript.skriptanvilgui.anvilgui.Anvil;

public class BridgeAnvilClose extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private final String anvilId;
    private final AnvilGUI.StateSnapshot state;
    private final Anvil anvil;
    private boolean cancelled = false;

    public BridgeAnvilClose(String anvilId, AnvilGUI.StateSnapshot state, Anvil anvil) {
        this.anvilId = anvilId;
        this.state = state;
        this.anvil = anvil;
    }

    public String getAnvilId() {
        return this.anvilId;
    }

    public AnvilGUI.StateSnapshot getAnvilState(){
        return state;
    }

    public Anvil getAnvil(){
        return this.anvil;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        if(b){
            anvil.openAnvil(state.getPlayer());
            cancelled = true;
        }
    }
}