package com.bank.sistemabancario.domain.service.email;

import com.bank.sistemabancario.config.MailConfiguration;
// import com.spring.mail.sender.config.MailConfiguration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String toUser, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        MailConfiguration mailConfiguration = new MailConfiguration();

        mailMessage.setFrom(mailConfiguration.getEmail());

        mailMessage.setTo(toUser);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        mailSender.send(mailMessage);
    }

    @Override
    public void sendEmailWithFile(String[] toUser, String subject, String body, File file) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MailConfiguration mailConfiguration = new MailConfiguration();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(mailConfiguration.getEmail());

            mimeMessageHelper.setTo(toUser);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.addAttachment(file.getName(), file);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}