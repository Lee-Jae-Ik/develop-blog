package com.lji.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * SecurityConfig
 *
 * @author Lee-Jae-Ik
 * @version 0.1
 * @see
 * @since 2022-04-08
 */
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.csrf().disable();
        http.authorizeRequests((auths) -> auths
                .antMatchers("/", "/**").permitAll());

        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .invalidateHttpSession(true);

        http.cors().configurationSource(corsConfigurationSource());
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList(
                "http://localhost:3000"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization","Cache-Control","Content-Type","Set-Cookie"));
        corsConfiguration.setAllowedMethods(Arrays.asList("POST","GET","DELETE"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Set-Cookie"));
        corsConfiguration.setAllowCredentials(true);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
