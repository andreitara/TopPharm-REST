package md.pharm.restservice.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by Andrei on 9/23/2015.
 */
public class NoOpAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        // TODO Auto-generated method stub
        return authentication;
    }
}
