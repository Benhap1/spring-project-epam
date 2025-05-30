package com.example.book.service.service;

import com.example.book.service.repo.ClientRepository;
import com.example.book.service.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clientRepository.findByEmail(email)
                .map(client -> (UserDetails) client)
                .orElseGet(() ->
                        employeeRepository.findByEmail(email)
                                .map(emp -> (UserDetails) emp)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"))
                );
    }
}
