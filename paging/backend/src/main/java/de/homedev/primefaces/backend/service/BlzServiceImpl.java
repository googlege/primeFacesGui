package de.homedev.primefaces.backend.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.primefaces.api.model.BlzEntity;
import de.homedev.primefaces.api.model.BlzSearchDto;
import de.homedev.primefaces.backend.dao.BlzDao;
import de.homedev.primefaces.backend.service.api.IBlzService;

@Service(IBlzService.SERVICE_NAME)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BlzServiceImpl implements IBlzService {
    // private static Logger log =
    // LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private BlzDao blzDao;

    @Override
    public BlzEntity save(BlzEntity entity) {
        return blzDao.save(entity);
    }

    @Override
    public void deleteById(Long pk) {
        blzDao.deleteById(pk);

    }

    @Override
    public BlzEntity getOne(Long id) {
        BlzEntity result = blzDao.getOne(id);
        Hibernate.initialize(result);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<BlzEntity> findPage(Map<String, Object> preparedFilterMap, PageRequest request) {
        BlzSpecification specification = new BlzSpecification(preparedFilterMap);
        return blzDao.findAll(specification, request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BlzEntity> findAll(BlzSearchDto searchDto, Sort sort) {
        String blz = StringUtils.isEmpty(searchDto.getBlz()) ? "%" : searchDto.getBlz();
        String bic = StringUtils.isEmpty(searchDto.getBic()) ? "%" : searchDto.getBic();
        String bankname = StringUtils.isEmpty(searchDto.getBankname()) ? "%" : searchDto.getBankname();
        if (sort == null) {
            return blzDao.find(blz, bic, bankname);
        }
        return blzDao.find(blz, bic, bankname, sort);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BlzEntity> find(BlzSearchDto searchDto, Pageable pageable) {
        String blz = StringUtils.isEmpty(searchDto.getBlz()) ? "%" : searchDto.getBlz();
        String bic = StringUtils.isEmpty(searchDto.getBic()) ? "%" : searchDto.getBic();
        String bankname = StringUtils.isEmpty(searchDto.getBankname()) ? "%" : searchDto.getBankname();
        return blzDao.find(blz, bic, bankname, pageable);
    }

}
