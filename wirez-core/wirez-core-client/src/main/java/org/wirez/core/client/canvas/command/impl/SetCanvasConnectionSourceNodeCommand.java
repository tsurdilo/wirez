package org.wirez.core.client.canvas.command.impl;

import org.wirez.core.api.command.Command;
import org.wirez.core.api.command.CommandResult;
import org.wirez.core.api.graph.Edge;
import org.wirez.core.api.graph.Node;
import org.wirez.core.api.graph.command.GraphCommandExecutionContext;
import org.wirez.core.api.graph.content.view.View;
import org.wirez.core.api.rule.RuleViolation;
import org.wirez.core.client.canvas.AbstractCanvasHandler;
import org.wirez.core.client.canvas.command.AbstractCanvasGraphCommand;
import org.wirez.core.client.canvas.command.CanvasViolation;
import org.wirez.core.client.impl.BaseConnector;

public final class SetCanvasConnectionSourceNodeCommand extends AbstractCanvasGraphCommand {


    Node<? extends View<?>, Edge> node;
    Edge<? extends View<?>, Node> edge;
    int magnetIndex;
            
    public SetCanvasConnectionSourceNodeCommand(final Node<? extends View<?>, Edge> node,
                                                final Edge<? extends View<?>, Node> edge,
                                                final int magnetIndex) {
        this.node = node;
        this.edge = edge;
        this.magnetIndex = magnetIndex;
    }

    @Override
    protected Command<GraphCommandExecutionContext, RuleViolation> buildGraphCommand(final AbstractCanvasHandler context) {
        return context.getGraphCommandFactory().SET_SOURCE_NODE( node, edge, magnetIndex );
    }

    @Override
    public CommandResult<CanvasViolation> execute(final AbstractCanvasHandler context) {
        final String uuid = edge.getUUID();
        BaseConnector connector = (BaseConnector) context.getCanvas().getShape(uuid);
        connector.applyConnections( edge, context );
        return buildResult();
    }

    @Override
    public CommandResult<CanvasViolation> undo(final AbstractCanvasHandler context) {
        return execute( context );
    }
}
