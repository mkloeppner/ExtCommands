package com.mkloeppner.core.commands;

import com.mkloeppner.core.commands.bungee.ExtBungeeCommand;
import lombok.Getter;
import lombok.Setter;
import java.lang.String;

/**
 * Created by martinkloeppner on 12/04/15.
 */
public class ExtBaseCommand {

    @Getter
    private ExtPluginCommand dependencyInjectionContainer;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private ExtBaseCommandFormatter formatter;

    @Getter @Setter
    private ExtBaseCommand parent;

    @Getter @Setter
    private ExtCommandSender commandSender;

    @Getter
    private String[] rawParameters;

    public ExtBaseCommand(String name) {
        this.name = name;
        this.parent = null;
        this.formatter = new ExtBaseCommandFormatter(this);
    }

    public void execute(ExtCommandSender commandSender, String[] strings) {
        this.commandSender = commandSender;
        this.rawParameters = strings;
        this.executeCommand();
    }

    public void executeCommand() {
    }

    public boolean isRoot() {
        return this.parent == null;
    }

    public String getCompleteCommand() {
        return this.getFormatter().getCommandPath();
    }

    public void sendMessage(String message) {
        this.getCommandSender().sendMessage(this.getDependencyInjectionContainer().createTextComponent(message));
    }

    public void setDependencyInjectionContainer(ExtPluginCommand dependencyInjectionContainer) {
        this.dependencyInjectionContainer = dependencyInjectionContainer;
    }
}
