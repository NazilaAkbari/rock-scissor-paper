package com.github.naziii.rsp.rockscissorpaper.dto;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/9/18
 */
public class PlayerDto {
    private Long id;
    private String username;
    private Integer rank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
