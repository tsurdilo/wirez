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

import org.wirez.core.api.definition.DefinitionSet;
import org.wirez.core.api.graph.Graph;
import org.wirez.core.client.ShapeSet;

public class DefaultCanvasSettingsBuilder implements CanvasSettingsBuilder<DefaultCanvasSettingsBuilder> {


    private DefaultCanvasSettings settings;
    
    public DefaultCanvasSettingsBuilder() {
        settings = new DefaultCanvasSettings();
    }

    @Override
    public DefaultCanvasSettingsBuilder uuid(final String uuid) {
        settings.setUUUID(uuid);
        return this;
    }

    @Override
    public DefaultCanvasSettingsBuilder definitionSet(final DefinitionSet definitionSet) {
        settings.setDefinitionSet(definitionSet);
        return this;
    }

    @Override
    public DefaultCanvasSettingsBuilder shapeSet(final ShapeSet shapeSet) {
        settings.setShapeSet(shapeSet);
        return this;
    }


    @Override
    public DefaultCanvasSettingsBuilder title(final String title) {
        settings.setTitle(title);
        return this;
    }

    @Override
    public DefaultCanvasSettingsBuilder graph(final Graph graph) {
        settings.setGraph(graph);
        return this;
    }

    @Override
    public CanvasSettings build() {
        return settings;
    }
}
