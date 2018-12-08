package cloud.martinodutto.tpt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // we have to manually deregister the JDBC drivers on context destruction because this is not automatically accomplished
    @Bean
    protected ServletContextListener listener() {

        return new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                LOGGER.info("Initializing Context...");
            }

            @Override
            public final void contextDestroyed(ServletContextEvent sce) {
                LOGGER.info("Destroying Context...");
                ClassLoader cl = Thread.currentThread().getContextClassLoader();
                Enumeration<Driver> drivers = DriverManager.getDrivers();
                while (drivers.hasMoreElements()) {
                    Driver driver = drivers.nextElement();
                    if (driver.getClass().getClassLoader() == cl) {
                        try {
                            LOGGER.info("Deregistering JDBC driver {}", driver);
                            DriverManager.deregisterDriver(driver);
                        } catch (SQLException ex) {
                            LOGGER.error("Error deregistering JDBC driver {}", driver, ex);
                        }
                    } else {
                        LOGGER.trace("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader", driver);
                    }
                }
            }
        };
    }
}
