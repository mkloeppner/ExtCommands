package com.mkloeppner.core.commands.bungee;

import com.mkloeppner.core.commands.ExtBaseCommand;
import com.mkloeppner.core.commands.ExtCommand;
import com.mkloeppner.core.commands.ExtCommandTextComponent;
import com.mkloeppner.core.commands.ExtPluginCommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by martinkloeppner on 14/04/15.
 */
public class ExtBungeeCommand<T extends ExtBaseCommand> extends Command implements ExtPluginCommand {

    private T command;

    public ExtBungeeCommand(T command) {
        super(command.getName());
        this.command = command;
        this.command.setDependencyInjectionContainer(this);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        this.command.execute(new ExtBungeeCommandSender(commandSender), strings);
    }

    @Override
    public ExtCommandTextComponent createTextComponent(String message) {
        return new ExtBungeeCommandTextComponent(new TextComponent(message));
    }
}
