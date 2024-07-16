package net.denistor.dungeonrpg.init;



import net.denistor.dungeonrpg.GUI.OpMenuScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.screen.ScreenHandler;
import org.lwjgl.glfw.GLFW;

public class HotKeys {
    public static final KeyBinding O_key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.dungeonrpg_fabric.o_key",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            "key.category.dungeonrpg_fabric.dungeon_rpg"
    ));
    public static void register(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(O_key.wasPressed()){
                client.setScreen(new OpMenuScreen(null));
            }
        });
    }
}
