package com.mkloeppner;

import com.mkloeppner.core.commands.ExtCommand;
import com.mkloeppner.core.commands.ExtCommandParam;
import com.mkloeppner.core.commands.ExtGroupCommand;
import com.mkloeppner.core.commands.bungee.ExtBungeeCommand;
import net.md_5.bungee.api.plugin.Plugin;

public class ExtCommands extends Plugin {

    @Override
    public void onLoad() {
        super.onLoad();

        ExtGroupCommand clanCmd = new ExtGroupCommand("clan");

        ExtCommand inviteCmd = new ExtCommand("invite", null);
        inviteCmd.addParameter(new ExtCommandParam("player"));

        ExtBungeeCommand asBungeeCommand = new ExtBungeeCommand(inviteCmd);

        getProxy().getPluginManager().registerCommand(this, asBungeeCommand);

    }
}