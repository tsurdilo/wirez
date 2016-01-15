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

package org.wirez.basicset.api;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.wirez.core.api.definition.DefaultContent;
import org.wirez.core.api.definition.DefaultContentImpl;
import org.wirez.core.api.definition.property.Property;
import org.wirez.core.api.definition.property.PropertySet;
import org.wirez.core.api.definition.property.defaultset.DefaultPropertySetBuilder;
import org.wirez.core.api.definition.property.defaultset.NameBuilder;
import org.wirez.core.api.graph.Bounds;
import org.wirez.core.api.graph.Edge;
import org.wirez.core.api.graph.Node;
import org.wirez.core.api.graph.content.ViewContent;
import org.wirez.core.api.graph.content.ViewContentImpl;
import org.wirez.core.api.graph.factory.DefaultGraphFactory;
import org.wirez.core.api.graph.impl.DefaultGraph;
import org.wirez.core.api.graph.impl.DefaultGraphImpl;
import org.wirez.core.api.graph.store.DefaultGraphEdgeStore;
import org.wirez.core.api.graph.store.DefaultGraphNodeStore;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Portable
public class Diagram extends BasicDefinition implements DefaultGraphFactory<ViewContent<Diagram>> {

    public static final String ID = "diagram";

    public Diagram() {
        super(ID);
        setContent(new DefaultContentImpl(BasicSetCategories.INSTANCE.DIAGRAM,
                "Basic Diagram",
                "A basic shapes diagram",
                labels,
                propertySets,
                properties));
            
    }

    private final Set<Property> properties = new HashSet<Property>() {{
        // No custom properties.
    }};

    private final Set<String> labels = new HashSet<String>() {{
        add( "all" );
        add( "diagram" );
    }};
    
    private final Set<PropertySet> propertySets = new HashSet<PropertySet>() {{
        add(new DefaultPropertySetBuilder()
                .withProperty(new NameBuilder().defaultValue("My diagram").build())
                .build()
        );
    }};

    @Override
    public DefaultGraph<ViewContent<Diagram>, Node, Edge> build(final String uuid,
                                                               final Set<String> labels,
                                                               final Map<String, Object> properties) {
        return new DefaultGraphImpl<ViewContent<Diagram>> (
                uuid,
                buildElementProperties(properties),
                buildElementLabels(labels),
                new ViewContentImpl<Diagram>(this, buildBounds()),
                new DefaultGraphNodeStore(), new DefaultGraphEdgeStore()
        );
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }
}
