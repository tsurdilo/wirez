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

package org.wirez.core.api.rule.violations;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.wirez.core.api.graph.Element;
import org.wirez.core.api.graph.content.view.View;
import org.wirez.core.api.rule.violations.AbstractRuleViolation;

public abstract class AbstractCardinalityRuleViolation<T, S> extends AbstractRuleViolation {
    
    protected T target;
    protected  S candidate;
    protected  Long restrictedOccurrences;
    protected  Long currentOccurrences;

    public AbstractCardinalityRuleViolation(T target,
                                            S candidate,
                                            Long restrictedOccurrences,
                                            Long currentOccurrences) {
        this.target = target;
        this.candidate = candidate;
        this.restrictedOccurrences = restrictedOccurrences;
        this.currentOccurrences = currentOccurrences;
    }
    
    protected String getTargetText() {
        if ( target instanceof Element ) {
            return getDefinitionTitle((Element<? extends View<?>>) target);
        }
        return target.toString();
    }

    protected String getCandidateText() {
        if ( candidate instanceof Element ) {
            return getDefinitionTitle((Element<? extends View<?>>) candidate);
        }
        return candidate.toString();
    }

    @Override
    public Type getViolationType() {
        return Type.ERROR;
    }
    
}