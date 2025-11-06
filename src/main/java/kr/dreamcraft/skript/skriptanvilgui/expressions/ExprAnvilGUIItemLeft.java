package kr.dreamcraft.skript.skriptanvilgui.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import kr.dreamcraft.skript.skriptanvilgui.anvilgui.Anvil;


public class ExprAnvilGUIItemLeft extends SimplePropertyExpression<Anvil, ItemStack> {

    static {
        register(ExprAnvilGUIItemLeft.class, ItemStack.class, "left (slot|item)", "virtualanvil");
    }

    @Override
    public ItemStack convert(Anvil anvil) {
        return anvil.getItemLeft();
    }

    @Override
    public Class<?>[] acceptChange(final ChangeMode mode){
        if(mode == ChangeMode.SET){
            return CollectionUtils.array(ItemStack.class);
        }
        return null;
    }

    @Override
    public void change(Event event, Object[] delta, ChangeMode mode) {
        Anvil anvil = getExpr().getSingle(event);
        if(anvil != null){
            anvil.setItemLeft((ItemStack) delta[0]);
        }
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    protected String getPropertyName() {
        return "left item";
    }
}