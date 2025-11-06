package kr.dreamcraft.skript.skriptanvilgui.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import kr.dreamcraft.skript.skriptanvilgui.anvilgui.Anvil;

public class EffOpenAnvilGUI extends Effect {
    static{
        Skript.registerEffect(EffOpenAnvilGUI.class,
                "open (anvilgui|anvil gui) %virtualanvil% to %player%");
    }

    private Expression<Anvil> exprAnvil;
    private Expression<Player> exprPlayer;


    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult){
        exprAnvil = (Expression<Anvil>) expressions[0];
        exprPlayer = (Expression<Player>) expressions[1];
        return true;
    }

    @Override
    protected void execute(Event event) {
        Anvil anvil = exprAnvil.getSingle(event);
        Player player = exprPlayer.getSingle(event);
        if(anvil != null && player != null){
            anvil.getBuilder().open(player);
        }
    }

    @Override
    public String toString(Event event, boolean b) {
        Player player = exprPlayer.getSingle(event);
        Anvil anvil = exprAnvil.getSingle(event);
        return "open anvil gui "
                + (anvil != null ? anvil.toString() : "")
                + (player != null ? player.toString() : "");
    }
}
