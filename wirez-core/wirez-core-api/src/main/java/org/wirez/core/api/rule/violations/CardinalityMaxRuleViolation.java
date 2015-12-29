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
import org.wirez.core.api.rule.RuleViolation;

@Portable
public class CardinalityMaxRuleViolation implements RuleViolation {
    
    private String target;
    private String candidate;
    private Long maxOccurrences;
    private Long currentOccurrences;

    public CardinalityMaxRuleViolation(@MapsTo("target") String target,
                                       @MapsTo("candidate") String candidate,
                                       @MapsTo("maxOccurrences") Long maxOccurrences,
                                       @MapsTo("currentOccurrences") Long currentOccurrences) {
        this.target = target;
        this.candidate = candidate;
        this.maxOccurrences = maxOccurrences;
        this.currentOccurrences = currentOccurrences;
    }

    @Override
    public String getMessage() {
        return "'" + target + "' can have a maximum '" + maxOccurrences + "' of '" + candidate + "' nodes. Found '" + currentOccurrences + "'.";
    }

    @Override
    public Type getViolationType() {
        return Type.ERROR;
    }
    
}