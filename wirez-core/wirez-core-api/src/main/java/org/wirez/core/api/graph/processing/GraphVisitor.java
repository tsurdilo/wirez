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

package org.wirez.core.api.graph.processing;

import org.wirez.core.api.graph.Bounds;
import org.wirez.core.api.graph.Element;
import org.wirez.core.api.graph.impl.DefaultEdge;
import org.wirez.core.api.graph.impl.DefaultGraph;
import org.wirez.core.api.graph.impl.DefaultNode;

import java.util.*;

public class GraphVisitor {
    
    public interface Visitor {
        
        void visitGraph(DefaultGraph graph);

        void visitNode(DefaultNode node);

        void visitEdge(DefaultEdge edge);
        
        void visitUnconnectedEdge(DefaultEdge edge);
        
        void end();
        
    }

    public interface BoundsVisitor {
        void visitBounds(Element element, Bounds.Bound ul, Bounds.Bound lr);
    }
    
    public interface PropertyVisitor {
        void visitProperty(Element element, String key, Object value);
    }
    
    public enum VisitorPolicy {
        EDGE_FIRST, EDGE_LAST;
    }
    
    private DefaultGraph graph;
    private Visitor visitor;
    private VisitorPolicy policy;
    private BoundsVisitor boundsVisitor = null;
    private PropertyVisitor propertyVisitor = null;
    private Set<String> processesEdges;

    public GraphVisitor(final DefaultGraph graph,
                        final Visitor visitor,
                        final VisitorPolicy policy) {
        this.graph = graph;
        this.visitor = visitor;
        this.policy = policy;
        this.processesEdges = new HashSet<String>();
    }
    
    public void run() {
        visitGraph();
        visitUnconnectedEdges();
        visitor.end();
    }

    public GraphVisitor setBoundsVisitor(BoundsVisitor boundsVisitor) {
        this.boundsVisitor = boundsVisitor;
        return this;
    }

    public GraphVisitor setPropertyVisitor(PropertyVisitor propertyVisitor) {
        this.propertyVisitor = propertyVisitor;
        return this;
    }

    private void visitUnconnectedEdges() {
        Iterable<DefaultEdge> edges = graph.edges();
        Iterator<DefaultEdge> edgesIt = edges.iterator();
        while (edgesIt.hasNext()) {
            DefaultEdge edge = edgesIt.next();
            if (!this.processesEdges.contains(edge.getUUID())) {
                visitor.visitUnconnectedEdge(edge);
            }
        }
    }


    private void visitGraph() {
        assert graph != null && visitor != null;
        
        visitor.visitGraph(graph);
        visitProperties(graph);
        visitBounds(graph)
        ;
        Collection<DefaultNode> startingNodes = getStartingNodes(graph);
        if (!startingNodes.isEmpty()) {
            for (DefaultNode node : startingNodes) {
                visitNode(node);
            }
        }
    }

    private void visitNode(final DefaultNode graphNode) {
        visitor.visitNode(graphNode);
        visitProperties(graphNode);
        visitBounds(graphNode);
                
        List<DefaultEdge> outEdges = graphNode.getOutEdges();
        if (outEdges != null && !outEdges.isEmpty()) {
            for (DefaultEdge edge : outEdges) {
                visitEdge(edge);
            }
        }
    }

    private void visitEdge(final DefaultEdge edge) {
        processesEdges.add(edge.getUUID());
        if (VisitorPolicy.EDGE_FIRST.equals(policy)) {
            visitor.visitEdge(edge);
            visitProperties(edge);
            visitBounds(edge);
        }
        final DefaultNode outNode = (DefaultNode) edge.getTargetNode();
        if (outNode != null) {
            visitNode(outNode);
        }
        if (VisitorPolicy.EDGE_LAST.equals(policy)) {
            visitor.visitEdge(edge);
            visitProperties(edge);
            visitBounds(edge);
        }
    }

    private Collection<DefaultNode> getStartingNodes(final DefaultGraph graph) {
        final Collection<DefaultNode> result = new LinkedList<DefaultNode>();

        final Iterator<DefaultNode> nodesIt = graph.nodes().iterator();
        while (nodesIt.hasNext()) {
            final DefaultNode node = nodesIt.next();
            if (node.getInEdges() == null || !node.getInEdges().iterator().hasNext()) {
                result.add(node);
            }
        }

        return result;
    }
    
    private void visitProperties(final Element element) {
        if (element != null && propertyVisitor != null) {
            final Map<String, Object> properties = element.getProperties();
            if (properties != null) {
                for (final Map.Entry<String, Object> entry : properties.entrySet()) {
                    propertyVisitor.visitProperty(element, entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private void visitBounds(final Element element) {
        if (element != null && boundsVisitor != null) {
            final Bounds bounds = element.getBounds();
            if (bounds != null) {
                boundsVisitor.visitBounds(element, bounds.getUpperLeft(), bounds.getLowerRight());
            }
        }
    }
    
}