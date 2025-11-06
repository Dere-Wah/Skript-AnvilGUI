package kr.dreamcraft.skript.skriptanvilgui.events.Anvil;

import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import kr.dreamcraft.skript.skriptanvilgui.anvilgui.Anvil;

public class BridgeAnvilClick extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final String anvilId;
    private final AnvilGUI.StateSnapshot state;
    private final Anvil anvil;
    private final Integer slot;

    public BridgeAnvilClick(String anvilId, Integer slot, AnvilGUI.StateSnapshot state, Anvil anvil) {
        this.anvilId = anvilId;
        this.state = state;
        this.slot = slot;
        this.anvil = anvil;
    }

    public String getAnvilId() {
        return this.anvilId;
    }

    public AnvilGUI.StateSnapshot getAnvilState(){
        return this.state;
    }

    public Integer getAnvilSlot(){
        return this.slot;
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
}