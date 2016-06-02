package org.wirez.bpmn.shape.proxy;

import org.wirez.bpmn.definition.EndTerminateEvent;
import org.wirez.core.client.shape.HasChildren;
import org.wirez.shapes.proxy.BasicShapeProxy;
import org.wirez.shapes.proxy.CircleProxy;
import org.wirez.shapes.proxy.HasChildProxies;
import org.wirez.shapes.proxy.RingProxy;

import java.util.HashMap;
import java.util.Map;

public final class EndTerminateEventShapeProxy implements 
        CircleProxy<EndTerminateEvent>,
        HasChildProxies<EndTerminateEvent> {
    
    @Override
    public double getRadius( final EndTerminateEvent element ) {
        return element.getRadius().getValue();
    }

    @Override
    public String getBackgroundColor( final EndTerminateEvent element ) {
        return element.getBackgroundSet().getBgColor().getValue();
    }

    @Override
    public String getBorderColor( final EndTerminateEvent element ) {
        return element.getBackgroundSet().getBorderColor().getValue();
    }

    @Override
    public double getBorderSize( final EndTerminateEvent element ) {
        return element.getBackgroundSet().getBorderSize().getValue();
    }

    @Override
    public String getFontFamily( final EndTerminateEvent element ) {
        return element.getFontSet().getFontFamily().getValue();
    }

    @Override
    public String getFontColor( final EndTerminateEvent element ) {
        return element.getFontSet().getFontColor().getValue();
    }

    @Override
    public double getFontSize( final EndTerminateEvent element ) {
        return element.getFontSet().getFontSize().getValue();
    }

    @Override
    public String getNamePropertyValue( final EndTerminateEvent element ) {
        return element.getGeneral().getName().getValue();
    }

    @Override
    public double getFontBorderSize( final EndTerminateEvent element ) {
        return element.getFontSet().getFontBorderSize().getValue();
    }

    @Override
    public String getGlyphBackgroundColor() {
        return EndTerminateEvent.EndTerminateEventBuilder.COLOR;
    }

    @Override
    public String getDescription() {
        return EndTerminateEvent.description;
    }

    @Override
    public Map<BasicShapeProxy<EndTerminateEvent>, HasChildren.Layout> getChildProxies() {
        return new HashMap<BasicShapeProxy<EndTerminateEvent>, HasChildren.Layout>() {{
            put( new EndNoneEventRingProxy(), HasChildren.Layout.CENTER );
        }};
    }
    
    public final class EndNoneEventRingProxy implements RingProxy<EndTerminateEvent> {

        @Override
        public double getInnerRadius( final EndTerminateEvent element ) {
            return EndTerminateEventShapeProxy.this.getRadius( element ) * 0.7;
        }

        @Override
        public double getOuterRadius( final EndTerminateEvent element ) {
            return EndTerminateEventShapeProxy.this.getRadius( element ) * 0.9;
        }

        @Override
        public String getFontFamily( final EndTerminateEvent element ) {
            return EndTerminateEventShapeProxy.this.getFontFamily( element );
        }

        @Override
        public String getFontColor( final EndTerminateEvent element ) {
            return EndTerminateEventShapeProxy.this.getFontColor( element );
        }

        @Override
        public double getFontSize( final EndTerminateEvent element ) {
            return EndTerminateEventShapeProxy.this.getFontSize( element );
        }

        @Override
        public double getFontBorderSize( final EndTerminateEvent element ) {
            return EndTerminateEventShapeProxy.this.getFontBorderSize( element );
        }

        @Override
        public String getBackgroundColor( final EndTerminateEvent element ) {
            return EndTerminateEvent.EndTerminateEventBuilder.RING_COLOR;
        }

        @Override
        public String getBorderColor( final EndTerminateEvent element ) {
            return EndTerminateEvent.EndTerminateEventBuilder.RING_COLOR;
        }

        @Override
        public double getBorderSize( final EndTerminateEvent element ) {
            return 0;
        }

        @Override
        public String getGlyphBackgroundColor() {
            return EndTerminateEvent.EndTerminateEventBuilder.RING_COLOR;
        }

        @Override
        public String getNamePropertyValue( final EndTerminateEvent element ) {
            return null;
        }

        @Override
        public String getDescription() {
            return null;
        }
        
    }
    
}