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

    @Getter @Setter
    private boolean optional;


    public ExtCommandParam(String name) {
        this(name, false);
    }

    public ExtCommandParam(String name, boolean optional) {
        this(name, optional, null);
    }

    public ExtCommandParam(String name, boolean optional, String value) {
        this.name = name;
        this.optional = optional;
        this.value = value;
    }

}
