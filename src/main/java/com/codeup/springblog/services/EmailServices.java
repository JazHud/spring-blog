package com.codeup.springblog.services;

import com.codeup.springblog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class EmailServices {

    @Autowired //same as dependency injection
    public JavaMailSender emailSender; //library capable of sending emails

    @Value("${spring.mail.from}")
    private String from;

    public void prepareAndSend(User user, String subject, String body){
        SimpleMailMessage msg = new SimpleMailMessage(); //creating empty message
        msg.setTo(user.getEmail()); //who we are sending the email too
        msg.setFrom(from); //giving our empty message from info
        msg.setSubject(subject);  //giving our empty message subject info
        msg.setText(body);  //giving our empty message body info

        try {
            this.emailSender.send(msg);
        } catch (MailException mex) {
            System.err.println(mex.getMessage());

        }
    }
}
