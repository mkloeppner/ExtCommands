package com.mkloeppner.core.commands;

import com.mkloeppner.core.exception.InvalidParameterCountException;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinkloeppner on 12/04/15.
 */
public class ExtCommand extends ExtBaseCommand {

    @Getter @Setter
    private ExtCommandDelegate delegate;

    @Getter
    private List<ExtCommandParam> parameters;

    public ExtCommand(String name, ExtCommandDelegate delegate) {
        super(name);
        this.delegate = delegate;
        this.parameters = new ArrayList<ExtCommandParam>();
        this.setFormatter(new ExtCommandFormatter(this));
    }

    public void addParameter(ExtCommandParam param) { this.parameters.add(param); }
    public void addParameterAt(ExtCommandParam param, Integer position) { this.parameters.add(position, param); }
    public void removeParameter(ExtCommandParam param) { this.parameters.remove(param); }

    @Override
    public void executeCommand() {
        try {
            this.readParameters();
            this.performCommand();
        } catch (InvalidParameterCountException ex) {
            this.sendMessage(this.getFormatter().getCommandDescriptionLine());
        }
    }

    public void readParameters() throws InvalidParameterCountException {

        if (this.getRawParameters().length != this.getParameters().size()) {
            throw new InvalidParameterCountException(this);
        }

        for (int i = 0; i < this.getRawParameters().length; i++) {
            String value = this.getRawParameters()[i];
            ExtCommandParam param = this.getParameters().get(i);
            param.setValue(value);
        }
    }

    public void performCommand() {
        this.delegate.executeCommand(this, this.getParameters());
    }



}
