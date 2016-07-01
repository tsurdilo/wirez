package org.wirez.client.widgets.palette.view;

import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.widget.LienzoPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import org.wirez.client.widgets.palette.AbstractPaletteWidget;
import org.wirez.core.client.components.glyph.ShapeGlyphDragHandler;
import org.wirez.core.client.components.palette.view.PaletteView;
import org.wirez.core.client.shape.view.ShapeGlyph;

public abstract class AbstractPaletteWidgetView
        extends Composite
        implements PaletteWidgetView {

    ShapeGlyphDragHandler<LienzoPanel, Group> shapeGlyphDragHandler;

    protected LienzoPanel lienzoPanel;
    protected final Layer lienzoLayer = new Layer().setTransformable(true);
    protected AbstractPaletteWidget presenter;
    protected PaletteView paletteView;
    protected final Rectangle background = new Rectangle( 1, 1 ).setFillAlpha( 0 ).setStrokeAlpha( 0 );

    public AbstractPaletteWidgetView( final ShapeGlyphDragHandler<LienzoPanel, Group> shapeGlyphDragHandler ) {
        this.shapeGlyphDragHandler = shapeGlyphDragHandler;
    }

    protected abstract Panel getParentPanel();

    public AbstractPaletteWidgetView setPresenter(final AbstractPaletteWidget presenter) {
        this.presenter = presenter;
        return this;
    }

    @Override
    public void setBackgroundColor( final String color ) {
        background.setFillAlpha( 1 ).setFillColor( color );
    }

    @Override
    @SuppressWarnings("unchecked")
    public void showDragProxy( final String itemId,
                               final double x,
                               final double y ) {

        final ShapeGlyph<Group> glyph = presenter.getShapeGlyph( itemId );

        shapeGlyphDragHandler.show( lienzoPanel, glyph, x, y, new ShapeGlyphDragHandler.Callback<LienzoPanel>() {
            @Override
            public void onMove( final LienzoPanel floatingPanel,
                                final double x,
                                final double y) {

                presenter.onDragProxyMove( itemId, x, y );

            }

            @Override
            public void onComplete( final LienzoPanel floatingPanel,
                                    final double x,
                                    final double y ) {

                presenter.onDragProxyComplete( itemId, x, y  );

            }
        });

    }

    @Override
    @SuppressWarnings("unchecked")
    public void show( final PaletteView paletteView ) {
        this.show( paletteView, (int) paletteView.getWidth() + 10, (int) paletteView.getHeight() + 10 );
    }

    @Override
    @SuppressWarnings("unchecked")
    public void show( final PaletteView paletteView,
                            final int _width,
                            final int _height ) {

        final int width = _width > -1 ? _width : (int) paletteView.getWidth();
        final int height = _height > -1 ? _height : (int) paletteView.getHeight();

        beforeShow( paletteView, width, height );

        initLienzoPanel( width, height );

        this.paletteView = paletteView;
        this.paletteView.setX( 0 );
        this.paletteView.setY( 0 );
        this.paletteView.attach( lienzoLayer );

        this.paletteView.show();

    }

    protected void beforeShow( final PaletteView paletteView,
                               final int width,
                               final int height ) {
    }

    @Override
    public void setPaletteSize( final int width,
                                final int height ) {

        if ( null != lienzoPanel ) {

            initLienzoPanel( width, height );

        }

    }

    protected void initLienzoPanel(final int width,
                                   final int height ) {

        if ( null != lienzoPanel ) {

            destroyLienzoStuff();

        }

        lienzoPanel = new LienzoPanel( width, height );

        lienzoPanel.add( lienzoLayer );

        lienzoLayer.add( background.setWidth( width ).setHeight( height ) );

        getParentPanel().add( lienzoPanel );

    }

    @Override
    public void clear() {
        if ( null != paletteView ) {
            paletteView.clear();
        }
    }

    @Override
    public void destroy() {
        if ( null != paletteView ) {
            paletteView.destroy();
            destroyLienzoStuff();
        }
    }



    protected void destroyLienzoStuff() {

        lienzoLayer.clear();
        lienzoLayer.removeFromParent();

        if ( null != lienzoPanel ) {

            lienzoPanel.clear();
            lienzoPanel.removeFromParent();
            lienzoPanel = null;
        }

        getParentPanel().clear();

    }

}