package com.mkloeppner.core.commands;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by martinkloeppner on 12/04/15.
 */
public class ExtCommandParam {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String value;


    public ExtCommandParam(String name) {
        this(name, null);
    }

    public ExtCommandParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
