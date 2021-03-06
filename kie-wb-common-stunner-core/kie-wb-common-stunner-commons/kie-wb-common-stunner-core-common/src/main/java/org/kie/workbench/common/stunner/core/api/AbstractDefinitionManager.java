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

package org.kie.workbench.common.stunner.core.api;

import org.kie.workbench.common.stunner.core.api.DefinitionManager;
import org.kie.workbench.common.stunner.core.definition.adapter.Adapter;
import org.kie.workbench.common.stunner.core.definition.adapter.AdapterManager;
import org.kie.workbench.common.stunner.core.registry.DynamicRegistry;
import org.kie.workbench.common.stunner.core.registry.RegistryFactory;
import org.kie.workbench.common.stunner.core.registry.definition.TypeDefinitionSetRegistry;

public abstract class AbstractDefinitionManager implements DefinitionManager {

    private final TypeDefinitionSetRegistry<?> definitionSetRegistry;
    private final AdapterManager adapterManager;

    protected AbstractDefinitionManager() {
        this.definitionSetRegistry = null;
        this.adapterManager = null;
    }

    public AbstractDefinitionManager( final RegistryFactory registryFactory,
                                      final AdapterManager adapterManager ) {
        this.definitionSetRegistry = registryFactory.newDefinitionSetRegistry();
        this.adapterManager = adapterManager;
    }

    @Override
    public TypeDefinitionSetRegistry<?> definitionSets() {
        return definitionSetRegistry;
    }

    @Override
    public AdapterManager adapters() {
        return adapterManager;
    }

    @SuppressWarnings( "unchecked" )
    protected void addDefinitionSet( final Object object ) {
        ( ( DynamicRegistry) definitionSetRegistry).register( object );
    }

    @SuppressWarnings( "unchecked" )
    protected void addAdapter( final Adapter adapter ) {
        final DynamicRegistry<Adapter> adapterDynamicRegistry = ( DynamicRegistry<Adapter> ) adapterManager.registry();
        adapterDynamicRegistry.register( adapter );
    }


}

