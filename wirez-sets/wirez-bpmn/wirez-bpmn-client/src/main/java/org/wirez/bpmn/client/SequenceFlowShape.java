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

package org.wirez.bpmn.client;

import com.ait.lienzo.client.core.shape.AbstractDirectionalMultiPointShape;
import com.ait.lienzo.client.core.shape.DecoratableLine;
import com.ait.lienzo.client.core.shape.Decorator;
import com.ait.lienzo.client.core.shape.wires.WiresManager;
import org.wirez.bpmn.api.SequenceFlow;
import org.wirez.core.client.animation.ShapeDeSelectionAnimation;
import org.wirez.core.client.animation.ShapeSelectionAnimation;
import org.wirez.core.client.canvas.Canvas;
import org.wirez.core.client.canvas.ShapeState;
import org.wirez.core.client.canvas.impl.BaseCanvas;
import org.wirez.core.client.mutation.HasCanvasStateMutation;

public class SequenceFlowShape extends BPMNBasicConnector<SequenceFlow> implements HasCanvasStateMutation {

    DecoratableLine decorator;

    private double strokeWidth;
    private String color;
    
    public SequenceFlowShape(final AbstractDirectionalMultiPointShape<?> line, 
                          final Decorator<?> head, 
                          final Decorator<?> tail, 
                          final WiresManager manager) {
        super(line, head, tail, manager);
        // buildDecorator();
    }

    @Override
    public void afterMutations(final Canvas canvas) {
        super.afterMutations(canvas);
    }
    
    @Override
    public String toString() {
        return "SequenceFlowShape{}";
    }

    @Override
    public void destroy() {

    }

    @Override
    public void applyState(final ShapeState shapeState) {
        if (ShapeState.SELECTED.equals(shapeState)) {
            this.strokeWidth = getDecoratableLine().getStrokeWidth();
            this.color = getDecoratableLine().getStrokeColor();
            new ShapeSelectionAnimation(this)
                    .setDuration(BaseCanvas.ANIMATION_SELECTION_DURATION)
                    .run();
        } else if (ShapeState.DESELECTED.equals(shapeState)) {
            new ShapeDeSelectionAnimation(this, this.strokeWidth, 1, this.color)
                    .setDuration(BaseCanvas.ANIMATION_SELECTION_DURATION)
                    .run();
        }
    }
    
}
