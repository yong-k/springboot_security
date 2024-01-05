package com.security.web3.controller;

import com.security.web3.security.PrincipalDetails;
import com.security.web3.consts.ResultCode;
import com.security.web3.exception.DataNotFoundException;
import com.security.web3.service.UserService;
import com.security.web3.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userService.createUser(user);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("code", ResultCode.DATA_INTEGRITY_VIOLATION.value());
            return "error/error";
        } catch (Exception e) {
            model.addAttribute("code", ResultCode.UNKNOWN_ERROR.value());
            log.error("Error in UserController.createUser()", e);
            return "error/error";
        }
        return "redirect:/loginform?code=" + ResultCode.JOIN_COMPLETE.value();
    }

    @GetMapping("/user/info")
    public String getUserInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails userDetails = (UserDetails) principal;

            System.out.println("getPrincipal() = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            System.out.println("getCredentials() = " + SecurityContextHolder.getContext().getAuthentication().getCredentials());
            System.out.println("getautho() = " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            System.out.println("userDetails.getUsername() : " + userDetails.getUsername());
            System.out.println("userDetails.getAuthorities() : " + userDetails.getAuthorities());

            UserVo user = userService.getUserById(principalDetails.getId());
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
    public String updateform(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        try {
            UserVo user = userService.getUserById(principalDetails.getId());
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
    public String updateUser(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model, UserVo user) {
        try {
            String encodedPassword = "";
            System.out.println(user.getPassword().isEmpty());
            if (user.getPassword().isEmpty()) {
                encodedPassword = principalDetails.getPassword();
            } else {
                encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
                principalDetails.setPassword(encodedPassword);
            }
            user.setId(principalDetails.getId());
            user.setPassword(encodedPassword);
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
    public String deleteUser(@RequestBody String password, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        try {
            String inputPw = password.substring(9);
            if (!bCryptPasswordEncoder.matches(inputPw, principalDetails.getPassword()))
                return "redirect:/user/withdrawform?code=" + ResultCode.MISMATCH_PASSWORD.value();
            userService.deleteUser(principalDetails.getId());
            model.addAttribute("code", 2);
        } catch (DataNotFoundException e) {
            model.addAttribute("code", ResultCode.DATA_NOT_FOUND.value());
            return "error/error";
        } catch (Exception e) {
            model.addAttribute("code", ResultCode.UNKNOWN_ERROR.value());
            log.error("Error in UserController.deleteUser()", e);
            return "error/error";
        }
        return "redirect:/?code=" + ResultCode.WITHDRAW_COMPLETE.value();
    }

    @GetMapping("/user/pwcheckform")
    public String pwcheckform() {
        return "pwCheck";
    }

    @PostMapping("/user/checkpw")
    public String checkPassword(@RequestBody String password, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String inputPw = password.substring(9);
        if (bCryptPasswordEncoder.matches(inputPw, principalDetails.getPassword()))
            return "redirect:/user/updateform";
        return "redirect:/user/pwcheckform?code=" + ResultCode.MISMATCH_PASSWORD.value();
    }

    @GetMapping("/user/withdrawform")
    public String withdrawform() {
        return "withdrawform";
    }

    @GetMapping("/checkusername")
    public @ResponseBody Integer checkDuplicateUsername(@RequestParam String username, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long id = null;
        if (principalDetails != null)
            id = principalDetails.getId();
        return userService.countDuplicateUsername(id, username);
    }

    @GetMapping("/checkemail")
    public @ResponseBody Integer checkDuplicateEmail(@RequestParam String email, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long id = null;
        if (principalDetails != null)
            id = principalDetails.getId();
        return userService.countDuplicateEmail(id, email);
    }

    @PostMapping("/checknowpw")
    public @ResponseBody boolean checkNowPassword(@RequestBody String nowPassword, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String inputPw = nowPassword.substring(12);
        return bCryptPasswordEncoder.matches(inputPw, principalDetails.getPassword());
    }
}

