package kr.dreamcraft.skript.skriptanvilgui.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import kr.dreamcraft.skript.skriptanvilgui.anvilgui.Anvil;

public class ExprNewAnvilGUI extends SimpleExpression<Anvil> {

    static {
        Skript.registerExpression(ExprNewAnvilGUI.class, Anvil.class, ExpressionType.SIMPLE,
                "create anvil gui [inventory] with id %string% [with] title %string% [with] [default] text %string%"
        );
    }

    private Expression<String> exprId;
    private Expression<String> exprTitle;
    private Expression<String> exprText;

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        exprId = (Expression<String>) expressions[0];
        exprTitle = (Expression<String>) expressions[1];
        exprText = (Expression<String>) expressions[2];
        return true;
    }

    @Override
    protected Anvil[] get(Event event) {
        String id = exprId.getSingle(event);
        String title = exprTitle.getSingle(event);
        String text = exprText.getSingle(event);

        if (id == null || title == null || text == null) {
            return new Anvil[0];
        }

        Anvil anvil = new Anvil(id);
        anvil.setTitle(title);
        anvil.setText(text);

        return new Anvil[]{anvil};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Anvil> getReturnType() {
        return Anvil.class;
    }

    @Override
    public String toString(Event event, boolean debug) {
        String id = exprId.getSingle(event);
        String title = exprTitle.getSingle(event);
        String text = exprText.getSingle(event);
        return "create anvil gui with id " + (id != null ? id : "unknown") +
                " title " + (title != null ? title : "unknown") +
                " text " + (text != null ? text : "unknown");
    }
}