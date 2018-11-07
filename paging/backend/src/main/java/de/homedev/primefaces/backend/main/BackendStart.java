package de.homedev.primefaces.backend.main;

import java.rmi.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import de.homedev.primefaces.api.fassade.IBlzFassade;
import de.homedev.primefaces.api.fassade.IPersonFassade;
import de.homedev.primefaces.api.fassade.IUserFassade;
import de.homedev.primefaces.backend.config.DbConfig;
import de.homedev.primefaces.backend.util.RmiServerUtils;

/**
 * 
 * @author Mikhalev, Viatcheslav
 * @email slava.mikhalev@gmail.com
 * 
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "de.homedev.primefaces.api", "de.homedev.primefaces.backend"
})
@Import({
        DbConfig.class
})
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
public class BackendStart {
    private static final Logger log = LoggerFactory.getLogger(BackendStart.class);

    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext ctx = SpringApplication.run(BackendStart.class, args);
            int port = Integer.valueOf(ctx.getEnvironment().getRequiredProperty("app.rmi.port"));
            log.info("rmi server port: " + port);
            // Start RMI Server
            Registry registry = RmiServerUtils.createRegistry(port);

            IUserFassade userFassade = (IUserFassade) ctx.getBean(IUserFassade.SERVICE_NAME);
            RmiServerUtils.bindUserFassade(registry, userFassade);

            IPersonFassade personFassade = (IPersonFassade) ctx.getBean(IPersonFassade.SERVICE_NAME);
            RmiServerUtils.bindPersonFassade(registry, personFassade);

            IBlzFassade blzFassade = (IBlzFassade) ctx.getBean(IBlzFassade.SERVICE_NAME);
            RmiServerUtils.bindBlzFassade(registry, blzFassade);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    // @Bean
    // public UserDetailsService getUserDetailsService(@Autowired
    // ConfigurableApplicationContext ctx) {
    // return (UserDetailsService) ctx.getBean(IUserService.SERVICE_NAME);
    // }

}
