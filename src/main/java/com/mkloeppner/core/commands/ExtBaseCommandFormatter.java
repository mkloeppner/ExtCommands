package com.mkloeppner.core.commands;

import lombok.Getter;

/**
 * Created by martinkloeppner on 12/04/15.
 */
public class ExtBaseCommandFormatter {

    @Getter
    private ExtBaseCommand baseCommand;

    public ExtBaseCommandFormatter(ExtBaseCommand baseCommand) {
        this.baseCommand = baseCommand;
    }

    public String getCommandPath() {
        StringBuilder commandChain = new StringBuilder();
        ExtBaseCommand command = this.getBaseCommand();

        while (command.isRoot() == false) {
            commandChain.insert(0, " ");
            commandChain.insert(1, command.getName());
            command = command.getParent();
        }

        String commandPath = "/" + command.getName() + commandChain.toString();

        return commandPath;
    }

    public String getCommandDescriptionLine() {
        return this.getCommandPath();
    }

}
