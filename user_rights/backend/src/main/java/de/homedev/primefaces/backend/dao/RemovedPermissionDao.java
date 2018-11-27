package de.homedev.primefaces.backend.dao;

import de.homedev.primefaces.api.model.RemovedPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface RemovedPermissionDao extends JpaRepository<RemovedPermission, Long> {
   
}
