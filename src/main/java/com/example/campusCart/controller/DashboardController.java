package com.example.campusCart.controller;
import java.io.IOException;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.campusCart.model.UserModel;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.campusCart.repository.UserRepository;

@Controller
public class DashboardController {

private final UserRepository userRepository;

public DashboardController(UserRepository userRepository) {
    this.userRepository = userRepository;
}

  @GetMapping("/dashboard/index")
public String dashboardHome(HttpServletResponse response, Principal principal) throws IOException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    String username = authentication.getName();
    String roleName = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .findFirst()
            .orElse("ROLE_UNKNOWN");

    // You would need userRepository to fetch roleId from DB:
    UserModel user = userRepository.findByUsername(username).orElseThrow();
    Long roleId = user.getRole().getId();

    if (roleId == 1L) {
        response.sendRedirect("/"); // Redirect to home
        return null;
    }

    if (roleId == 2L || roleId == 3L) {
        // Stay on /dashboard/index
        return "dashboard/index";
    }

    response.sendRedirect("/access-denied");
    return null;
}

}
