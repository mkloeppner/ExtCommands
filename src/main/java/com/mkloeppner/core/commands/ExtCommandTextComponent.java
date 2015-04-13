package com.mkloeppner.core.commands;

import lombok.Getter;

/**
 * Created by martinkloeppner on 14/04/15.
 */
public class ExtCommandTextComponent<T> {

    @Getter
    private T textComponent;

    public ExtCommandTextComponent(T textComponent) {
        this.textComponent = textComponent;
    }

}
