package kr.dreamcraft.skript.skriptanvilgui.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.EventValues;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import kr.dreamcraft.skript.skriptanvilgui.anvilgui.Anvil;
import kr.dreamcraft.skript.skriptanvilgui.events.Anvil.BridgeAnvilClick;

public class EvtOnAnvilGUIClick extends SkriptEvent {

    static {
        Skript.registerEvent("On AnvilGUI Click", EvtOnAnvilGUIClick.class, BridgeAnvilClick.class,
                "anvil gui click [of %-string%]");

        EventValues.registerEventValue(BridgeAnvilClick.class, String.class,
                event -> event.getAnvilState().getText(),
                EventValues.TIME_NOW);

        EventValues.registerEventValue(BridgeAnvilClick.class, Player.class,
                event -> event.getAnvilState().getPlayer(),
                EventValues.TIME_NOW);

        EventValues.registerEventValue(BridgeAnvilClick.class, Integer.class,
                BridgeAnvilClick::getAnvilSlot,
                EventValues.TIME_NOW);

        EventValues.registerEventValue(BridgeAnvilClick.class, Anvil.class,
                BridgeAnvilClick::getAnvil,
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
        if (!(event instanceof BridgeAnvilClick)) {
            return false;
        }

        if (anvilId == null) {
            return true;
        }

        BridgeAnvilClick anvilEvent = (BridgeAnvilClick) event;
        return anvilEvent.getAnvilId().equals(anvilId.getSingle());
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "anvil gui click" + (anvilId != null ? " of " + anvilId.getSingle() : "");
    }
}