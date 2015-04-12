package com.mkloeppner.core.commands;

import java.util.List;

/**
 * Created by martinkloeppner on 13/04/15.
 */
public interface ExtCommandDelegate {

    public void executeCommand(ExtCommand command, List<ExtCommandParam> parameters);

}
