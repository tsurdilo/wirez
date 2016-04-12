package org.wirez.core.client.canvas.command.impl;

import org.wirez.core.api.command.Command;
import org.wirez.core.api.command.CommandResult;
import org.wirez.core.api.graph.Edge;
import org.wirez.core.api.graph.Graph;
import org.wirez.core.api.graph.Node;
import org.wirez.core.api.graph.command.GraphCommandExecutionContext;
import org.wirez.core.api.graph.content.view.View;
import org.wirez.core.api.graph.processing.index.IncrementalIndexBuilder;
import org.wirez.core.api.graph.processing.index.Index;
import org.wirez.core.api.graph.processing.index.IndexBuilder;
import org.wirez.core.api.rule.RuleViolation;
import org.wirez.core.client.canvas.AbstractCanvasHandler;
import org.wirez.core.client.canvas.command.CanvasViolation;
import org.wirez.core.client.factory.ShapeFactory;
import org.wirez.core.client.impl.BaseConnector;

public final class AddCanvasEdgeCommand extends AddCanvasElementCommand<Edge>  {
    
    Node parent;
    
    public AddCanvasEdgeCommand(final Node parent, final Edge candidate, final ShapeFactory factory) {
        super(candidate, factory);
        this.parent = parent;
    }

    @Override
    protected void doRegister(final AbstractCanvasHandler context) {
        super.doRegister(context);
        final IndexBuilder<Graph<?, Node>, Node, Edge, Index<Node, Edge>> indexBuilder = context.getIndexBuilder();
        if ( indexBuilder instanceof IncrementalIndexBuilder) {
            ((IncrementalIndexBuilder) indexBuilder).addEdge(context.getGraphIndex(), candidate);
        }
    }

    @Override
    public CommandResult<CanvasViolation> execute(final AbstractCanvasHandler context) {
        CommandResult<CanvasViolation> result = super.execute(context);
        final String uuid = candidate.getUUID();
        BaseConnector connector = (BaseConnector) context.getCanvas().getShape(uuid);
        connector.applyConnections((Edge<View, Node>) candidate, context);
        return result;
    }

    @Override
    public CommandResult<CanvasViolation> undo(final AbstractCanvasHandler context) {
        return context.getCommandFactory().DELETE_EDGE( candidate ).execute( context );
    }


    @Override
    protected Command<GraphCommandExecutionContext, RuleViolation> buildGraphCommand(final AbstractCanvasHandler context) {
        return context.getGraphCommandFactory().ADD_EDGE(parent, candidate);
    }
    
}
