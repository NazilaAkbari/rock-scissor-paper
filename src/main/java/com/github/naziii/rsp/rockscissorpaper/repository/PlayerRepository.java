package com.github.naziii.rsp.rockscissorpaper.repository;

import com.github.naziii.rsp.rockscissorpaper.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/8/18
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByUsername(String username);
}
