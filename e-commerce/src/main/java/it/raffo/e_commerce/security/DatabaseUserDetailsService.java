package it.raffo.e_commerce.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.raffo.e_commerce.model.User;
import it.raffo.e_commerce.repository.UserRepo;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            System.out.println(user.get().getUsername());
            return new DatabaseUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("Utente non trovato");
        }

    }

}
