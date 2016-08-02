package org.wirez.backend.service;

import org.jboss.errai.bus.server.annotations.Service;
import org.wirez.core.api.DiagramManager;
import org.wirez.core.diagram.Diagram;
import org.wirez.core.backend.annotation.Tenant;
import org.wirez.core.remote.DiagramService;

import javax.inject.Inject;
import java.util.Collection;

@Service
public class TenantDiagramService implements DiagramService {
    
    DiagramManager<Diagram> tenantDiagramManager;

    @Inject
    public TenantDiagramService(@Tenant DiagramManager<Diagram> tenantDiagramManager) {
        this.tenantDiagramManager = tenantDiagramManager;
    }

    @Override
    public void saveOrUpdate(Diagram diagram) {

        if ( contains( diagram ) ) {
            
            update( diagram);
            
        } else {

            register( diagram );
            
        }
        
    }
    
    @Override
    public void update(Diagram diagram) {
        tenantDiagramManager.update( diagram );
    }

    @Override
    public void register(Diagram diagram) {
        tenantDiagramManager.register( diagram );
    }

    @Override
    public boolean contains(Diagram diagram) {
        return tenantDiagramManager.contains( diagram );
    }

    @Override
    public boolean remove(Diagram diagram) {
        return tenantDiagramManager.remove( diagram );
    }

    @Override
    public Diagram getDiagramByUUID(String criteria) {
        return tenantDiagramManager.getDiagramByUUID( criteria );
    }

    @Override
    public Collection<Diagram> getItems() {
        return tenantDiagramManager.getItems();
    }

    @Override
    public void clear() {
        tenantDiagramManager.clear();
    }

    
}
