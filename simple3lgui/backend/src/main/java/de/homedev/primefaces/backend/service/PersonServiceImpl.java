package de.homedev.primefaces.backend.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.homedev.primefaces.api.model.PersonEntity;
import de.homedev.primefaces.api.model.SearchDto;
import de.homedev.primefaces.backend.dao.PersonDao;
import de.homedev.primefaces.backend.service.api.IPersonService;

@Service(IPersonService.SERVICE_NAME)
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PersonServiceImpl implements IPersonService {
	// private static Logger log =
	// LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	private PersonDao personDao;

	@Override
	public PersonEntity save(PersonEntity entity) {
		return personDao.save(entity);
	}

	@Override
	public void deleteById(Long pk) {
		personDao.deleteById(pk);

	}

	@Transactional(readOnly = true)
	@Override
	public List<PersonEntity> findAll() {
		return personDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)

	public Page<PersonEntity> findAll(Pageable pageable) {
		return personDao.findAll(pageable);
	}

	@Override
	public PersonEntity getOne(Long id) {
		PersonEntity result = personDao.getOne(id);
		Hibernate.initialize(result);
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonEntity> findAll(SearchDto searchDto) {
		String firstName = StringUtils.isEmpty(searchDto.getFirstName()) ? "%" : searchDto.getFirstName();
		String lastName = StringUtils.isEmpty(searchDto.getLastName()) ? "%" : searchDto.getLastName();
		return personDao.findByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PersonEntity> findAll(SearchDto searchDto, Pageable pageable) {
		String firstName = StringUtils.isEmpty(searchDto.getFirstName()) ? "%" : searchDto.getFirstName();
		String lastName = StringUtils.isEmpty(searchDto.getLastName()) ? "%" : searchDto.getLastName();
		return personDao.findByFirstNameAndLastName(firstName, lastName, pageable);
	}

}
