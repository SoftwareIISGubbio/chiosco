package it.edu.iisgubbio.servizitotem;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@Component
@WebFilter("/*")
public class FiltroCronometro implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FiltroCronometro.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        long time = System.currentTimeMillis();
        try {
            chain.doFilter(req, resp);
        } finally {
            time = System.currentTimeMillis() - time;
            LOGGER.info("{}: {} ms ", ((HttpServletRequest) req).getRequestURI(),  time);
        }
    }

}