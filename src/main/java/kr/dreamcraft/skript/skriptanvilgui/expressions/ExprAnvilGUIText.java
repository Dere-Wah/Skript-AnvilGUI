package kr.dreamcraft.skript.skriptanvilgui.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import kr.dreamcraft.skript.skriptanvilgui.anvilgui.Anvil;


public class ExprAnvilGUIText extends SimplePropertyExpression<Anvil, String> {

    static {
        register(ExprAnvilGUIText.class, String.class, "text", "virtualanvil");
    }

    @Override
    public String convert(Anvil anvil) {
        return anvil.getText();
    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode){
        if (mode == ChangeMode.SET) {return CollectionUtils.array(String.class);}
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, ChangeMode mode) {
        Anvil anvil = getExpr().getSingle(event);
        if(anvil != null && delta != null){
            anvil.setText((String) delta[0]);
        }
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    protected String getPropertyName() {
        return "text";
    }
}