package org.wirez.bpmn.api;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.wirez.core.api.annotation.definitionset.Definition;
import org.wirez.core.api.definition.DefinitionSet;

@Portable
@Bindable
@org.wirez.core.api.annotation.definitionset.DefinitionSet
public class BPMNDefinitionSet implements DefinitionSet {

    public static final String ID = "bpmnDefSet";

    @Definition
    private BPMNGraph graph;
    
    @Definition
    private BPMNDiagram diagram;

    @Definition
    private StartNoneEvent startNoneEvent;

    @Definition
    private EndNoneEvent endNoneEvent;

    @Definition
    private Task task;

    @Definition
    private SequenceFlow sequenceFlow;

    @Definition
    private ParallelGateway parallelGateway;

    @Definition
    private EndTerminateEvent endTerminateEvent;

    @Definition
    private Lane lane;

    public BPMNDefinitionSet() {
    }

    public BPMNDefinitionSet(@MapsTo("graph") BPMNGraph graph,
                             @MapsTo("diagram") BPMNDiagram diagram,
                             @MapsTo("startNoneEvent") StartNoneEvent startNoneEvent,
                             @MapsTo("endNoneEvent") EndNoneEvent endNoneEvent,
                             @MapsTo("task") Task task,
                             @MapsTo("sequenceFlow") SequenceFlow sequenceFlow,
                             @MapsTo("parallelGateway") ParallelGateway parallelGateway,
                             @MapsTo("endTerminateEvent") EndTerminateEvent endTerminateEvent,
                             @MapsTo("lane") Lane lane) {
        this.graph = graph;
        this.diagram = diagram;
        this.startNoneEvent = startNoneEvent;
        this.endNoneEvent = endNoneEvent;
        this.task = task;
        this.sequenceFlow = sequenceFlow;
        this.parallelGateway = parallelGateway;
        this.endTerminateEvent = endTerminateEvent;
        this.lane = lane;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getDomain() {
        return "bpmn.org";
    }

    @Override
    public String getDescription() {
        return "THe BPMN definition set";
    }

    public BPMNGraph getGraph() {
        return graph;
    }

    public void setGraph(BPMNGraph graph) {
        this.graph = graph;
    }

    public BPMNDiagram getDiagram() {
        return diagram;
    }

    public StartNoneEvent getStartNoneEvent() {
        return startNoneEvent;
    }

    public Task getTask() {
        return task;
    }

    public void setDiagram(BPMNDiagram diagram) {
        this.diagram = diagram;
    }

    public void setStartNoneEvent(StartNoneEvent startNoneEvent) {
        this.startNoneEvent = startNoneEvent;
    }

    public EndNoneEvent getEndNoneEvent() {
        return endNoneEvent;
    }

    public void setEndNoneEvent(EndNoneEvent endNoneEvent) {
        this.endNoneEvent = endNoneEvent;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public SequenceFlow getSequenceFlow() {
        return sequenceFlow;
    }

    public void setSequenceFlow(SequenceFlow sequenceFlow) {
        this.sequenceFlow = sequenceFlow;
    }

    public ParallelGateway getParallelGateway() {
        return parallelGateway;
    }

    public void setParallelGateway(ParallelGateway parallelGateway) {
        this.parallelGateway = parallelGateway;
    }

    public EndTerminateEvent getEndTerminateEvent() {
        return endTerminateEvent;
    }

    public void setEndTerminateEvent(EndTerminateEvent endTerminateEvent) {
        this.endTerminateEvent = endTerminateEvent;
    }

    public Lane getLane() {
        return lane;
    }

    public void setLane(Lane lane) {
        this.lane = lane;
    }
}
