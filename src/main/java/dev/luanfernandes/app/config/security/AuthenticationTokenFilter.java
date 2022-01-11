package dev.luanfernandes.app.config.security;

import dev.luanfernandes.app.domain.entity.Customer;
import dev.luanfernandes.app.repository.CustomerRepository;
import dev.luanfernandes.app.service.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final CustomerRepository repository;

    public AuthenticationTokenFilter(TokenService tokenService, CustomerRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        boolean valid = tokenService.isValidToken(token);
        if (valid) authenticateCustomer(token);
        filterChain.doFilter(request, response);
    }

    private void authenticateCustomer(String token) {
        Long idCustomer = tokenService.getCustomerId(token);
        Customer customer = repository.findById(idCustomer).orElseThrow(() -> new ObjectNotFoundException("object not found ID=" + idCustomer));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer "))
            return null;
        return token.substring(7);
    }
}
