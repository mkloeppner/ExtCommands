package com.mkloeppner.core.commands.bungee;

import com.mkloeppner.core.commands.ExtCommandSender;
import net.md_5.bungee.api.CommandSender;

/**
 * Created by martinkloeppner on 14/04/15.
 */
public class ExtBungeeCommandSender implements ExtCommandSender<ExtBungeeCommandTextComponent> {

    private CommandSender commandSender;

    public ExtBungeeCommandSender(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public void sendMessage(ExtBungeeCommandTextComponent textComponent) {
        this.commandSender.sendMessage(textComponent.getTextComponent());
    }

}
