package org.kie.workbench.common.stunner.client.lienzo.canvas.controls.toolbox.command.builder;

import com.ait.lienzo.client.core.shape.Shape;
import org.kie.workbench.common.stunner.core.client.api.ClientDefinitionManager;
import org.kie.workbench.common.stunner.core.client.ShapeManager;
import org.kie.workbench.common.stunner.core.client.canvas.AbstractCanvasHandler;
import org.kie.workbench.common.stunner.core.client.canvas.controls.builder.EdgeBuilderControl;
import org.kie.workbench.common.stunner.core.client.canvas.controls.toolbox.command.builder.NewConnectorCommand;
import org.kie.workbench.common.stunner.core.client.components.drag.ConnectorDragProxy;
import org.kie.workbench.common.stunner.core.client.components.glyph.DefinitionGlyphTooltip;
import org.kie.workbench.common.stunner.core.client.service.ClientFactoryServices;
import org.kie.workbench.common.stunner.core.graph.processing.index.bounds.GraphBoundsIndexer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class LienzoNewConnectorToolboxCommand extends NewConnectorCommand<Shape<?>> {

    protected LienzoNewConnectorToolboxCommand() {
        this( null, null, null, null, null, null, null );
    }

    @Inject
    public LienzoNewConnectorToolboxCommand(final ClientDefinitionManager clientDefinitionManager,
                                            final ClientFactoryServices clientFactoryServices,
                                            final ShapeManager shapeManager,
                                            final DefinitionGlyphTooltip<?> glyphTooltip,
                                            final GraphBoundsIndexer graphBoundsIndexer,
                                            final ConnectorDragProxy<AbstractCanvasHandler> connectorDragProxyFactory,
                                            final EdgeBuilderControl<AbstractCanvasHandler> edgeBuilderControl ) {
        super( clientDefinitionManager, clientFactoryServices, shapeManager, glyphTooltip,
                graphBoundsIndexer, connectorDragProxyFactory, edgeBuilderControl );
    }

    @PostConstruct
    public void init() {
        glyphTooltip.setPrefix( "Create a new " );
    }
    
}
