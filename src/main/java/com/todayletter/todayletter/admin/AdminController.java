package com.todayletter.todayletter.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private static final String ADMIN_PASSWORD = "0101";

    @GetMapping("/admin/login")
    public String adminLoginPage() {
        return "admin/login";
    }

    @PostMapping("/admin/login")
    public String adminLogin(@RequestParam String password, Model model) {
        if (ADMIN_PASSWORD.equals(password)) {
            return "redirect:/home?writerType=ADMIN";
        }

        model.addAttribute("hasError", true);
        return "admin/login";
    }
}
