package de.homedev.primefaces.backend.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.primefaces.api.model.BlzEntity;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface BlzDao extends JpaRepository<BlzEntity, Long>, JpaSpecificationExecutor<BlzEntity> {
    @Query("SELECT p FROM BlzEntity p WHERE p.blz LIKE :blz AND p.bic LIKE :bic AND p.bankname LIKE :bankname")
    public List<BlzEntity> find(@Param("blz") String blz, @Param("bic") String bic,
                                @Param("bankname") String bankname);

    @Query("SELECT p FROM BlzEntity p WHERE p.blz LIKE :blz AND p.bic LIKE :bic AND p.bankname LIKE :bankname")
    public List<BlzEntity> find(@Param("blz") String blz, @Param("bic") String bic,
                                @Param("bankname") String bankname, Sort sort);

    @Query("SELECT p FROM BlzEntity p WHERE p.blz LIKE :blz AND p.bic LIKE :bic AND p.bankname LIKE :bankname")
    public Page<BlzEntity> find(@Param("blz") String blz, @Param("bic") String bic,
                                @Param("bankname") String bankname, Pageable pageable);

}
