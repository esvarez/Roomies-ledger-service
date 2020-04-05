package dev.ericksuarez.roomies.ledger.service.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/swag", "/swagger-ui.html", "/h2/**", "/api/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/protected")
                            .hasAuthority("SCOPE_read")
                        .anyRequest()
                            .authenticated()
                .and()
                    .oauth2ResourceServer()
                        .jwt();
    }

}