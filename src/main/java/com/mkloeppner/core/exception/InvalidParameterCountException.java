package com.mkloeppner.core.exception;

import com.mkloeppner.core.commands.ExtBaseCommand;

/**
 * Created by martinkloeppner on 12/04/15.
 */
public class InvalidParameterCountException extends UserException {

    private ExtBaseCommand command;

    public InvalidParameterCountException(ExtBaseCommand command) {
        this.command = command;
    }

}
