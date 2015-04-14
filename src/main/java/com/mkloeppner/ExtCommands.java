package com.mkloeppner;

import com.mkloeppner.core.commands.*;
import com.mkloeppner.core.commands.bungee.ExtBungeeCommand;
import com.mkloeppner.core.commands.bungee.ExtBungeeCommandTextComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ExtCommands extends Plugin implements ExtCommandDelegate, ExtCommandsHelpable {

    private List<ExtBaseCommand> commands;

    @Override
    public void onLoad() {
        super.onLoad();

        this.commands = new ArrayList<>();

        ExtGroupCommand clanCmd = new ExtGroupCommand("ext");

        ExtCommand inviteCmd = new ExtCommand("help", this);
        clanCmd.addSubcommand(inviteCmd);

        this.commands.add(clanCmd);
        getProxy().getPluginManager().registerCommand(this, new ExtBungeeCommand(clanCmd));
    }

    @Override
    public void executeCommand(ExtCommand command, List<ExtCommandParam> parameters) {
        for (Plugin plugin : getProxy().getPluginManager().getPlugins()) {
            if (plugin instanceof ExtCommandsHelpable) {
                ExtCommandsHelpable helpable = (ExtCommandsHelpable)plugin;
                for (ExtBaseCommand baseCommand : helpable.getAvailableCommands()) {
                    if (baseCommand instanceof ExtGroupCommand) {
                        ExtGroupCommandFormatter cmdFormatter = ((ExtGroupCommand) baseCommand).getGroupCommandFormatter();
                        command.getCommandSender().sendMessage(new ExtBungeeCommandTextComponent(cmdFormatter.getSubcommandsList()));
                    } else {
                        command.getCommandSender().sendMessage(new ExtBungeeCommandTextComponent(command.getFormatter().getCommandDescriptionLine()));
                    }
                }
            }
        }
    }

    @Override
    public List<ExtBaseCommand> getAvailableCommands() {
        return this.commands;
    }
}