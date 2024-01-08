package com.security.web3.controller;

import com.security.web3.consts.ResultCode;
import com.security.web3.exception.DataNotFoundException;
import com.security.web3.service.UserService;
import com.security.web3.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Security;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public String deleteUser(@RequestBody String inputPassword, Model model) {
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String nowPassword = userService.getEncodedPassword(username);
            inputPassword = inputPassword.substring(9, inputPassword.indexOf("&_csrf="));
            if (!bCryptPasswordEncoder.matches(inputPassword, nowPassword)) {
                return "redirect:/user/withdrawform?code=" + ResultCode.MISMATCH_PASSWORD.value();
            }
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
    public String checkPassword(@RequestBody String inputPassword) {
        // Question
        //String nowPassword = userService.getUserByUsername(username).getPassword();
        //String password = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nowPassword = userService.getEncodedPassword(username);
        inputPassword = inputPassword.substring(9, inputPassword.indexOf("&_csrf="));
        if (bCryptPasswordEncoder.matches(inputPassword, nowPassword))
            return "redirect:/user/updateform";
        return "redirect:/user/pwcheckform?code=" + ResultCode.MISMATCH_PASSWORD.value();
    }

    @GetMapping("/user/withdrawform")
    public String withdrawform() {
        return "withdrawform";
    }

    @GetMapping("/checkusername")
    public @ResponseBody Integer checkDuplicateUsername(@RequestParam String username) {
        return userService.countDuplicateUsername(username);
    }

    @GetMapping("/checkemail")
    public @ResponseBody Integer checkDuplicateEmail(@RequestParam String email) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (username.equals("anonymousUser"))
            username = null;
        return userService.countDuplicateEmail(username, email);
    }

    @PostMapping("/checknowpw")
    public @ResponseBody boolean checkNowPassword(@RequestBody String inputPassword) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nowPassword = userService.getEncodedPassword(username);
        inputPassword = inputPassword.substring(12);
        return bCryptPasswordEncoder.matches(inputPassword, nowPassword);
    }
}
