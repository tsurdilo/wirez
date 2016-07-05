package org.wirez.client.widgets.explorer.tree;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.uberfire.client.mvp.UberView;
import org.wirez.core.api.DefinitionManager;
import org.wirez.core.client.canvas.event.AbstractCanvasHandlerEvent;
import org.wirez.core.client.canvas.event.registration.CanvasElementAddedEvent;
import org.wirez.core.client.canvas.event.registration.CanvasElementRemovedEvent;
import org.wirez.core.client.canvas.event.registration.CanvasElementUpdatedEvent;
import org.wirez.core.client.canvas.event.selection.CanvasElementSelectedEvent;
import org.wirez.core.graph.Edge;
import org.wirez.core.graph.Graph;
import org.wirez.core.graph.Node;
import org.wirez.core.graph.content.relationship.Child;
import org.wirez.core.graph.processing.traverse.content.AbstractContentTraverseCallback;
import org.wirez.core.graph.processing.traverse.content.ChildrenTraverseProcessor;
import org.wirez.core.client.canvas.AbstractCanvas;
import org.wirez.core.client.canvas.event.*;
import org.wirez.core.client.canvas.Canvas;
import org.wirez.core.client.canvas.CanvasHandler;
import org.wirez.core.client.shape.Shape;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

// TODO: Use incremental updates, do not visit whole graph on each model update.
@Dependent
public class TreeExplorer implements IsWidget {
    
    private static Logger LOGGER = Logger.getLogger(TreeExplorer.class.getName());

    public interface View extends UberView<TreeExplorer> {

        View addItem(String uuid, IsWidget itemView, boolean state);

        View addItem(String uuid, IsWidget itemView, boolean state, int... parentIdx);

        View removeItem(int index);

        View removeItem(int index, int... parentIdx);
        
        View clear();
    }
    
    DefinitionManager definitionManager;
    ChildrenTraverseProcessor childrenTraverseProcessor;
    Instance<TreeExplorerItem> treeExplorerItemInstances;
    Event<CanvasElementSelectedEvent> elementSelectedEventEvent;
    View view;

    private CanvasHandler canvasHandler;

    @Inject
    public TreeExplorer(final DefinitionManager definitionManager,
                        final ChildrenTraverseProcessor childrenTraverseProcessor,
                        final Instance<TreeExplorerItem> treeExplorerItemInstances,
                        final Event<CanvasElementSelectedEvent> elementSelectedEventEvent,
                        final View view) {
        this.definitionManager = definitionManager;
        this.childrenTraverseProcessor = childrenTraverseProcessor;
        this.treeExplorerItemInstances = treeExplorerItemInstances;
        this.elementSelectedEventEvent = elementSelectedEventEvent;
        this.view = view;
    }

    @PostConstruct
    public void init() {
        view.init(this);
    }
    
    public void show(final CanvasHandler canvasHandler) {
        this.canvasHandler = canvasHandler;
        assert canvasHandler != null;
        doShow(canvasHandler.getDiagram().getGraph());
    }

    private void doShow(final Graph<org.wirez.core.graph.content.view.View, Node<org.wirez.core.graph.content.view.View, Edge>> graph) {
        traverseChildrenEdges(graph, true);
    }

    private void traverseChildrenEdges(final Graph<org.wirez.core.graph.content.view.View, Node<org.wirez.core.graph.content.view.View, Edge>> graph,
                                       final boolean expand) {
        assert graph != null;

        clear();

        childrenTraverseProcessor.traverse(graph, new AbstractContentTraverseCallback<Child, Node<org.wirez.core.graph.content.view.View, Edge>, Edge<Child, Node>>() {

            Node parent = null;
            int level = 0;
            final List<Integer> levelIdx = new LinkedList<Integer>();
            
            @Override
            public void startEdgeTraversal(final Edge<Child, Node> edge) {
                super.startEdgeTraversal(edge);
                final Node newParent = edge.getSourceNode();
                assert newParent != null;
                
                if ( null == parent || ( !parent.equals(newParent) ) ) {
                    level++;
                }
                
                this.parent = edge.getSourceNode();
            }

            @Override
            public void endEdgeTraversal(final Edge<Child, Node> edge) {
                super.endEdgeTraversal(edge);

                final Node newParent = edge.getSourceNode();
                assert newParent != null;
                
                if ( !parent.equals(newParent) ) {
                    level--;
                    this.parent = newParent;
                }
            }

            @Override
            public void startGraphTraversal(final Graph<org.wirez.core.graph.content.view.View, Node<org.wirez.core.graph.content.view.View, Edge>> graph) {
                super.startGraphTraversal(graph);
                levelIdx.clear();
                levelIdx.add(-1);
            }

            @Override
            public void startNodeTraversal(final Node<org.wirez.core.graph.content.view.View, Edge> node) {
                super.startNodeTraversal(node);

                inc(levelIdx, level);
                
                if ( null == parent ) {

                    final TreeExplorerItem item = treeExplorerItemInstances.get();
                    view.addItem(node.getUUID(), item.asWidget(), expand);
                    item.show(node);

                } else {
                    
                    int[] parentsIdx = getParentsIdx(levelIdx, level);
                    final TreeExplorerItem item = treeExplorerItemInstances.get();
                    view.addItem(node.getUUID(), item.asWidget(), expand, parentsIdx);
                    item.show(node);
                    
                }

            }
          
        });

    }
    
    private void inc(final List<Integer> levels, final int level) {
        if ( levels.size() < ( level + 1 ) ) {
            levels.add(0);
        } else {
            final int idx = levels.get(level);
            levels.set(level, idx + 1);
        }
    }

    private int[] getParentsIdx(final List<Integer> idxList, final int maxLevel) {
        if ( !idxList.isEmpty() ) {
            final int targetPos = ( idxList.size() - ( idxList.size() - maxLevel ) ) + 1;
            final int[] resultArray = new int[targetPos];
            for (int x = 0; x < targetPos ; x++) {
                resultArray[x] = idxList.get(x);
            }
            return resultArray;
        }

        return new int[] { };
    }
    
    public void clear() {
        view.clear();
    }
    
    void onSelect(final String uuid) {
        selectShape(canvasHandler.getCanvas(), uuid);
    }

    private void selectShape(final Canvas canvas, final String uuid) {
        elementSelectedEventEvent.fire( new CanvasElementSelectedEvent( canvasHandler, uuid ) );

    }

    void onCanvasClearEvent(@Observes CanvasClearEvent canvasClearEvent) {
        if ( canvasHandler != null && canvasHandler.getCanvas().equals(canvasClearEvent.getCanvas()) ) {
            clear();
        }
    }

    void onCanvasElementAddedEvent(@Observes CanvasElementAddedEvent canvasElementAddedEvent) {
        if ( checkEventContext(canvasElementAddedEvent) ) {
            showEventGraph( canvasElementAddedEvent );
        }
    }

    void onCanvasElementRemovedEvent(@Observes CanvasElementRemovedEvent elementRemovedEvent) {
        if ( checkEventContext(elementRemovedEvent) ) {
            showEventGraph( elementRemovedEvent );
        }
    }

    void onCanvasElementUpdatedEvent(@Observes CanvasElementUpdatedEvent canvasElementUpdatedEvent) {
        if ( checkEventContext(canvasElementUpdatedEvent) ) {
            showEventGraph( canvasElementUpdatedEvent );
        }
    }

    private boolean checkEventContext(final AbstractCanvasHandlerEvent canvasHandlerEvent) {
        final CanvasHandler _canvasHandler = canvasHandlerEvent.getCanvasHandler();
        return canvasHandler != null && canvasHandler.equals(_canvasHandler);
    }
    
    private void showEventGraph(final AbstractCanvasHandlerEvent canvasHandlerEvent) {
        doShow( canvasHandlerEvent.getCanvasHandler().getDiagram().getGraph() );
    } 

    @Override
    public Widget asWidget() {
        return view.asWidget();
    }

}
