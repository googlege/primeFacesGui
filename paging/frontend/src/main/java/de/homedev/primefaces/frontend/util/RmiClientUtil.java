package de.homedev.primefaces.frontend.util;

import javax.naming.NamingException;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import de.homedev.primefaces.api.fassade.IBlzFassade;
import de.homedev.primefaces.api.fassade.IPersonFassade;
import de.homedev.primefaces.api.fassade.IUserFassade;
import de.homedev.primefaces.frontend.config.FrontendSettings;

public final class RmiClientUtil {
    private RmiClientUtil() {
    }

    private static IUserFassade userFassade;
    private static IPersonFassade personFassade;
    private static IBlzFassade blzFassade;

    public static IUserFassade getUserFassade(FrontendSettings settings) throws NamingException {
        if (userFassade == null) {
            RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
            rmiProxyFactory
                .setServiceUrl("rmi://" + settings.getRmiHost() + ':' + settings.getRmiPort() + '/' + IUserFassade.RMI_SERVICE_NAME);
            rmiProxyFactory.setServiceInterface(IUserFassade.class);
            rmiProxyFactory.afterPropertiesSet();
            userFassade = (IUserFassade) rmiProxyFactory.getObject();
        }
        return userFassade;
    }

    public static IPersonFassade getPersonFassade(FrontendSettings settings) throws NamingException {
        if (personFassade == null) {
            RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
            rmiProxyFactory
                .setServiceUrl("rmi://" + settings.getRmiHost() + ':' + settings.getRmiPort() + '/' + IPersonFassade.RMI_SERVICE_NAME);
            rmiProxyFactory.setServiceInterface(IPersonFassade.class);
            rmiProxyFactory.afterPropertiesSet();
            personFassade = (IPersonFassade) rmiProxyFactory.getObject();
        }
        return personFassade;
    }

    public static IBlzFassade getBlzFassade(FrontendSettings settings) throws NamingException {
        if (blzFassade == null) {
            RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
            rmiProxyFactory
                .setServiceUrl("rmi://" + settings.getRmiHost() + ':' + settings.getRmiPort() + '/' + IBlzFassade.RMI_SERVICE_NAME);
            rmiProxyFactory.setServiceInterface(IBlzFassade.class);
            rmiProxyFactory.afterPropertiesSet();
            blzFassade = (IBlzFassade) rmiProxyFactory.getObject();
        }
        return blzFassade;
    }
}
