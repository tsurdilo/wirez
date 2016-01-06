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
import org.wirez.core.api.graph.commands.UpdateElementPropertyValueCommand;
import org.wirez.core.client.Shape;
import org.wirez.core.client.canvas.command.impl.CompositeElementCanvasCommand;
import org.wirez.core.client.control.BaseShapeControl;
import org.wirez.core.client.impl.BaseShape;
import org.wirez.core.client.mutation.HasRadiusMutation;
import org.wirez.core.client.mutation.HasSizeMutation;
import org.wirez.core.client.mutation.StaticMutationContext;

public abstract class BaseResizeControl<S extends Shape, E extends Element> extends BaseShapeControl<S, E> {
    
    protected void doResizeStart(final S shape, final E element, final double width, final double height) {
        
    }

    protected void doResizeStep(final S shape, final E element, final double width, final double height) {
        if (shape instanceof HasSizeMutation) {
            ( (HasSizeMutation) shape).applySize(width, height, new StaticMutationContext());
        } else if (shape instanceof HasRadiusMutation) {
            final double radius = getRadius(width, height);
            ( (HasRadiusMutation) shape).applyRadius(radius, new StaticMutationContext());
        }
    }
    
    protected void doResizeEnd(final S shape, final E element, final double width, final double height) {
        
        if (shape instanceof HasSizeMutation) {
            
            getCommandManager().execute(
                    new CompositeElementCanvasCommand(element)
                            .add(new UpdateElementPropertyValueCommand(element, "width", (int) width ))
                            .add(new UpdateElementPropertyValueCommand(element, "height" , (int) height ))
                            .doApplyElementProperties()
            );
            
        } else if (shape instanceof HasRadiusMutation) {

            final double radius = getRadius(width, height);
            getCommandManager().execute(
                    new CompositeElementCanvasCommand(element)
                            .add(new UpdateElementPropertyValueCommand(element, "radius" , (int) radius ))
                            .doApplyElementProperties()
            );
            
        }
        
    }

    @Override
    public void disable(final S shape) {

        if (shape instanceof BaseShape) {
            ((BaseShape) shape).setResizable(false);
        }
        
    }

    protected double getRadius(double width, double height) {
        final double radiusW = width / 2;
        final double radiusH = height / 2;
        return  (radiusH >= radiusW ? radiusW : radiusH);
    }

}
