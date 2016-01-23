package org.wirez.core.backend.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wirez.core.api.adapter.DefinitionAdapter;
import org.wirez.core.api.definition.DefaultDefinition;
import org.wirez.core.api.definition.Definition;
import org.wirez.core.api.definition.property.Property;
import org.wirez.core.api.definition.property.PropertySet;
import org.wirez.core.api.graph.Element;
import org.wirez.core.api.graph.Node;
import org.wirez.core.api.graph.factory.DefaultGraphFactory;
import org.wirez.core.api.graph.factory.EdgeFactory;
import org.wirez.core.api.graph.factory.ElementFactory;
import org.wirez.core.api.graph.factory.NodeFactory;
import org.wirez.core.api.graph.impl.DefaultGraph;
import org.wirez.core.api.rule.Rule;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@ApplicationScoped
public class AnnotatedDefinitionAdapter implements DefinitionAdapter<Definition> {

    private static final Logger LOG = LoggerFactory.getLogger(AnnotatedDefinitionAdapter.class);

    @Inject
    AnnotatedPropertyAdapter propertyAdapter;

    @Inject
    AnnotatedPropertySetAdapter propertySetAdapter;

    @Inject
    DefaultGraphFactory<? extends Definition> graphFactory;

    @Inject
    NodeFactory<? extends Definition> nodeFactory;

    @Inject
    EdgeFactory<? extends Definition> edgeFactory;

    @Override
    public boolean accepts(Class pojoClass) {
        return !pojoClass.equals(DefaultDefinition.class);
    }

    @Override
    public Set<PropertySet> getPropertySets(final Definition definition) {
        Set<PropertySet> result = null;

        if ( null != definition ) {
            Field[] fields = definition.getClass().getDeclaredFields();
            if ( null != fields ) {
                result = new HashSet<>();
                for (Field field : fields) {
                    org.wirez.core.api.annotation.definition.PropertySet annotation = field.getAnnotation(org.wirez.core.api.annotation.definition.PropertySet.class);
                    if ( null != annotation) {
                        try {
                            field.setAccessible(true);
                            PropertySet propertySet = (PropertySet) field.get(definition);
                            result.add(propertySet);
                        } catch (Exception e) {
                            LOG.error("Error obtaining annotated property sets for Definition with id " + definition.getId());
                        }
                    }
                }
            }
        }

        return result;
    }

    @Override
    public Set<Property> getProperties(final Definition definition) {
        Set<Property> result = null;

        if ( null != definition ) {
            Field[] fields = definition.getClass().getDeclaredFields();
            if ( null != fields ) {
                result = new HashSet<>();
                for (Field field : fields) {
                    org.wirez.core.api.annotation.definition.Property annotation = field.getAnnotation(org.wirez.core.api.annotation.definition.Property.class);
                    if ( null != annotation) {
                        try {
                            field.setAccessible(true);
                            Property property = (Property) field.get(definition);
                            result.add(property);
                        } catch (Exception e) {
                            LOG.error("Error obtaining annotated properties for Definition with id " + definition.getId());
                        }
                    }
                }
            }
        }
        
        return result;
    }

    @Override
    public Map<Property, Object> getPropertiesValues(Definition pojo) {

        if ( null != pojo ) {

            final Set<Property> properties = new HashSet<>();
            final Set<Property> _properties = getProperties(pojo);
            if ( null != _properties ) {
                properties.addAll(_properties);
            }
            
            final Set<PropertySet> propertySets = getPropertySets(pojo);
            if ( null != propertySets && !propertySets.isEmpty() ) {
                for (PropertySet propertySet : propertySets) {
                    final Set<Property> psProps = propertySetAdapter.getProperties(propertySet);
                    if ( null != psProps ) {
                        properties.addAll(psProps);
                    }
                }
            }
            
            if ( !properties.isEmpty() ) {

                final Map<Property, Object> result = new HashMap<>(properties.size());

                for (final Property property : properties) {
                    
                    Object value = propertyAdapter.getValue(property);
                    result.put(property, value);

                }
                
                return result;
            }
            
        }
        
        return new HashMap<>();
    }

    @Override
    public Class<? extends Element> getGraphElementType(Definition definition) {

        Class<? extends Element> result = null;

        if ( null != definition ) {
            org.wirez.core.api.annotation.graph.Graph annotation = definition.getClass().getAnnotation(org.wirez.core.api.annotation.graph.Graph.class);
            result = annotation.type();
        }
        
        return result;
    }

    @Override
    public ElementFactory getElementFactory(Definition definition) {
       
        if ( null != definition ) {

            Class<? extends Element> item = getGraphElementType(definition);

            if (item.equals(DefaultGraph.class)) {
                return graphFactory;
            } else if (item.equals(Node.class)) {
                return nodeFactory;
            } else if (item.equals(Node.class)) {
                return edgeFactory;
            }

        }
        
        return null;
    }

    @Override
    public int getPriority() {
        return 100;
    }
    
}