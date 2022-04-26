package com.jamesw.concurtv.service;

import com.jamesw.concurtv.model.User;
import com.jamesw.concurtv.model.UserPrincipal;
import com.jamesw.concurtv.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository userRepo;

    public UserDetailsLoader(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserPrincipal(user);
    }
}