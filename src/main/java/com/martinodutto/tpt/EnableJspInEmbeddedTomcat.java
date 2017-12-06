package com.martinodutto.tpt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Workaround for a bug detected when launching the application as a main program, using spring boot's facilities.
 * The embedded tomcat is unable to resolve the JSP files since it is launched in a temporary directory.
 * Here we force it to work with the correct path.
 * <b>Don't do this at home, folks!</b>
 */
@Component
@Profile("development")
public class EnableJspInEmbeddedTomcat implements EmbeddedServletContainerCustomizer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnableJspInEmbeddedTomcat.class);

    public void customize(ConfigurableEmbeddedServletContainer container) {
        File absoluteFile = new File("src/main/webapp").getAbsoluteFile();
        if (!absoluteFile.exists() || !absoluteFile.isDirectory()) {
            throw new AssertionError(
                    "Please ensure that the directory " + absoluteFile + " exists! " +
                            "Check the working directory where you are launching the application; it should be the root of the project");
        }
        LOGGER.warn("Overriding embedded Tomcat to have a context root in {}", absoluteFile);
        container.setDocumentRoot(absoluteFile);
    }
}
