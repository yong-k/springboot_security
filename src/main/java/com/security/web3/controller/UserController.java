package com.security.web3.controller;

import com.security.web3.consts.ResultCode;
import com.security.web3.exception.DataNotFoundException;
import com.security.web3.service.UserService;
import com.security.web3.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/loginform")
    public String loginform() {
        return "loginform";
    }

    @GetMapping("/joinform")
    public String joinform() {
        return "joinform";
    }

    @PostMapping("/join")
    public String createUser(Model model, UserVo user) {
        try {
            userService.createUser(user);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("code", ResultCode.DATA_INTEGRITY_VIOLATION.value());
            return "error/error";
        } catch (Exception e) {
            model.addAttribute("code", ResultCode.UNKNOWN_ERROR.value());
            log.error("Error in UserController.createUser()", e);
            return "error/error";
        }
        return "redirect:/joinform?code=" + ResultCode.JOIN_COMPLETE.value();
    }

    @GetMapping("/user/info")
    public String getUserInfo(Model model) {
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserVo user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
        } catch (DataNotFoundException e) {
            model.addAttribute("code", ResultCode.DATA_NOT_FOUND.value());
            return "error/error";
        } catch (Exception e) {
            model.addAttribute("code", ResultCode.UNKNOWN_ERROR.value());
            log.error("Error in UserController.getUserInfo()", e);
            return "error/error";
        }
        return "userInfo";
    }

    @GetMapping("/user/updateform")
    public String updateform(Model model) {
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserVo user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
        } catch (DataNotFoundException e) {
            model.addAttribute("code", ResultCode.DATA_NOT_FOUND.value());
            return "error/error";
        } catch (Exception e) {
            model.addAttribute("code", ResultCode.UNKNOWN_ERROR.value());
            log.error("Error in UserController.updateform()", e);
            return "error/error";
        }
        return "updateform";
    }

    @PostMapping("/user/update")
    public String updateUser(Model model, UserVo user) {
        try {
            userService.updateUser(user);
        } catch (DataNotFoundException e) {
            model.addAttribute("code", ResultCode.DATA_NOT_FOUND.value());
            return "error/error";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("code", ResultCode.DATA_INTEGRITY_VIOLATION.value());
            return "error/error";
        } catch (Exception e) {
            model.addAttribute("code", ResultCode.UNKNOWN_ERROR.value());
            log.error("Error in UserController.updateUser()", e);
            return "error/error";
        }
        return "redirect:/user/info";
    }

    @PostMapping("/user/delete")
    public String deleteUser(@RequestParam("password") String inputPassword, Model model) {
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!userService.isMatchPassword(username, inputPassword))
                return "redirect:/user/withdrawform?code=" + ResultCode.MISMATCH_PASSWORD.value();
            userService.deleteUser(username);
            model.addAttribute("code", 2);
        } catch (DataNotFoundException e) {
            model.addAttribute("code", ResultCode.DATA_NOT_FOUND.value());
            return "error/error";
        } catch (Exception e) {
            model.addAttribute("code", ResultCode.UNKNOWN_ERROR.value());
            log.error("Error in UserController.deleteUser()", e);
            return "error/error";
        }
        return "redirect:/user/withdrawform?code=" + ResultCode.WITHDRAW_COMPLETE.value();
    }

    @GetMapping("/user/pwcheckform")
    public String pwcheckform() {
        return "pwCheck";
    }

    @PostMapping("/user/checkpw")
    public String checkPassword(@RequestParam("password") String inputPassword, Model model) {
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (userService.isMatchPassword(username, inputPassword))
                return "redirect:/user/updateform";
        } catch (Exception e) {
            model.addAttribute("code", ResultCode.UNKNOWN_ERROR.value());
            log.error("Error in UserController.checkPassword()", e);
            return "error/error";
        }
        return "redirect:/user/pwcheckform?code=" + ResultCode.MISMATCH_PASSWORD.value();
    }

    @GetMapping("/user/withdrawform")
    public String withdrawform() {
        return "withdrawform";
    }

    @GetMapping("/checkusername")
    public @ResponseBody boolean checkDuplicateUsername(@RequestParam String username) {
        return userService.countDuplicateUsername(username) <= 0;
    }

    @GetMapping("/checkemail")
    public @ResponseBody boolean checkDuplicateEmail(@RequestParam String email) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> role = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (role.contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS")))
            username = null;
        return userService.countDuplicateEmail(username, email) <= 0;
    }

    @PostMapping("/checknowpw")
    public @ResponseBody boolean checkNowPassword(@RequestParam("nowPassword") String inputPassword) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.isMatchPassword(username, inputPassword);
    }
}
