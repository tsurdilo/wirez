package org.kie.workbench.common.stunner.core.client.shape.view.event;

public interface MouseEvent extends ViewEvent {
    
    double getX();

    double getY();

    double getClientX();

    double getClientY();

}
