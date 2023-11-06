package savit.group2.sockstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import savit.group2.sockstore.model.entity.VertifyEmail;
import savit.group2.sockstore.repository.VertifyEmailRepository;
import savit.group2.sockstore.util.CheckEmailHelper;

@Service
public class VertifyEmailService {
  @Autowired
  CheckEmailHelper checkEmailHelper;
  @Autowired
  VertifyEmailRepository repository;
  @Autowired
  EmailService emailService;

  public VertifyEmail getVertifyEmail(String email) {
    if (checkEmailHelper.isEmailNotExsits(email)) {
      // throw exeption
    } else {
      Optional<VertifyEmail> vertifyEmailOpl = repository.findById(email);
      if (vertifyEmailOpl.isPresent()) {
        return vertifyEmailOpl.get();
      }
    }
    // throw exeption
    return null;
  }

  public VertifyEmail createVertifyEmail(String email) {
    if (checkEmailHelper.isEmailNotExsits(email)) {
      // throw exeption
      return null;
    } else {
      VertifyEmail newVertifyEmail = repository.save(new VertifyEmail(email));
      emailService.activeEmailMessage(newVertifyEmail);
      return newVertifyEmail;
    }
  }

  public boolean vertifyEmail(VertifyEmail vertifyEmail) {
    if (checkEmailHelper.isEmailNotExsits(vertifyEmail.getEmail())) { // throw exeption
      return false;
    }
    VertifyEmail vertifyEmail2 = this.getVertifyEmail(vertifyEmail.getEmail());
    if (vertifyEmail2.checkCode(vertifyEmail.getCode())) {
      return true;
    }
    return false;
  }
}
