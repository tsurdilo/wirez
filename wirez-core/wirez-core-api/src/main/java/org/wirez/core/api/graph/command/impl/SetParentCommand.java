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
import org.wirez.core.api.graph.command.GraphCommandFactory;
import org.wirez.core.api.graph.command.GraphCommandResult;
import org.wirez.core.api.graph.content.ParentChildRelationship;
import org.wirez.core.api.rule.RuleManager;
import org.wirez.core.api.rule.RuleViolation;

import java.util.Collection;
import java.util.LinkedList;

/**
 * A Command to set a DefaultNode children of another container node.
 */
public class SetParentCommand extends AbstractGraphCommand {

    private Node parent;
    private Node candidate;
    private Edge<ParentChildRelationship, Node> edge;

    public SetParentCommand(final GraphCommandFactory commandFactory,
                            final Node parent,
                            final Node candidate,
                            final Edge<ParentChildRelationship, Node> edge) {
        super(commandFactory);
        this.parent = PortablePreconditions.checkNotNull( "parent",
                parent );
        this.candidate = PortablePreconditions.checkNotNull( "candidate",
                                                             candidate );
        this.edge = PortablePreconditions.checkNotNull( "edge",
                edge );
    }
    
    @Override
    public CommandResult<RuleViolation> allow(final RuleManager ruleManager) {
        return check(ruleManager);
    }

    @Override
    public CommandResult<RuleViolation> execute(final RuleManager ruleManager) {
        final CommandResult<RuleViolation> results = check(ruleManager);
        if ( !results.getType().equals( CommandResult.Type.ERROR ) ) {
            
            final Node oldParent = edge.getSourceNode();
            if ( null != oldParent ) {
                oldParent.getOutEdges().remove( edge );
            }

            final Node oldChild = edge.getTargetNode();
            if ( null != oldChild ) {
                oldChild.getInEdges().remove( edge );
            }
            
            edge.setSourceNode(parent);
            edge.setTargetNode(candidate);
            parent.getOutEdges().add( edge );
            candidate.getInEdges().add( edge );
            
        }
        return results;
    }
    
    private CommandResult<RuleViolation> check(final RuleManager ruleManager) {
        final Collection<RuleViolation> containmentRuleViolations = (Collection<RuleViolation>) ruleManager.checkContainment( parent, candidate).violations();
        final Collection<RuleViolation> violations = new LinkedList<RuleViolation>();
        violations.addAll(containmentRuleViolations);
        return new GraphCommandResult(violations);
    }

    @Override
    public CommandResult<RuleViolation> undo(RuleManager ruleManager) {
        // TODO
        return null;
    }

    @Override
    public String toString() {
        return "SetParentCommand [parent=" + parent.getUUID() + ", candidate=" + candidate.getUUID() + "]";
    }
}
