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
package org.wirez.core.api.graph.command.impl;

import org.uberfire.commons.validation.PortablePreconditions;
import org.wirez.core.api.command.CommandResult;
import org.wirez.core.api.graph.Edge;
import org.wirez.core.api.graph.Node;
import org.wirez.core.api.graph.command.factory.GraphCommandFactory;
import org.wirez.core.api.graph.command.GraphCommandResult;
import org.wirez.core.api.graph.content.ConnectionContent;
import org.wirez.core.api.graph.content.ViewContent;
import org.wirez.core.api.rule.RuleManager;
import org.wirez.core.api.rule.RuleViolation;

import java.util.Collection;
import java.util.LinkedList;

/**
 * A Command to set the incoming connection for a DefaultEdge in a Graph.
 */
public class SetConnectionTargetNodeCommand extends AbstractGraphCommand {

    private Node<? extends ViewContent<?>, Edge> targetNode;
    private Node<? extends ViewContent<?>, Edge> lastTargetNode;
    private Node<? extends ViewContent<?>, Edge> sourceNode;
    private Edge<? extends ViewContent<?>, Node> edge;
    private int magnetIndex;

    public SetConnectionTargetNodeCommand(final GraphCommandFactory commandFactory,
                                          final Node<? extends ViewContent<?>, Edge> targetNode,
                                          final Edge<? extends ViewContent<?>, Node> edge,
                                          final int magnetIndex) {
        super(commandFactory);
        this.edge = PortablePreconditions.checkNotNull( "edge",
                edge );;
        this.targetNode = PortablePreconditions.checkNotNull( "targetNode",
                targetNode);;
        this.lastTargetNode = edge.getTargetNode();
        this.sourceNode = edge.getSourceNode();
        this.magnetIndex = magnetIndex;
    }
    
    @Override
    public CommandResult<RuleViolation> allow(final RuleManager ruleManager) {
        return check(ruleManager);
    }

    @Override
    public CommandResult<RuleViolation> execute(final RuleManager ruleManager) {
        final CommandResult<RuleViolation> results = check(ruleManager);
        if ( !results.getType().equals(CommandResult.Type.ERROR) ) {
            if (lastTargetNode != null) {
                lastTargetNode.getInEdges().remove( edge );
            }
            targetNode.getInEdges().add( edge );
            edge.setTargetNode( targetNode );
            ConnectionContent connectionContent = (ConnectionContent) edge.getContent();
            connectionContent.setTargetMagnetIndex(magnetIndex);
        }
        return results;
    }
    
    private CommandResult<RuleViolation> check(final RuleManager ruleManager) {
        final Collection<RuleViolation> connectionRuleViolations = (Collection<RuleViolation>) ruleManager.checkConnectionRules(sourceNode, targetNode, edge).violations();
        final Collection<RuleViolation> cardinalityRuleViolations = (Collection<RuleViolation>) ruleManager.checkCardinality(sourceNode, targetNode, edge, RuleManager.Operation.ADD).violations();
        final Collection<RuleViolation> violations = new LinkedList<RuleViolation>();
        violations.addAll(connectionRuleViolations);
        violations.addAll(cardinalityRuleViolations);

        return new GraphCommandResult(violations);        
    }

    @Override
    public CommandResult<RuleViolation> undo(RuleManager ruleManager) {
        throw new UnsupportedOperationException("Undo for this command not supported yet.");
    }

    @Override
    public String toString() {
        return "SetConnectionTargetNodeCommand [edge=" + edge.getUUID() + ", candidate=" + targetNode.getUUID() + ", magnet=" + magnetIndex + "]";
    }
    
}