package de.homedev.primefaces.frontend.testjndi;

import java.util.List;

import de.homedev.primefaces.api.fassade.IPersonFassade;
import de.homedev.primefaces.api.model.PersonEntity;
import de.homedev.primefaces.frontend.util.RmiClientUtil;

/**
 * A sample program which acts a remote client for a EJB deployed on AS7 server.
 * This program shows how to lookup stateful and stateless beans via JNDI and
 * then invoke on them
 * 
 * @author Viatcheslav Mikhalev
 */
public class PersonClientMain {

	public static void main(String[] args) throws Exception {
		IPersonFassade fassade2 = RmiClientUtil.getPersonFassade(1099, "localhost");
		List<PersonEntity> list = fassade2.findAll();
		for (PersonEntity dto : list) {
			System.out.println(dto);
		}

		PersonEntity personDto = new PersonEntity();
		personDto.setFirstName("Viatcheslav");
		personDto.setLastName("Mikhalev");
		fassade2.save(personDto);
	}

}
