package org.example.myvoc.config;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.*;
import java.io.IOException;

@Setter
@Getter
public class CharacterEncodingFilter implements Filter {

    private String encoding;
    private boolean forceEncoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Read the encoding from the filter configuration
        encoding = filterConfig.getInitParameter("requestEncoding");

        if( encoding==null ) encoding="UTF-8";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain       next)
            throws IOException, ServletException
    {
        // Respect the client-specified character encoding
        // (see HTTP specification section 3.4.1)
        if(null == request.getCharacterEncoding())
            request.setCharacterEncoding(encoding);


        /**
         * Set the default response content type and encoding
         */
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");


        next.doFilter(request, response);
    }

    public void setForceEncoding(boolean forceEncoding) {
        this.forceEncoding = forceEncoding;
    }
}

