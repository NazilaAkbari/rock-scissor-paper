package com.github.naziii.rsp.rockscissorpaper.controller;

import com.github.naziii.rsp.rockscissorpaper.dto.PlayerDto;
import com.github.naziii.rsp.rockscissorpaper.exception.PlayerNotFoundException;
import com.github.naziii.rsp.rockscissorpaper.mapper.PlayerMapper;
import com.github.naziii.rsp.rockscissorpaper.repository.PlayerRepository;
import com.github.naziii.rsp.rockscissorpaper.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/9/18
 */
@RestController
public class PlayerController {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/playerinfo")
    public ResponseEntity<PlayerDto> getPlayerInfo() {
        return ResponseEntity.ok(PlayerMapper.toDto(playerRepository
                .findByUsername(SecurityUtil.currentUser()).orElseThrow(() ->
                        new PlayerNotFoundException(SecurityUtil.currentUser()))));
    }
}
