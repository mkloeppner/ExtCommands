package com.mkloeppner.core.commands;

/**
 * Created by martinkloeppner on 14/04/15.
 */
public interface ExtCommandSender<T extends ExtCommandTextComponent> {

    public void sendMessage(T textComponent);

}
