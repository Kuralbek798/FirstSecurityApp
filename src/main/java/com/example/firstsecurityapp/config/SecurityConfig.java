
package com.example.firstsecurityapp.config;



import com.example.firstsecurityapp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

 private final PersonDetailsService personDetailsService;



 @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }


    //конфигурируем сам Spring Security
    //конфигурируем авторизацию
    @Override
    protected void configure(HttpSecurity http) throws Exception{
      http.csrf().disable()
              .authorizeRequests()
              .antMatchers("/auth/login","/error","/auth/registration").permitAll()
              .anyRequest().authenticated()
              .and()
              .formLogin().loginPage("/auth/login")
              .loginProcessingUrl("/process_login")
              .defaultSuccessUrl("/hello",true)
              .failureUrl("/auth/login?error")
              .and()
              .logout().logoutUrl("/logout")
              .logoutUrl("/auth/login");
    }

//Настраиваем аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
     return NoOpPasswordEncoder.getInstance();
    }
}
