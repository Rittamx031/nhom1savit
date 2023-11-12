package savit.group2.sockstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import savit.group2.sockstore.model.entity.VertifyEmail;
import savit.group2.sockstore.model.request.SimpleMail;

@Component
public class EmailService {
  @Autowired
  TemplateEngine templateEngine;
  @Autowired
  private JavaMailSender emailSender;

  public void sendSimpleMessage(
      SimpleMail simpleMail) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("thatdeptraivjpro@26kleft.com");

    message.setTo(simpleMail.getEmailto());
    message.setSubject(simpleMail.getSubject());
    message.setText(simpleMail.getText());
    this.emailSender.send(message);
  }

  public void activeEmailMessage(VertifyEmail request) {
    MimeMessage message = emailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
      helper.setFrom("thatdeptraivjpro@26kleft.com");
      helper.setTo(request.getEmail());
      helper.setSubject("Active Email");

      Context context = new Context();
      context.setVariable("code", request.getCode());
      String htmlCode = templateEngine.process("mail/activeCode", context);

      // Set the HTML content of the email
      helper.setText(htmlCode, true);

      emailSender.send(message);
    } catch (MessagingException e) {
      // Handle exception
    }
  }

  public void resetEmailMessage(VertifyEmail request) {
    MimeMessage message = emailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
      helper.setFrom("thatdeptraivjpro@26kleft.com");
      helper.setTo(request.getEmail());
      helper.setSubject("Active Email");

      Context context = new Context();
      context.setVariable("code", request.getCode());
      String htmlCode = templateEngine.process("mail/resetCode", context);

      // Set the HTML content of the email
      helper.setText(htmlCode, true);
      emailSender.send(message);
    } catch (MessagingException e) {
      // Handle exception
    }
  }
}
