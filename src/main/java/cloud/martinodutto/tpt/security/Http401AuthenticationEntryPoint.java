package cloud.martinodutto.tpt.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// provides an implementation that Spring Boot 2 no longer sells
public class Http401AuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final String headerValue;

    Http401AuthenticationEntryPoint(String headerValue) {
        this.headerValue = headerValue;
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {

        httpServletResponse.setHeader("WWW-Authenticate", this.headerValue);
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }
}
