package org.wirez.core.api.graph.processing.traverse.content;

import org.wirez.core.api.graph.Edge;
import org.wirez.core.api.graph.Node;
import org.wirez.core.api.graph.content.view.View;
import org.wirez.core.api.graph.processing.traverse.tree.TreeWalkTraverseProcessor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public final class ViewTraverseProcessorImpl extends AbstractContentTraverseProcessor<View<?>, Node<View, Edge>, Edge<View<?>, Node>>
        implements ViewTraverseProcessor {

    @Inject
    public ViewTraverseProcessorImpl(final TreeWalkTraverseProcessor treeWalkTraverseProcessor) {
        super(treeWalkTraverseProcessor);
    }

    @Override
    protected TreeWalkTraverseProcessor.TraversePolicy getPolicy() {
        return TreeWalkTraverseProcessor.TraversePolicy.VISIT_EDGE_BEFORE_TARGET_NODE;
    }

    @Override
    protected boolean doStartEdgeTraversal(final Edge edge) {
        return edge.getContent() instanceof View ;
    }

    @Override
    protected boolean doEndEdgeTraversal(final Edge edge) {
        return edge.getContent() instanceof View;
    }

}