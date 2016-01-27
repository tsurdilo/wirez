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

package org.wirez.core.client.control.toolbox;

import com.ait.lienzo.client.core.event.NodeMouseClickEvent;
import com.ait.lienzo.client.core.event.NodeMouseClickHandler;
import com.ait.lienzo.client.core.event.NodeMouseDoubleClickEvent;
import com.ait.lienzo.client.core.event.NodeMouseDoubleClickHandler;
import com.ait.lienzo.client.core.shape.Node;
import com.ait.lienzo.client.core.shape.SVGPath;
import com.ait.lienzo.client.core.shape.wires.WiresShape;
import com.ait.lienzo.shared.core.types.Direction;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.wirez.core.api.graph.Element;
import org.wirez.core.client.Shape;
import org.wirez.core.client.canvas.command.impl.DefaultCanvasCommands;
import org.wirez.core.client.impl.BaseShape;
import org.wirez.lienzo.toolbox.HoverToolbox;
import org.wirez.lienzo.toolbox.HoverToolboxButton;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

@Dependent
public class NameToolboxControl extends BaseToolboxControl<Shape, Element> implements IsWidget {

    final Toolbox<Element> toolbox;

    @Inject
    public NameToolboxControl(DefaultCanvasCommands defaultCanvasCommands, Toolbox<Element> toolbox) {
        super(defaultCanvasCommands);
        this.toolbox = toolbox;
    }

    @Override
    public void enable(final Shape shape, final Element element) {

        toolbox.initialize(canvasHandler);

        if ( shape instanceof BaseShape ) {

            BaseShape baseShape = (BaseShape) shape;
            
            if ( null != baseShape.getText() ) {

                baseShape.getText().addNodeMouseClickHandler(new NodeMouseClickHandler() {
                    @Override
                    public void onNodeMouseClick(NodeMouseClickEvent nodeMouseDoubleClickEvent) {
                        final double[] xy = getContainerXY(shape);
                        GWT.log("ToolboxControl - Showing name toolbox");
                        toolbox.show(element, xy[0], xy[1]);
                    }
                });

            }
            
        }

    }

    @Override
    public void disable(final Shape shape) {

        toolbox.hide();

    }

    @Override
    public Widget asWidget() {
        return toolbox.asWidget();
    }

}