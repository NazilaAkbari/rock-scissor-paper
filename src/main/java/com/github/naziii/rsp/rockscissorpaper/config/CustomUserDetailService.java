package com.github.naziii.rsp.rockscissorpaper.config;

import com.github.naziii.rsp.rockscissorpaper.model.Player;
import com.github.naziii.rsp.rockscissorpaper.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/8/18
 */
@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Player> playerOptional = playerRepository.findByUsername(username);
        Player player = playerOptional.orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(player.getUsername(), player.getPassword(), Collections.emptyList());
    }
}
