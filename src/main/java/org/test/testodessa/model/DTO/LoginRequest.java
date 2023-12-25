package org.test.testodessa.model.DTO;

import lombok.Data;
import org.test.testodessa.model.User;

@Data
public class LoginRequest { //I think this is a POJO, but I'm not sure, probably DTO
    private String username;
    private String password;

    public User toUser(String password) {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }
}
