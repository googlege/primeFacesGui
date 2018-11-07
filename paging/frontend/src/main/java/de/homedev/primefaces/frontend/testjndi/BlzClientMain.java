package de.homedev.primefaces.frontend.testjndi;

import java.util.List;

import org.springframework.data.domain.Sort;

import de.homedev.primefaces.api.fassade.IBlzFassade;
import de.homedev.primefaces.api.model.BlzEntity;
import de.homedev.primefaces.api.model.BlzSearchDto;
import de.homedev.primefaces.frontend.config.FrontendSettings;
import de.homedev.primefaces.frontend.util.RmiClientUtil;

/**
 * A sample program which acts a remote client for a EJB deployed on AS7 server.
 * This program shows how to lookup stateful and stateless beans via JNDI and
 * then invoke on them
 * 
 * @author Viatcheslav Mikhalev
 */
public class BlzClientMain {

    public static void main(String[] args) throws Exception {
        IBlzFassade fassade2 = RmiClientUtil.getBlzFassade(FrontendSettings.getSettings());
        List<BlzEntity> list = fassade2.findAll(new BlzSearchDto(), Sort.by("id"));
        for (BlzEntity dto : list) {
            System.out.println(dto);
        }

    }

}
