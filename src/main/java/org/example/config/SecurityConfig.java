package org.example.config;

import lombok.AllArgsConstructor;
import org.example.repository.UserRepository;
import org.example.services.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableOAuth2Client
public class SecurityConfig{

//    @Autowired
//    private AuthProvider authProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private OAuth2ClientContext oAuth2ClientContext;

    @Autowired
    private UserRepository userRepository;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    @ConfigurationProperties("google.client")
//    public AuthorizationCodeResourceDetails google() {
//        return new AuthorizationCodeResourceDetails();
//    }

//    @Bean
//    @ConfigurationProperties("google.resource")
//    public ResourceServerProperties googleResource() {
//        return new ResourceServerProperties();
//    }

//    @Bean
//    public FilterRegistrationBean oAuth2ClientFilterRegistration(OAuth2ClientContextFilter oAuth2ClientContextFilter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter((Filter) oAuth2ClientContextFilter);
//        registration.setOrder(-100);
//        return registration;
//    }

//    private Filter ssoFilter() {
//        OAuth2ClientAuthenticationProcessingFilter googleFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/google");
//        OAuth2RestTemplate googleTemplate = new OAuth2RestTemplate(google(), oAuth2ClientContext);
//        googleFilter.setRestTemplate(googleTemplate);
//        CustomUserInfoTokenServices tokenServices = new CustomUserInfoTokenServices(googleResource().getUserInfoUri(), google().getClientId());
//        tokenServices.setRestTemplate(googleTemplate);
//        googleFilter.setTokenServices(tokenServices);
//        tokenServices.setUserRepository(userRepository);
//        tokenServices.setPasswordEncoder(passwordEncoder);
//        return googleFilter;
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/resources/**", "/", "/login**", "/registration").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login")
//                .defaultSuccessUrl("/notes").failureUrl("/login?error").permitAll()
//                .and().logout().logoutSuccessUrl("/").permitAll();
//
//        http
//                .addFilterBefore(ssoFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authProvider);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/notes")
                        .failureUrl("/login?error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll());



        return http.build();
    }

}
