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

package ${packageName};

import javax.annotation.Generated;
import javax.enterprise.context.Dependent;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.wirez.core.api.definition.property.Property;
import org.wirez.core.api.definition.property.PropertyType;
import org.wirez.core.api.definition.property.HasDefaultValue;
import org.wirez.core.processors.ProcessingElement;

/*
 * WARNING! This class is generated. Do not modify.
 */
@Generated("org.wirez.core.processors.property.PropertyProcessor")
@Dependent
@Portable
public class ${className} ${classHierarchyModifier} ${realClassName}
<#if defaultValue??>
    , HasDefaultValue<${defaultValue.className}>
</#if>
{

    private ${defaultValue.className} value;

    public ${className}() {}

    @Override
    public String getId() {
        return "${identifier}";
    }

    @Override
    public PropertyType getType() {
        return new ${typeName}();
    }

    @Override
    public String getCaption() {
        return "${caption}";
    }

    @Override
    public String getDescription() {
        return "${description}";
    }
    
    @Override
    public boolean isReadOnly() {
        return ${isReadOnly};
    }
    
    @Override
    public boolean isOptional() {
        return ${isOptional};
    }
    
    @Override
    public boolean isPublic() {
        return ${isPublic};
    }

    <#if defaultValue??>
        @Override
        public ${defaultValue.className} getDefaultValue() {
            return ${realClassName}.${defaultValue.methodName};
        }
    </#if>

    @Override
    public ${defaultValue.className} getValue() {
        return value;
    }
    
    @Override
    public void setValue(final ${defaultValue.className} value) {
        this.value = value;
    }

}