package com.example.campusCart;

import com.example.campusCart.model.Role;
import com.example.campusCart.model.UserModel;
import com.example.campusCart.repository.RoleRepository;
import com.example.campusCart.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CampusCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusCartApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(
			RoleRepository roleRepository,
			UserRepository userRepository,
			PasswordEncoder passwordEncoder
	) {
		return args -> {
			// Create roles if not exist
			Role userRole = roleRepository.findByName("ROLE_USER")
					.orElseGet(() -> roleRepository.save(new Role(null, "ROLE_USER")));

			Role adminRole = roleRepository.findByName("ROLE_ADMIN")
					.orElseGet(() -> roleRepository.save(new Role(null, "ROLE_ADMIN")));

			Role superAdminRole = roleRepository.findByName("ROLE_SUPER_ADMIN")
					.orElseGet(() -> roleRepository.save(new Role(null, "ROLE_SUPER_ADMIN")));

			// Create users if not exist
			if (userRepository.findByUsername("user").isEmpty()) {
				userRepository.save(new UserModel(null, "user", passwordEncoder.encode("1234"), userRole));
			}

			if (userRepository.findByUsername("admin").isEmpty()) {
				userRepository.save(new UserModel(null, "admin", passwordEncoder.encode("1234"), adminRole));
			}

			if (userRepository.findByUsername("superadmin").isEmpty()) {
				userRepository.save(new UserModel(null, "superadmin", passwordEncoder.encode("1234"), superAdminRole));
			}
		};
	}
}