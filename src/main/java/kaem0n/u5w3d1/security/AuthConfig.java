package kaem0n.u5w3d1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig {
    @Bean
    SecurityFilterChain sfc(HttpSecurity hs) throws Exception {
        hs.formLogin(http -> http.disable());
        hs.csrf(http -> http.disable());
        hs.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        hs.authorizeHttpRequests(http -> http.requestMatchers("/**").permitAll());

        return hs.build();
    }
}
