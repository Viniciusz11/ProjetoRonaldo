package com.api.cliente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Desabilita CSRF para facilitar testes com Postman/frontend
            .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll() // Permite acesso ao endpoint de autenticação
                .anyRequest().authenticated() // Todas as outras requisições exigem autenticação
            .and()
            .formLogin(); // Ou .httpBasic() para autenticação básica
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Exemplo de usuário em memória (para testes)
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*"); // Permite todas as origens (ajuste para produção)
        config.addAllowedHeader("*"); // Permite todos os cabeçalhos
        config.addAllowedMethod("*"); // Permite todos os métodos HTTP
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}


