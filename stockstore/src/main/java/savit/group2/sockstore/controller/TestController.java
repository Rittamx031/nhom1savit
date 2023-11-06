package savit.group2.sockstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import savit.group2.sockstore.model.entity.Account;
import savit.group2.sockstore.model.request.SimpleMail;
import savit.group2.sockstore.model.request.UserSingupRequest;
import savit.group2.sockstore.service.AccountService;
import savit.group2.sockstore.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("test/sendmail")
public class TestController {
  @Autowired
  AccountService accountService;
  @Autowired
  EmailService emailService;
  @Autowired
  public JavaMailSender emailSender;

  @PostMapping(value = "singup")
  public ResponseEntity<Account> postMethodName(@RequestBody UserSingupRequest entity) {
    return ResponseEntity.ok().body(accountService.singup(entity));
  }

  @PostMapping("sendingmailtest")
  public String sendingmail(@RequestBody SimpleMail simpleMail) {
    emailService.sendSimpleMessage(simpleMail);
    return "sendthanhcong";
  }

}
