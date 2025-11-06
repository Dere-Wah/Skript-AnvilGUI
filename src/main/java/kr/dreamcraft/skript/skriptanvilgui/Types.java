package kr.dreamcraft.skript.skriptanvilgui;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import kr.dreamcraft.skript.skriptanvilgui.anvilgui.Anvil;

public class Types {

    static {
        Classes.registerClass(new ClassInfo<>(Anvil.class, "virtualanvil")
                .user("(?:virtual(?: )?)?anvil(?:(?: )?gui)?")
                .defaultExpression(new EventValueExpression<>(Anvil.class))
                .name("anvil gui")
                .description("Represents an anvil gui.")
                .parser(new Parser<Anvil>(){

                    @Override
                    public boolean canParse(ParseContext parseContext){
                        return false;
                    }

                    @Override
                    public String toString(Anvil anvil, int debug){
                        return anvil.toString();
                    }

                    @Override
                    public String toVariableNameString(Anvil anvil){
                        return anvil.toString();
                    }
                })
        );
    }
}
