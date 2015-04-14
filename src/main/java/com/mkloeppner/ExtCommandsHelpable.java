package com.mkloeppner;

import com.mkloeppner.core.commands.ExtBaseCommand;
import com.mkloeppner.core.commands.ExtCommand;

import java.util.List;

/**
 * Created by martinkloeppner on 14/04/15.
 */
public interface ExtCommandsHelpable {

    public List<ExtBaseCommand> getAvailableCommands();

}
