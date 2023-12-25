package org.test.testodessa.model.DTO;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {
    String token;
    String message;
}

