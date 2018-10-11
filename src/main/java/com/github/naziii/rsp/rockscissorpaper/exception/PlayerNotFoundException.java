package com.github.naziii.rsp.rockscissorpaper.exception;

/**
 * @author Nazila Akbari
 * @version 1.0
 * @since 10/9/18
 */
public class PlayerNotFoundException extends RuntimeException{

    public PlayerNotFoundException(String username) {
        super(String.format("User with username %s does not exist",username));
    }
}
