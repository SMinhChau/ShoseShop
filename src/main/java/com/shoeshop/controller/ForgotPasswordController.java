package com.shoeshop.controller;

import com.shoeshop.entity.Customer;
import com.shoeshop.service.CustomerService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("")
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/user/forgot-password")
    public String showForgotPassword() {
        return "users/forgot_password";
    }

    @PostMapping("/user/forgot-password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            customerService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/user/reset-password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }

        return "users/forgot_password";
    }

    public class Utility {
        public static String getSiteURL(HttpServletRequest request) {
            String siteURL = request.getRequestURL().toString();
            return siteURL.replace(request.getServletPath(), "");
        }
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@tulshop.com", "TulShop Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    @GetMapping("/user/reset-password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        Customer customer = customerService.getByResetPasswordToken(token);
        model.addAttribute("token", token);
        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "users/forgot_password";
        }

        return "users/reset_password";
    }

    @PostMapping("/user/reset-password")
    public String processResetPassword(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        Customer customer = customerService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");
        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "users/forgot_password";
        } else {
            if (password.length()<6){
                model.addAttribute("message", "Password must have at least 8 characters");
                return "users/reset_password";
            }
            customerService.updatePassword(customer, password);
            redirectAttributes .addFlashAttribute("message_forget_password", "<script> alert(\"?????i m???t kh???u th??nh c??ng\");</script>");
        }

        return "redirect:/";
    }
}
