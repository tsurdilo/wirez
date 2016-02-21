/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

package org.wirez.core.client.control.resize;

import com.ait.lienzo.client.core.shape.wires.event.AbstractWiresEvent;
import com.ait.lienzo.client.core.shape.wires.event.ResizeEvent;
import com.ait.lienzo.client.core.shape.wires.event.ResizeHandler;
import org.wirez.core.api.graph.Element;
import org.wirez.core.client.Shape;
import org.wirez.core.client.canvas.command.factory.CanvasCommandFactory;
import org.wirez.core.client.impl.BaseShape;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class LienzoResizeControl extends BaseResizeControl<Shape, Element> {

    @Inject
    public LienzoResizeControl(CanvasCommandFactory commandFactory) {
        super(commandFactory);
    }

    @Override
    public void doEnable(final Shape shape, final Element element) {
        
        // TODO
        /*if (shape instanceof BaseShape) {
            ( (BaseShape) shape).setResizable(shape.getShape(), true).addWiresHandler(AbstractWiresEvent.RESIZE, new ResizeHandler() {
                @Override
                public void onResizeStart(ResizeEvent resizeEvent) {
                    
                }

                @Override
                public void onResizeStep(ResizeEvent resizeEvent) {
                    
                }

                @Override
                public void onResizeEnd(ResizeEvent resizeEvent) {
                    doResizeEnd(shape, element, resizeEvent.getWidth(), resizeEvent.getHeight());
                }
            });
        }*/
        
    }
    
}
