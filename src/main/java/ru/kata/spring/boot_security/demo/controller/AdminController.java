package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String getAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "all-users";
    }

    @GetMapping("/showAddNewUserForm")
    public String showAddNewUserForm(Model model) {
        model.addAttribute("addUser", new User());
        model.addAttribute("roles", roleService.getRoles());
        return "add-user";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("addUser") User user) {
        userService.addUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping(value = "/showEditUserForm")
    public String showEditUserForm(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getRoles());
        return "edit-user";
    }

    @PostMapping("/saveEditUser")
    public String saveEditUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return "redirect:/admin/user";
    }
}