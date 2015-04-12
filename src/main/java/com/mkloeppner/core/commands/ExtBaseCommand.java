package com.mkloeppner.core.commands;

import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import java.lang.Override;
import java.lang.String;

/**
 * Created by martinkloeppner on 12/04/15.
 */
public class ExtBaseCommand extends Command {

    @Getter @Setter
    private ExtBaseCommandFormatter formatter;

    @Getter @Setter
    private ExtBaseCommand parent;

    @Getter
    private CommandSender commandSender;

    @Getter
    private String[] rawParameters;

    public ExtBaseCommand(String name) {
        super(name);
        this.parent = null;
        this.formatter = new ExtBaseCommandFormatter(this);
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
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
}
