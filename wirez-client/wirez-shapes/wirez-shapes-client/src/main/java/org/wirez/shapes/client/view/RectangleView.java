package org.wirez.shapes.client.view;

import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.Shape;
import com.ait.lienzo.client.core.shape.wires.WiresLayoutContainer;
import com.ait.lienzo.client.core.shape.wires.WiresManager;
import org.wirez.core.client.shape.view.HasSize;

public class RectangleView<T extends RectangleView> extends BasicPrimitiveShapeView<T>
        implements HasSize<T> {

    private Rectangle rectangle;

    public RectangleView( final double width,
                          final double height ) {
        
        super(new MultiPath().rect(0, 0, width, height) );
    }

    @Override
    protected Shape getPrimitive() {
        return rectangle;
    }

    @Override
    protected Shape<?> createChildren() {

        // The main rectangle shape. Avoid using the multipath as main shape because it does not provide easy
        // support for animations, corner radius, etc.
        this.rectangle = new Rectangle( 1, 1 ).setCornerRadius( 5 );
        this.addChild( rectangle, WiresLayoutContainer.Layout.CENTER );

        // Another rectangle shape used as decorator.
        final Rectangle decorator = new Rectangle( 1, 1 ).setCornerRadius( 5 );
        this.addChild( decorator, WiresLayoutContainer.Layout.CENTER );

        return decorator;
    }

    @Override
    public T setSize( final double width,
                      final double height ) {
        return super.setSize( width ,height );
    }

    @Override
    protected void doDestroy() {
        super.doDestroy();
        rectangle = null;
    }
    
}
