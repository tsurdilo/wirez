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

package org.wirez.core.api.definition;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.uberfire.commons.validation.PortablePreconditions;

@Portable
public class DefaultDefinition<C extends Content> implements Definition<C> {

    private final String id;
    private C content;

    public DefaultDefinition(@MapsTo("id") String id) {
        this.id = PortablePreconditions.checkNotNull( "id",
                id );
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public C getContent() {
        return content;
    }

    public void setContent(C content) {
        this.content = content;
    }
}
