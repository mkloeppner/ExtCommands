package com.mkloeppner.core.commands;

/**
 * Created by martinkloeppner on 12/04/15.
 */
public class ExtGroupCommandFormatter extends ExtBaseCommandFormatter {

    public ExtGroupCommandFormatter(ExtGroupCommand groupCommand) {
        super(groupCommand);
    }

    public String getSubcommandsList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.getGroupCommand().getSubcommands().size(); i++) {
            if (i != 0) {
                stringBuilder.append(System.getProperty("line.separator"));
            }

            ExtBaseCommand subcommmand = this.getGroupCommand().getSubcommands().get(i);
            if (subcommmand instanceof ExtGroupCommand) {
                ExtGroupCommandFormatter subGroupdCommandFormatter = ((ExtGroupCommand) subcommmand).getGroupCommandFormatter();
                stringBuilder.append(subGroupdCommandFormatter.getSubcommandsList());
            } else {
                stringBuilder.append(subcommmand.getFormatter().getCommandDescriptionLine());
            }
        }
        return stringBuilder.toString();
    }

    public ExtGroupCommand getGroupCommand() {
        return (ExtGroupCommand)this.getBaseCommand();
    }
}
