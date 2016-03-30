package org.wirez.client.widgets.explorer.tree;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.uberfire.client.mvp.UberView;
import org.wirez.core.api.DefinitionManager;
import org.wirez.core.api.definition.Definition;
import org.wirez.core.api.definition.property.Property;
import org.wirez.core.api.definition.property.defaults.Name;
import org.wirez.core.api.graph.Element;
import org.wirez.core.api.graph.Node;
import org.wirez.core.api.util.ElementUtils;
import org.wirez.core.client.ShapeManager;
import org.wirez.core.client.factory.ShapeFactory;
import org.wirez.core.client.view.ShapeGlyph;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.logging.Logger;

@Dependent
public class TreeExplorerItem implements IsWidget {
    
    private static Logger LOGGER = Logger.getLogger("org.wirez.client.widgets.explorer.tree.TreeExplorerItem");

    public interface View extends UberView<TreeExplorerItem> {

        View setUUID(String uuid);

        View setName(String name);

        View setGlyph(ShapeGlyph glyph);
        
    }
    
    ShapeManager shapeManager;
    DefinitionManager definitionManager;
    View view;

    @Inject
    public TreeExplorerItem(final ShapeManager shapeManager, 
                            final DefinitionManager definitionManager, 
                            final View view) {
        this.shapeManager = shapeManager;
        this.definitionManager = definitionManager;
        this.view = view;
    }

    @PostConstruct
    public void init() {
        view.init(this);
    }

    @Override
    public Widget asWidget() {
        return view.asWidget();
    }
    
    public void show(final Element<org.wirez.core.api.graph.content.view.View> element) {
        
        final Definition definition = element.getContent().getDefinition();
        final ShapeFactory factory = shapeManager.getFactory(definition);
        
        view.setUUID( element.getUUID() )
            .setName( getItemText(element) )
            .setGlyph( factory.getGlyphFactory().build(25, 25) );
        
    }

    private String getItemText(final Element item) {
        final Property property = ElementUtils.getProperty(item, Name.ID);
        final String name= (String) definitionManager.getPropertyAdapter(property).getValue(property);
        return   ( name != null ? name : "- No name -" );
    }
    
}