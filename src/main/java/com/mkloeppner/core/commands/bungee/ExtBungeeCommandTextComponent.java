package com.mkloeppner.core.commands.bungee;

import com.mkloeppner.core.commands.ExtCommandTextComponent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Created by martinkloeppner on 14/04/15.
 */
public class ExtBungeeCommandTextComponent extends ExtCommandTextComponent<TextComponent> {

    public ExtBungeeCommandTextComponent(String message) {
        super(new TextComponent(message));
    }

}
