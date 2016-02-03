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

package org.wirez.core.api.annotation.rule;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE) 
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Cardinality.class)
public @interface Occurrences {

    String id() default "";
    
    // If a role value is given here, it will override any role value for any of the child EdgeOccurrences instances.
    String role() default "";
    
    long min() default 0;

    long max() default -1;
    
    EdgeOccurrences[] value();
    
}
