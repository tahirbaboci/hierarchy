package com.tahir.hierarchy.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.provisioning.InMemoryUserDetailsManager


@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests().anyRequest().authenticated()
            .and().httpBasic()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService? {
        val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        val user: UserDetails = User
            .withUsername("theUsername")
            .password(encoder.encode("thePassword"))
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }
}