package edu.vanderbilt.cs278.grouppic;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="User cannot be found")
public class UserAuthenticationError extends RuntimeException {

}
