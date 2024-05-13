package com.pbl5.filter;

import com.pbl5.configs.ResponseConfig;
import com.pbl5.helpers.respone.Message;
import com.pbl5.helpers.respone.Meta;
import com.pbl5.utils.constants.Endpoint;
import com.pbl5.utils.exceptions.ErrorHandler;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {Endpoint.V1 + Endpoint.ADMIN + "/*" })
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if ("OPTIONS".equals(req.getMethod())) {
            ResponseConfig.ConfigHeader((HttpServletResponse) response);
            res.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        if (CheckRole.check(req, "ADM")) {
            filterChain.doFilter(request, response);
        } else {
            ResponseConfig.ConfigHeader(res);
            Meta meta = new Meta.Builder(HttpServletResponse.SC_FORBIDDEN).withMessage("Forbidden!").build();
            ErrorHandler.handle(res, new Message.Builder(meta).build());
        }
    }

    @Override
    public void destroy() {

    }
}
