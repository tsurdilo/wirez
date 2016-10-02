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

package org.kie.workbench.common.stunner.basicset.definition;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.NonPortable;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.workbench.common.forms.metaModel.FieldDef;
import org.kie.workbench.common.stunner.basicset.definition.property.Name;
import org.kie.workbench.common.stunner.basicset.definition.property.background.BackgroundAndBorderSet;
import org.kie.workbench.common.stunner.basicset.definition.property.font.FontSet;
import org.kie.workbench.common.stunner.core.definition.annotation.*;
import org.kie.workbench.common.stunner.core.definition.annotation.definition.*;
import org.kie.workbench.common.stunner.basicset.definition.property.IconType;
import org.kie.workbench.common.stunner.basicset.definition.property.Radius;
import org.kie.workbench.common.stunner.basicset.shape.proxy.PolygonWithIconProxy;
import org.kie.workbench.common.stunner.core.definition.builder.Builder;
import org.kie.workbench.common.stunner.core.factory.graph.NodeFactory;
import org.kie.workbench.common.stunner.shapes.factory.BasicShapesFactory;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Portable
@Bindable
@Definition( graphFactory = NodeFactory.class, builder = PolygonWithIcon.PolygonWithIconBuilder.class )
@Shape( factory = BasicShapesFactory.class, proxy = PolygonWithIconProxy.class )
public class PolygonWithIcon {

    @Category
    public static final transient String category = Categories.BASIC_WITH_ICONS;

    @Title
    public static final transient String title = "Polygon with icon";

    @Description
    public static final transient String description = "A polygon with an icon";

    @Property
    @FieldDef( label = "Name", property = "value" )
    @Valid
    private Name name;

    @PropertySet
    @FieldDef( label = "Background and Borders", position = 0 )
    @Valid
    private BackgroundAndBorderSet backgroundSet;

    @PropertySet
    @FieldDef( label = "Font", position = 1 )
    @Valid
    private FontSet fontSet;

    @Property
    @FieldDef( label = "Radius", property = "value" )
    @Valid
    private Radius radius;

    @Property
    @FieldDef( label = "Icon Type", property = "value" )
    @Valid
    private IconType iconType;

    @Labels
    private final Set<String> labels = new HashSet<String>() {{
        add( "all" );
    }};

    @NonPortable
    public static class PolygonWithIconBuilder implements Builder<PolygonWithIcon> {

        public static final String COLOR = "#00FF66";
        public static final String BORDER_COLOR = "#000000";
        public static final Double RADIUS = 50d;
        public static final Double BORDER_SIZE = 1d;

        @Override
        public PolygonWithIcon build() {
            return new PolygonWithIcon( new Name( "Polygon With Icon" ),
                    new BackgroundAndBorderSet( COLOR, BORDER_COLOR, BORDER_SIZE ),
                    new FontSet(),
                    new Radius( RADIUS ),
                    new IconType() );
        }

    }

    public PolygonWithIcon() {

    }

    public PolygonWithIcon(@MapsTo("name") Name name,
                           @MapsTo("backgroundSet") BackgroundAndBorderSet backgroundSet,
                           @MapsTo("fontSet") FontSet fontSet,
                           @MapsTo("radius") Radius radius,
                           @MapsTo("iconType") IconType iconType) {
        this.name = name;
        this.backgroundSet = backgroundSet;
        this.fontSet = fontSet;
        this.radius = radius;
        this.iconType = iconType;

    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public BackgroundAndBorderSet getBackgroundSet() {
        return backgroundSet;
    }

    public void setBackgroundSet(BackgroundAndBorderSet backgroundSet) {
        this.backgroundSet = backgroundSet;
    }

    public FontSet getFontSet() {
        return fontSet;
    }

    public void setFontSet(FontSet fontSet) {
        this.fontSet = fontSet;
    }

    public Radius getRadius() {
        return radius;
    }

    public void setRadius(Radius radius) {
        this.radius = radius;
    }

    public IconType getIconType() {
        return iconType;
    }

    public void setIconType(IconType iconType) {
        this.iconType = iconType;
    }
}