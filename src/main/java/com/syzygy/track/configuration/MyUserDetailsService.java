package com.syzygy.track.configuration;

import com.syzygy.track.dto.MyUserDetails;
import com.syzygy.track.repository.UserRepository;
import com.syzygy.track.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginAttemptService loginAttemptService;
    @Autowired
    private HttpServletRequest request;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }

        Optional<User> user = userRepository.findByUserName(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: "));

        return new MyUserDetails(user.get());
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
