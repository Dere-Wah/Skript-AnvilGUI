package kr.dreamcraft.skript.skriptanvilgui.anvilgui;

import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import kr.dreamcraft.skript.skriptanvilgui.SkriptAnvilGUI;
import kr.dreamcraft.skript.skriptanvilgui.events.Anvil.BridgeAnvilClick;
import kr.dreamcraft.skript.skriptanvilgui.events.Anvil.BridgeAnvilClose;

import java.util.Arrays;

public class Anvil {

    // ✅ 레지스트리 제거 - GuiInventory처럼 변수로만 관리
    private final String id;
    private final AnvilGUI.Builder builder;
    private String title;
    private String text;
    private ItemStack itemLeft;
    private ItemStack itemRight;
    private ItemStack itemOutput;

    public Anvil(String id){
        this.id = id;
        this.builder = new AnvilGUI.Builder();
        this.text = "Input Text";
        this.title = "Anvil Title";
        builder.text(this.text);
        builder.title(this.title);
        builder.plugin(SkriptAnvilGUI.getInstance());
        builder.onClick((slot, stateSnapshot) -> {
            return Arrays.asList(AnvilGUI.ResponseAction.run(() ->
                    Bukkit.getPluginManager().callEvent(new BridgeAnvilClick(this.id, slot, stateSnapshot, this))));
        });
        builder.onClose(stateSnapshot -> {
            Bukkit.getPluginManager().callEvent(new BridgeAnvilClose(this.id, stateSnapshot, this));
        });
        // ✅ 레지스트리에 저장하지 않음
    }

    public String getId() {
        return this.id;
    }

    public void setText(String text){
        this.text = text;
        this.builder.text(text);
    }

    public String getText(){
        return this.text;
    }

    public void setTitle(String title){
        this.title = title;
        this.builder.title(title);
    }

    public String getTitle(){
        return this.title;
    }

    public void setItemLeft(ItemStack item){
        this.itemLeft = item;
        this.builder.itemLeft(item);
    }

    public ItemStack getItemLeft(){
        return this.itemLeft;
    }

    public void setItemRight(ItemStack item){
        this.itemRight = item;
        this.builder.itemRight(item);
    }

    public ItemStack getItemRight(){
        return this.itemRight;
    }

    public void setItemOutput(ItemStack item){
        this.itemOutput = item;
        this.builder.itemOutput(item);
    }

    public ItemStack getItemOutput(){
        return this.itemOutput;
    }

    public AnvilGUI.Builder getBuilder(){
        return this.builder;
    }

    public void openAnvil(Player player){
        Bukkit.getScheduler().runTaskLater(SkriptAnvilGUI.getInstance(), () -> getBuilder().open(player), 1);
    }

    @Override
    public String toString() {
        return "anvil gui " + id;
    }
}