package edu.poly.Service;

import javax.mail.MessagingException;

import edu.poly.model.MailInfo;

public interface MailerService {
    /**
     * Gửi email
     * @param mail thông tin email
     * @throws MessagingException lỗi gửi email
     */
    void send(MailInfo mail) throws MessagingException;
    /**
     * Gửi email đơn giản
     * @param to email người nhận
     * @param subject tiêu đề email
     * @param body nội dung email
     * @throws MessagingException lỗi gửi email
     */
    void send(String to, String subject, String body) throws MessagingException;

    void queue(MailInfo mail);
    /**
     * Tạo MailInfo và xếp vào hàng đợi
     * @param to email người nhận
     * @param subject tiêu đề email
     * @param body nội dung email
     */
    void queue(String to, String subject, String body);
}
