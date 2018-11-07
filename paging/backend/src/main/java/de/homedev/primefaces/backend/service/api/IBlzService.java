package de.homedev.primefaces.backend.service.api;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.*;

import de.homedev.primefaces.api.model.BlzEntity;
import de.homedev.primefaces.api.model.BlzSearchDto;

public interface IBlzService {
    public static final String SERVICE_NAME = "blzServiceImpl";

    public BlzEntity save(BlzEntity person);

    public void deleteById(Long pk);

    public BlzEntity getOne(Long id);

    public Page<BlzEntity> findPage(Map<String, Object> preparedFilterMap, PageRequest request);

    public List<BlzEntity> findAll(BlzSearchDto searchDto, Sort sort);

    public Page<BlzEntity> find(BlzSearchDto searchDto, Pageable pageable);
}
