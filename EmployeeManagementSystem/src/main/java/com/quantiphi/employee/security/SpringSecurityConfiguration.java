package com.quantiphi.employee.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.quantiphi.employee.repository.RoleRepository;

@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
    private Environment environment;
	
	List<String> roles;
	
	String admin;
	String manager;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		roles = roleRepository.findRoles();
		admin = roles.get(roles.indexOf(environment.getProperty("Employee.Roles.Admin"))).toUpperCase();
		manager = roles.get(roles.indexOf(environment.getProperty("Employee.Roles.Manager"))).toUpperCase();
		auth.inMemoryAuthentication()
		.withUser(environment.getProperty("Admin.username"))
		.password(environment.getProperty("Admin.password"))
		.roles(admin)
		.and()
		.withUser(environment.getProperty("Manager.username"))
		.password(environment.getProperty("Manager.password"))
		.roles(manager);
	}
	
	
	@Bean 
    public PasswordEncoder getPasswordEncoder() { 
		 return NoOpPasswordEncoder.getInstance(); 
		 }
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic()
		.and()
		.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers("/add").hasRole(manager)
		.antMatchers("/update").hasRole(manager)
		.antMatchers("/delete").hasRole(admin)
		.antMatchers("/searchFilter").hasRole(manager)
		.antMatchers("/addRoles").hasRole(admin)
		.antMatchers("/updateEmployees").hasRole(admin)
		.antMatchers("/").permitAll()
		.and().formLogin();
		
	}
	
	

}
