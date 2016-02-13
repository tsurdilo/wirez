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

package org.wirez.bpmn.client.factory;

import org.wirez.bpmn.api.EndTerminateEvent;
import org.wirez.bpmn.api.EndTerminateEvent;
import org.wirez.bpmn.client.EndTerminateEventShape;
import org.wirez.bpmn.client.factory.control.BPMNToolboxControlFactory;
import org.wirez.bpmn.client.glyph.EndTerminateEventGlyph;
import org.wirez.core.api.definition.Definition;
import org.wirez.core.client.ShapeGlyph;
import org.wirez.core.client.canvas.CanvasHandler;
import org.wirez.core.client.canvas.control.ShapeGlyphDragHandler;
import org.wirez.core.client.canvas.impl.BaseCanvas;
import org.wirez.core.client.factory.control.DefaultShapeControlFactories;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EndTerminateEventShapeFactory extends BaseBPMNShapeFactory<EndTerminateEvent, EndTerminateEventShape> {
    
    public EndTerminateEventShapeFactory() {
    }

    @Inject
    public EndTerminateEventShapeFactory(final DefaultShapeControlFactories defaultShapeControlFactories,
                                         final ShapeGlyphDragHandler shapeGlyphDragHandler,
                                         final BPMNToolboxControlFactory bpmnToolboxControlFactory) {
        super(defaultShapeControlFactories, shapeGlyphDragHandler, bpmnToolboxControlFactory);
    }

    @Override
    public boolean accepts(final Definition definition) {
        return definition instanceof EndTerminateEvent;
    }

    @Override
    public ShapeGlyph getGlyph() {
        return EndTerminateEventGlyph.INSTANCE;
    }

    @Override
    public String getDescription() {
        return "An end terminate event";
    }

    @Override
    public EndTerminateEventShape build(final EndTerminateEvent definition, final CanvasHandler canvasHandler) {

        final BaseCanvas baseWirezCanvas = (BaseCanvas) canvasHandler.getCanvas();

        EndTerminateEventShape circleShape = new EndTerminateEventShape(baseWirezCanvas.getWiresManager());

        return circleShape;

    }

}