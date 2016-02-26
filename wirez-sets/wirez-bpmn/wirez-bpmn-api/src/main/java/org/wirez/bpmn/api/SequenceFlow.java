/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

package org.wirez.bpmn.api;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.wirez.bpmn.api.property.general.BPMNGeneral;
import org.wirez.bpmn.api.property.general.BackgroundSet;
import org.wirez.core.api.annotation.definition.Definition;
import org.wirez.core.api.annotation.definition.PropertySet;
import org.wirez.core.api.annotation.rule.EdgeOccurrences;
import org.wirez.core.api.annotation.rule.EdgeType;
import org.wirez.core.api.annotation.rule.Occurrences;
import org.wirez.core.api.annotation.rule.PermittedConnection;
import org.wirez.core.api.definition.BaseDefinition;
import org.wirez.core.api.graph.Edge;

import java.util.HashSet;

@Portable
@Bindable
@Definition( type = Edge.class )
@PermittedConnection( startRole = "sequence_start", endRole = "sequence_end" )
@PermittedConnection( startRole = "choreography_sequence_start", endRole = "choreography_sequence_end" )
@PermittedConnection( startRole = "Exclusive_Eventbased_Gateway", endRole = "FromEventbasedGateway" )
@PermittedConnection( startRole = "EventbasedGateway", endRole = "FromEventbasedGateway" )
@Occurrences(
        role = "Startevents_all",
        min = 0,
        value = {
                @EdgeOccurrences(type = EdgeType.INCOMING, edge = "SequenceFlow", max = 0)
        }
)
@Occurrences(
        role = "Endevents_all",
        min = 0,
        value = {
                @EdgeOccurrences(type = EdgeType.OUTGOING, edge = "SequenceFlow", max = 0)
        }
)
public class SequenceFlow extends BaseDefinition implements BPMNDefinition {

    public static final String ID = "SequenceFlow";
    public static final String COLOR = "#000000";
    public static final String BORDER_COLOR = "#000000";
    public static final Double BORDER_SIZE = 3d;
    
    @PropertySet
    private BPMNGeneral general;

    @PropertySet
    private BackgroundSet backgroundSet;

    public SequenceFlow() {
        super("Connecting Objects", "Sequence Flow", "A Sequence Flow",
                new HashSet<String>(){{
                    add( "all" );
                    add( "ConnectingObjectsMorph" );
                }});
    }
    
    public SequenceFlow(@MapsTo("general") BPMNGeneral general,
                        @MapsTo("backgroundSet") BackgroundSet backgroundSet) {
        this();
        this.general = general;
        this.backgroundSet = backgroundSet;
    }

    public SequenceFlow buildDefaults() {
        getGeneral().getName().setValue("My sequence flow");
        getBackgroundSet().getBgColor().setValue(COLOR);
        getBackgroundSet().getBorderSize().setValue(BORDER_SIZE);
        getBackgroundSet().getBorderColor().setValue(BORDER_COLOR);
        return this;
    }
    
    @Override
    public String getId() {
        return ID;
    }

    public BPMNGeneral getGeneral() {
        return general;
    }

    public BackgroundSet getBackgroundSet() {
        return backgroundSet;
    }

    public void setGeneral(BPMNGeneral general) {
        this.general = general;
    }

    public void setBackgroundSet(BackgroundSet backgroundSet) {
        this.backgroundSet = backgroundSet;
    }

}
