package net.denistor.dungeonrpg.GUI;

import net.denistor.dungeonrpg.network.DungeonRPG_Network;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;


import net.minecraft.client.RunArgs;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;


@Environment(EnvType.CLIENT)

public class OpMenuScreen extends Screen {
    public OpMenuScreen(Screen parent) {
        super(Text.literal("My tutorial screen"));
        this.parent = parent;
    }
    private final Screen parent;
    @Override
    public void close() {
        client.setScreen(parent);
    }


    public ButtonWidget button1;
    public ButtonWidget button2;

    @Override
    protected void init() {
        button1 = ButtonWidget.builder(Text.literal("Teleport to TerraFortis world..."), button -> {
                    DungeonRPG_Network.send(DungeonRPG_Network.teleport_to_TerraFortis);
                })
                .dimensions(width / 2 - 205, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button1")))
                .build();
        button2 = ButtonWidget.builder(Text.literal("Teleport to Main World..."), button -> {
                    DungeonRPG_Network.send(DungeonRPG_Network.teleport_to_MainWorld);
                })
                .dimensions(width / 2 + 5, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button2")))
                .build();

        addDrawableChild(button1);
        addDrawableChild(button2);
    }

}
