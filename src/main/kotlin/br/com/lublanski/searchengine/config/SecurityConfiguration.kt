package br.com.lublanski.searchengine.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfiguration {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{

       return http
            .authorizeHttpRequests { request ->
                request.requestMatchers("/user/register").permitAll()
                    .requestMatchers("/user").hasRole("SUPERADM")
                    .requestMatchers("/role/register").hasAnyRole("ADM", "SUPERADM")
                    .requestMatchers("api/v1/teams/*").access { authentication, _ ->
                        val clientRoles =
                            (authentication as? org.springframework.security.core.Authentication)?.authorities

                        val clientHasRoleBanned = clientRoles?.any { it.authority == "ROLE_BANNED" } ?: false

                        if (clientHasRoleBanned) {
                            return@access AuthorizationDecision(false)
                        } else {
                            return@access AuthorizationDecision(true)
                        }
                    }
                    .anyRequest().authenticated()
            }
           .csrf { it.disable() }
           .formLogin { it.disable() }
           .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
           .httpBasic { }
           .build()

    }

    @Bean
    fun passwordEncoder() : PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(authConfig : AuthenticationConfiguration) : AuthenticationManager {
        return authConfig.authenticationManager
    }

}