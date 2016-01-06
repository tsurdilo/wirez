/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wirez.core.api.graph.commands;

import org.uberfire.commons.validation.PortablePreconditions;
import org.wirez.core.api.command.Command;
import org.wirez.core.api.command.CommandResult;
import org.wirez.core.api.command.DefaultCommandResult;
import org.wirez.core.api.definition.property.HasDefaultValue;
import org.wirez.core.api.definition.property.Property;
import org.wirez.core.api.graph.Element;
import org.wirez.core.api.rule.RuleManager;
import org.wirez.core.api.rule.RuleViolation;

import java.util.ArrayList;

/**
 * A Command to update an element's property.
 */
public class UpdateElementPropertyValueCommand implements Command {

    private Element element;
    private String propertyId;
    private Object value;


    public UpdateElementPropertyValueCommand(final Element element,
                                             final String propertyId,
                                             final Object value) {
        this.element = PortablePreconditions.checkNotNull( "element",
                element );;
        this.propertyId = PortablePreconditions.checkNotNull( "propertyId",
                propertyId );
        this.value = PortablePreconditions.checkNotNull( "value",
                value );
    }
    
    @Override
    public CommandResult allow(final RuleManager ruleManager) {
        // TODO: Check value.
        return new DefaultCommandResult(new ArrayList<RuleViolation>());
    }

    @Override
    public CommandResult execute(final RuleManager ruleManager) {
        element.getProperties().put(propertyId, value);
        return new DefaultCommandResult(new ArrayList<RuleViolation>());
    }
    
    @Override
    public CommandResult undo(RuleManager ruleManager) {
        // TODO
        return new DefaultCommandResult(new ArrayList<RuleViolation>());
    }

    @Override
    public String toString() {
        return "UpdateElementPropertyValueCommand [element=" + element.getUUID() + ", property=" + propertyId + ", value=" + value + "]";
    }
    
}
