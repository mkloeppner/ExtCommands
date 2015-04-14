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

        ExtCommand helpCmd = new ExtCommand("help", this);
        helpCmd.addParameter(new ExtCommandParam("page"));

        this.commands.add(helpCmd);
        getProxy().getPluginManager().registerCommand(this, new ExtBungeeCommand(helpCmd));
    }

    @Override
    public void executeCommand(ExtCommand command, List<ExtCommandParam> parameters) {
        switch (command.getCompleteCommand()) {
            case "/help":
                Integer pageSize = 10;
                if (command.getParameters().size() == 0) {
                    listCommands(command, 0, pageSize);
                } else {
                    Integer page = Integer.parseInt(command.getParameters().get(0).getValue());
                    listCommands(command, page * pageSize, pageSize);
                }
                break;
        }
    }

    private void listCommands(ExtCommand command, Integer offset, Integer size) {
        List<ExtBaseCommand> allCommands = getAllCommands();
        for (int i = offset; offset < offset + size && i < allCommands.size(); i++) {
            ExtBaseCommand baseCommand = allCommands.get(i);
            if (baseCommand instanceof ExtGroupCommand) {
                ExtGroupCommandFormatter cmdFormatter = ((ExtGroupCommand) baseCommand).getGroupCommandFormatter();
                command.sendMessage(cmdFormatter.getSubcommandsList());
            } else {
                command.sendMessage(command.getFormatter().getCommandDescriptionLine());
            }
        }
    }

    private List<ExtBaseCommand> getAllCommands() {
        List<ExtBaseCommand> allCommands = new ArrayList<>();
        for (Plugin plugin : getProxy().getPluginManager().getPlugins()) {
            if (plugin instanceof ExtCommandsHelpable) {
                ExtCommandsHelpable helpable = (ExtCommandsHelpable)plugin;
                for (ExtBaseCommand baseCommand : helpable.getAvailableCommands()) {
                    allCommands.add(baseCommand);
                }
            }
        }
        return allCommands;
    }

    @Override
    public List<ExtBaseCommand> getAvailableCommands() {
        return this.commands;
    }
}