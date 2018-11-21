package de.homedev.primefaces.backend.fassade;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.homedev.primefaces.api.exceptions.BackendException;
import de.homedev.primefaces.api.fassade.IBlzFassade;
import de.homedev.primefaces.api.model.BlzEntity;
import de.homedev.primefaces.api.model.BlzSearchDto;
import de.homedev.primefaces.api.model.UserEntity;
import de.homedev.primefaces.backend.service.api.IBlzService;

@Service(IBlzFassade.SERVICE_NAME)
public class BlzFassadeImpl implements IBlzFassade {
	private static Logger log = LoggerFactory.getLogger(UserFassadeImpl.class);

	@Autowired
	private IBlzService blzService;

	@Override
	public BlzEntity save(BlzEntity person) throws BackendException {
		try {
			return blzService.save(person);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteById(Long pk, UserEntity deletedBy) throws BackendException {
		try {
			blzService.deleteById(pk, deletedBy);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public BlzEntity getOne(Long id) throws BackendException {
		try {
			return blzService.getOne(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public Page<BlzEntity> findPage(Map<String, Object> preparedFilterMap, PageRequest request) throws RemoteException {
		try {
			return blzService.findPage(preparedFilterMap, request);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public List<BlzEntity> findAll(BlzSearchDto searchDto, Sort sort) throws RemoteException {
		try {
			return blzService.findAll(searchDto, sort);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

	@Override
	public Page<BlzEntity> find(BlzSearchDto searchDto, Pageable pageable) throws RemoteException {
		try {
			return blzService.find(searchDto, pageable);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BackendException(e.getMessage(), e);
		}
	}

}
