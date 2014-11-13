package organizer.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class ResourceExecutionTimeLoggingFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceExecutionTimeLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        LOG.info("Begin service call");
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        LOG.info("Service call took " + (end - start) + "ms");
    }

    @Override
    public void destroy() {

    }
}
