package org.kie.workbench.common.stunner.bpmn.shape.def;

import org.kie.workbench.common.stunner.bpmn.definition.BPMNDiagram;
import org.kie.workbench.common.stunner.core.definition.shape.AbstractShapeDef;
import org.kie.workbench.common.stunner.shapes.def.RectangleShapeDef;

public final class BPMNDiagramShapeDef
        extends AbstractShapeDef<BPMNDiagram>
        implements RectangleShapeDef<BPMNDiagram> {
    
    @Override
    public String getBackgroundColor( final BPMNDiagram element ) {
        return element.getBackgroundSet().getBgColor().getValue();
    }

    @Override
    public double getBackgroundAlpha( final BPMNDiagram element ) {
        return 0.8;
    }

    @Override
    public String getBorderColor( final BPMNDiagram element ) {
        return element.getBackgroundSet().getBorderColor().getValue();
    }

    @Override
    public double getBorderSize( final BPMNDiagram element ) {
        return element.getBackgroundSet().getBorderSize().getValue();
    }

    @Override
    public double getBorderAlpha( final BPMNDiagram element ) {
        return 1;
    }

    @Override
    public String getFontFamily( final BPMNDiagram element ) {
        return element.getFontSet().getFontFamily().getValue();
    }

    @Override
    public String getFontColor( final BPMNDiagram element ) {
        return element.getFontSet().getFontColor().getValue();
    }

    @Override
    public double getFontSize( final BPMNDiagram element ) {
        return element.getFontSet().getFontSize().getValue();
    }

    @Override
    public String getNamePropertyValue( final BPMNDiagram element ) {
        return element.getGeneral().getName().getValue();
    }

    @Override
    public double getFontBorderSize( final BPMNDiagram element ) {
        return element.getFontSet().getFontBorderSize().getValue();
    }

    @Override
    public String getGlyphBackgroundColor( final BPMNDiagram element ) {
        return element.getBackgroundSet().getBgColor().getValue();
    }
    @Override
    public double getWidth( final BPMNDiagram element ) {
        return element.getDimensionsSet().getWidth().getValue();
    }

    @Override
    public double getHeight( final BPMNDiagram element ) {
        return element.getDimensionsSet().getHeight().getValue();
    }

}
