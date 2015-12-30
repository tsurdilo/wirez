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

package org.wirez.basicset.client;

import com.ait.lienzo.client.core.shape.AbstractDirectionalMultiPointShape;
import com.ait.lienzo.client.core.shape.Decorator;
import com.ait.lienzo.client.core.shape.wires.WiresManager;
import org.wirez.basicset.api.Connector;

public class ConnectorShape extends BaseBasicConnector<Connector> {

    public ConnectorShape(final AbstractDirectionalMultiPointShape<?> line, 
                          final Decorator<?> head, 
                          final Decorator<?> tail, 
                          final WiresManager manager) {
        super(line, head, tail, manager);
    }


    @Override
    public String toString() {
        return "ConnectorShape{}";
    }

    @Override
    public void destroy() {

    }
}