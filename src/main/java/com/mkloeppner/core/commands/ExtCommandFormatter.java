package com.mkloeppner.core.commands;

/**
 * Created by martinkloeppner on 12/04/15.
 */
public class ExtCommandFormatter extends ExtBaseCommandFormatter {

    public ExtCommandFormatter(ExtCommand command) {
        super(command);
    }

    @Override
    public String getCommandDescriptionLine() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(this.getCommandPath());

        for (ExtCommandParam param : this.getCommand().getParameters()) {
            stringBuilder.append(" ");
            stringBuilder.append(String.format("<%s>", param.getName()));
        }

        return stringBuilder.toString();
    }

    public ExtCommand getCommand() {
        return (ExtCommand)this.getBaseCommand();
    }

}
