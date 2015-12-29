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

package org.wirez.core.api.graph.store;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.wirez.core.api.graph.impl.DefaultEdge;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Portable
public class DefaultGraphEdgeStore implements GraphEdgeStore<DefaultEdge> {

    protected Map<String, DefaultEdge> edges = new HashMap<String, DefaultEdge>();
    
    @Override
    public DefaultEdge add(final DefaultEdge edge) {
        return edges.put(edge.getUUID(), edge);
    }

    @Override
    public DefaultEdge remove(final String uuid) {
        return edges.remove(uuid);
    }

    @Override
    public DefaultEdge get(final String uuid) {
        return edges.get(uuid);
    }

    @Override
    public int size() {
        return edges.size();
    }

    @Override
    public void clear() {
        edges.clear();
    }

    @Override
    public Iterator<DefaultEdge> iterator() {
        return edges.values().iterator();
    }
}