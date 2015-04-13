package com.mkloeppner;

import com.mkloeppner.core.commands.ExtCommand;
import com.mkloeppner.core.commands.ExtCommandDelegate;
import com.mkloeppner.core.commands.ExtCommandParam;
import com.mkloeppner.core.commands.ExtGroupCommand;
import com.mkloeppner.core.commands.bungee.ExtBungeeCommand;
import com.mkloeppner.core.commands.bungee.ExtBungeeCommandTextComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.List;

public class ExtCommands extends Plugin implements ExtCommandDelegate {

    @Override
    public void onLoad() {
        super.onLoad();

        ExtGroupCommand clanCmd = new ExtGroupCommand("ext");

        ExtCommand inviteCmd = new ExtCommand("cmds", this);
        inviteCmd.addParameter(new ExtCommandParam("player"));
        clanCmd.addSubcommand(inviteCmd);

        ExtBungeeCommand asBungeeCommand = new ExtBungeeCommand(clanCmd);

        getProxy().getPluginManager().registerCommand(this, asBungeeCommand);

    }

    @Override
    public void executeCommand(ExtCommand command, List<ExtCommandParam> parameters) {
        command.getCommandSender().sendMessage(new ExtBungeeCommandTextComponent("test"));
    }
}