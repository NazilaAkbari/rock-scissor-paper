package com.github.naziii.rsp.rockscissorpaper.mapper;

import com.github.naziii.rsp.rockscissorpaper.dto.PlayerDto;
import com.github.naziii.rsp.rockscissorpaper.model.Player;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/9/18
 */
public class PlayerMapper {

    public static PlayerDto toDto(Player player){
        PlayerDto playerDto=new PlayerDto();
        playerDto.setId(player.getId());
        playerDto.setRank(player.getRank());
        playerDto.setUsername(player.getUsername());
        return playerDto;
    }
}
