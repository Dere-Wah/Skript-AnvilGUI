package kr.dreamcraft.skript.skriptanvilgui.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.EventValues;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import kr.dreamcraft.skript.skriptanvilgui.anvilgui.Anvil;
import kr.dreamcraft.skript.skriptanvilgui.events.Anvil.BridgeAnvilClose;

public class EvtOnAnvilGUIClose extends SkriptEvent {

    static {
        Skript.registerEvent("On AnvilGUI Close", EvtOnAnvilGUIClose.class, BridgeAnvilClose.class,
                "anvil gui close [of %-string%]");

        EventValues.registerEventValue(BridgeAnvilClose.class, String.class,
                event -> event.getAnvilState().getText(),
                EventValues.TIME_NOW);

        EventValues.registerEventValue(BridgeAnvilClose.class, Player.class,
                event -> event.getAnvilState().getPlayer(),
                EventValues.TIME_NOW);

        EventValues.registerEventValue(BridgeAnvilClose.class, Anvil.class,
                BridgeAnvilClose::getAnvil,
                EventValues.TIME_NOW);
    }

    private Literal<String> anvilId;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
        if (args[0] != null) {
            anvilId = (Literal<String>) args[0];
        }
        return true;
    }

    @Override
    public boolean check(Event event) {
        if (!(event instanceof BridgeAnvilClose)) {
            return false;
        }

        if (anvilId == null) {
            return true;
        }

        BridgeAnvilClose anvilEvent = (BridgeAnvilClose) event;
        return anvilEvent.getAnvilId().equals(anvilId.getSingle());
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "anvil gui close" + (anvilId != null ? " of " + anvilId.getSingle() : "");
    }
}