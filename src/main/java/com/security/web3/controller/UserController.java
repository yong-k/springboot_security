package com.security.web3.controller;

import com.security.web3.auth.PrincipalDetails;
import com.security.web3.consts.ResultCode;
import com.security.web3.exception.DataNotFoundException;
import com.security.web3.service.UserService;
import com.security.web3.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "redirect:/loginform";
    }

    @GetMapping("/user/info")
    public String getUserInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        try {
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
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
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

    @GetMapping("/user/delete")
    public String deleteUser(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        try {
            userService.deleteUser(principalDetails.getId());
        } catch (DataNotFoundException e) {
            model.addAttribute("code", ResultCode.DATA_NOT_FOUND.value());
            return "error/error";
        } catch (Exception e) {
            model.addAttribute("code", ResultCode.UNKNOWN_ERROR.value());
            log.error("Error in UserController.deleteUser()", e);
            return "error/error";
        }
        return "redirect:/";
    }
}
