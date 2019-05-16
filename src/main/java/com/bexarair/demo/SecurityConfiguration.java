//package com.bexarair.demo;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    private UserDetailsLoader usersLoader;
//
//    public SecurityConfiguration(UserDetailsLoader usersLoader) {
//        this.usersLoader = usersLoader;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(usersLoader) // How to find users by their username
//                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
//        ;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                /* Login configuration */
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/posts/profile") // user's home page, it can be any URL
//                .permitAll() // Anyone can go to the login page
//                /* Logout configuration */
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login?logout") // append a query string value
//                /* Pages that can be viewed without having to log in */
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/posts") // anyone can see the home and the post pages
//                .permitAll()
//                /* Pages that require authentication */
//                .and()
//                .authorizeRequests()
//                .antMatchers(
//                        "/posts/create", // only authenticated users can create ads
//                        "/posts/edit/{id}", // only authenticated users can edit ads
//                        "/posts/profile",
//                        "/posts/delete/{id}"
//
//                )
//                .authenticated()
//        ;
//    }
//}