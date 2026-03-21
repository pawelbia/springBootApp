package springboot.library.security.config;

import lombok.AllArgsConstructor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import springboot.library.service.AppUserService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AppUserService appUserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher("/h2-console/**"),
                                new AntPathRequestMatcher("/api/v1/registration"),
                                new AntPathRequestMatcher("/api/v1/registration/**")
                        )
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/api/v1/registration","/api/v1/registration/**").permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                );

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(appUserService);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return  daoAuthenticationProvider;
    }
}
