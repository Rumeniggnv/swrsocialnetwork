package com.letscode.SWRSocialNetwork.service;


import com.letscode.SWRSocialNetwork.model.Rebel;
import com.letscode.SWRSocialNetwork.repository.RebelRepository;
import com.letscode.SWRSocialNetwork.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@AllArgsConstructor
@Service
public class SecurityServiceImpl implements SecurityService, UserDetailsService {

    private final RebelRepository rebelRepository;

    private final AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public String authenticate(final String rebelName, final String password) {
        final Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(rebelName, password)
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final Rebel rebel = this.loadUserByUsername(rebelName);

        return JWTUtils.generateToken(rebel.getId(), rebel.getName(), rebel.getRole());
    }

    @Transactional
    @Override
    public void authenticate(final String token) {
        final Claims claims = JWTUtils.parseToken(token);

        Rebel user = new Rebel();
        user.setId(Long.parseLong(claims.getSubject()));
        user.setPassword("");
        user.setName(claims.get(JWTUtils.TOKEN_CLAIM_USERNAME).toString());
        user.setRole(claims.get(JWTUtils.TOKEN_CLAIM_ROLES).toString());

        // Setting up Authentication...
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())
        );
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    @Override
    public Rebel getCurrentRebel() {
        return (Rebel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Transactional
    @Override
    public Rebel loadUserByUsername(String username) throws UsernameNotFoundException {
        return rebelRepository.findByName( username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid rebel name or password."));
    }

}
