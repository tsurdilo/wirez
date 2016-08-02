package org.wirez.core.registry.definition;

import org.uberfire.mvp.Command;
import org.wirez.core.registry.Registry;

public interface DefinitionSetRegistry<T> extends Registry<T> {

    void setLazyInitializationCallback( Command callback );

    T getDefinitionSetById( String id );

}
