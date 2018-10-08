package com.github.naziii.rsp.rockscissorpaper.config;

import com.github.naziii.rsp.rockscissorpaper.model.Player;
import com.github.naziii.rsp.rockscissorpaper.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/8/18
 */
@Component
public class PlayerInitializer implements ApplicationRunner{
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerInitializer(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Player player1=new Player();
        player1.setUsername("player1");
        player1.setPassword(new BCryptPasswordEncoder().encode("1234"));
        player1.setRank(0);
        playerRepository.save(player1);

        Player player2=new Player();
        player2.setUsername("player2");
        player2.setPassword(new BCryptPasswordEncoder().encode("1234"));
        player2.setRank(0);
        playerRepository.save(player2);
    }
}
