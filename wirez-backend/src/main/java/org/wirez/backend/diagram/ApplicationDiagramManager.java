package org.wirez.backend.diagram;

import org.wirez.core.api.AbstractDiagramManager;
import org.wirez.core.api.DiagramManager;
import org.wirez.core.backend.annotation.Application;
import org.wirez.core.backend.annotation.VFS;
import org.wirez.core.backend.registry.BackendRegistryFactory;
import org.wirez.core.diagram.Diagram;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

/**
 * The application's global diagram manager.
 */
@ApplicationScoped
@Application
public class ApplicationDiagramManager extends AbstractDiagramManager<Diagram> {

    DiagramManager<Diagram> vfsDiagramManager;
    
    protected ApplicationDiagramManager() {
    }
    
    @Inject
    public ApplicationDiagramManager( BackendRegistryFactory registryFactory,
                                     @VFS DiagramManager<Diagram> vfsDiagramManager) {
        super(registryFactory.newDiagramSynchronizedRegistry());
        this.vfsDiagramManager = vfsDiagramManager;
    }
    
    @PostConstruct
    public void initializeCache() {
        
        // Load vfs diagrams and put into the registry.
        final Collection<Diagram> diagrams = vfsDiagramManager.getItems();
        if ( null != diagrams && !diagrams.isEmpty() ) {
            for ( Diagram diagram : diagrams ) {
                this.register( diagram );
            }
        }
        
    }

    @Override
    public void update(Diagram diagram) {
        super.update(diagram);
        
        // Update the VFS storage.
        vfsDiagramManager.update( diagram );
    }

    @Override
    public void register(Diagram diagram) {
        super.register(diagram);

        // Update the VFS storage.
        vfsDiagramManager.register( diagram );
    }

    @Override
    public boolean remove(Diagram diagram) {
        super.remove(diagram);

        // Update the VFS storage.
        return vfsDiagramManager.remove( diagram );
    }
    
}
