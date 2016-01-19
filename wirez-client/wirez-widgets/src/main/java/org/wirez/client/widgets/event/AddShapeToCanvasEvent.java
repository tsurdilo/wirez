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

package org.wirez.client.widgets.event;

import org.uberfire.workbench.events.UberFireEvent;
import org.wirez.core.api.definition.Definition;
import org.wirez.core.api.definition.DefinitionSet;
import org.wirez.core.client.Shape;
import org.wirez.core.client.factory.ShapeFactory;

/**
 * <p>CDI event when a shape in the palette is selected and must be added into the active canvas diagram.</p>
 *
 */
public class AddShapeToCanvasEvent implements UberFireEvent {

    private Definition definition;
    private ShapeFactory<? extends Definition, ? extends Shape> shapeFactory;
    private double x;
    private double y;

    public AddShapeToCanvasEvent(final Definition definition,
                                 final ShapeFactory<? extends Definition, ? extends Shape> shapeFactory) {
        this.definition = definition;
        this.shapeFactory = shapeFactory;
        this.x = -1;
        this.y = -1;
    }

    public AddShapeToCanvasEvent(final Definition definition,
                                 final ShapeFactory<? extends Definition, ? extends Shape> shapeFactory,
                                 final double x,
                                 final double y) {
        this.definition = definition;
        this.shapeFactory = shapeFactory;
        this.x = x;
        this.y = y;
    }

    public Definition getDefinition() {
        return definition;
    }

    public ShapeFactory<? extends Definition, ? extends Shape> getShapeFactory() {
        return shapeFactory;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "PaletteShapeSelectedEvent [definition=" + definition.getId() + ", factory=" + shapeFactory.toString() + 
                ", x=" + x + ", y=" + y + "]";
    }

}