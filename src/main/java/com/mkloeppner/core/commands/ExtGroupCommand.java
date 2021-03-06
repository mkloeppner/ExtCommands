package com.mkloeppner.core.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Martin on 11.04.2015.
 */
public class ExtGroupCommand extends ExtBaseCommand {

    private HashMap<String, ExtBaseCommand> subCommands;

    public ExtGroupCommand(String commandName) {
        super(commandName);
        this.subCommands = new HashMap<String, ExtBaseCommand>();
        this.setFormatter(new ExtGroupCommandFormatter(this));
    }

    public void addSubcommand(ExtBaseCommand subCommand) {
        subCommand.setParent(this);
        subCommand.setDependencyInjectionContainer(this.getDependencyInjectionContainer());
        this.subCommands.put(subCommand.getName(), subCommand);
    }
    public void removeSubcommand(ExtBaseCommand subCommand) {
        subCommand.setParent(null);
        subCommand.setDependencyInjectionContainer(null);
        this.subCommands.remove(subCommand.getName());
    }

    @Override
    public void executeCommand() {

        if (this.getRawParameters().length < 1) {
            this.sendMessage(this.getGroupCommandFormatter().getSubcommandsList());
            return;
        }

        String currentParam = this.getCurrentCommandParameter();
        String[] leftParams = this.getLeftCommandParameters();

        ExtBaseCommand command = this.subcommandForParameter(currentParam);

        if (command == null && containsSubcommands()) {
            this.sendMessage("Command not available in command group");
        } else if (command != null) {
            command.execute(getCommandSender(), leftParams);
        } else {
            this.sendMessage("Command does not contain subcommands");
        }

    }

    private String getCurrentCommandParameter() {
        return this.getRawParameters()[0];
    }

    private String[] getLeftCommandParameters() {
        String[] leftParams = new String[getRawParameters().length - 1];
        System.arraycopy(getRawParameters(), 1, leftParams, 0, leftParams.length);
        return leftParams;
    }

    private ExtBaseCommand subcommandForParameter(String param) {
        return this.subCommands.get(param);
    }

    private boolean containsSubcommands() {
        return this.subCommands.size() > 0;
    }

    public List<ExtBaseCommand> getSubcommands() {
        ArrayList<ExtBaseCommand> commands = new ArrayList<ExtBaseCommand>();
        for (ExtBaseCommand command : this.subCommands.values()) {
            commands.add(command);
        }
        return commands;
    }

    public ExtGroupCommandFormatter getGroupCommandFormatter() {
        return (ExtGroupCommandFormatter)this.getFormatter();
    }

    public void setDependencyInjectionContainer(ExtPluginCommand dependencyInjectionContainer) {
        super.setDependencyInjectionContainer(dependencyInjectionContainer);

        for (ExtBaseCommand command : this.getSubcommands()) {
            command.setDependencyInjectionContainer(this.getDependencyInjectionContainer());
        }
    }

}
