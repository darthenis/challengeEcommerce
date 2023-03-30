package com.easybuy.easybuy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailHandler {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMailAttachment(String from, String to, String subject, FileSystemResource file) throws MessagingException {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText("body", buildEmailTicket());
            mimeMessageHelper.setSubject(subject);

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            javaMailSender.send(mimeMessage);

    }

    public void sendEmailToken(String from, String to, String subject, String token) throws MessagingException {

        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText("body", buildEmailToken(token));
        mimeMessageHelper.setSubject(subject);

        javaMailSender.send(mimeMessage);

    }

    public String buildEmailToken(String token){

        return
                "<div style='background-color: #2C3E50; color: white;'>"+
                        "<h1 style=\"padding: 10px; margin: 0; text-align: center\">Welcome to Easy Buy</h1>" +
                        "<div style='font-size: 20px'>"+
                        "<div style=\"margin: 0; text-align: center\">"+
                        "<p>We are happy to receive you</p>" +
                        "<p>Please confirm your email to continue registration. Click this <a style='color: white;' href='http://localhost:8080/web/login.html?token="+token+"'>LINK</a></p>"+
                        "</div>"+
                        "</div>"+
                        "<img   style='width: 100%; margin: 0;'" +
                        "src='https://www.travelandleisure.com/thmb/LmeI6B9xXEr3XWCB4MHtYcF1-8I=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/woman-walking-looking-phone-STAYTOOL0522-705ddb14ac4047c0a7039df98319977c.jpg'></img>"+
                        "</div>";
    }

    public String buildEmailTicket(){

        return
                "<div style='background-color: #2C3E50; color: white;'>"+
                        "<h1 style=\"padding: 10px; margin: 0; text-align: center\">Thank you for buy in Easy Buy</h1>" +
                        "<div style='font-size: 20px'>"+
                        "<div style=\"margin: 0; text-align: center\">"+
                        "<p>Thank you for shopping, we have attached the purchase receipt for you.</p>"+
                        "</div>"+
                        "</div>"+
                        "<img   style='width: 100%; margin: 0;'" +
                        "src='https://searchengineland.com/wp-content/seloads/2015/05/ecommerce-shopping-retail-ss-1920.jpg'></img>"+
                        "</div>";
    }


}
