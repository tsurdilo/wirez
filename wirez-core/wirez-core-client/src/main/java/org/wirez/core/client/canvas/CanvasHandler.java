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

package org.wirez.core.client.canvas;

import org.wirez.core.api.diagram.Diagram;
import org.wirez.core.client.canvas.listener.CanvasListener;

public interface CanvasHandler<D extends Diagram, C extends Canvas, L extends CanvasListener> {

    /**
     * Loads the given diagram and displays it on the canvas..
     */
    CanvasHandler<D, C, L> draw( D diagram, C canvas );

    /**
     * Listens to events from this canvas handler.
     */
    CanvasHandler<D, C, L> addListener( L listener );

    /**
     * The managed diagram instance.
     */
    D getDiagram();
    
    /**
     * The managed canvas instance.
     */
    C getCanvas();
    
}
