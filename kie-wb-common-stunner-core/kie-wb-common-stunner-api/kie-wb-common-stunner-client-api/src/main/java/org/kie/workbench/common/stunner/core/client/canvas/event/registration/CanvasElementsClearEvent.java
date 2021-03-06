package org.kie.workbench.common.stunner.core.client.canvas.event.registration;

import org.jboss.errai.common.client.api.annotations.NonPortable;
import org.kie.workbench.common.stunner.core.client.canvas.CanvasHandler;
import org.kie.workbench.common.stunner.core.client.canvas.event.AbstractCanvasHandlerEvent;

@NonPortable
public final class CanvasElementsClearEvent extends AbstractCanvasHandlerEvent<CanvasHandler> {

    public CanvasElementsClearEvent( final CanvasHandler canvasHandler ) {
        super( canvasHandler );
    }

}
