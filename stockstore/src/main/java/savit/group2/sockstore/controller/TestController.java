package savit.group2.sockstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import savit.group2.sockstore.model.entity.Account;
import savit.group2.sockstore.model.request.UserSingupRequest;
import savit.group2.sockstore.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("user/account")
public class TestController {
  @Autowired
  AccountService accountService;

  @PostMapping(value = "singup")
  public ResponseEntity<Account> postMethodName(@RequestBody UserSingupRequest entity) {
    return ResponseEntity.ok().body(accountService.singup(entity));
  }
}
