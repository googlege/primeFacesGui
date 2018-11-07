package de.homedev.primefaces.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.primefaces.api.model.MandantEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface MandantDao extends JpaRepository<MandantEntity, Long> {

}
