package com.pbl5.utils;//package com.pbl5.controller;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import java.util.Date;
//import java.util.Properties;
//import java.util.Random;
//
//public class SendEmail {
//    public String getRandom() {
//        Random random = new Random();
//        int number = random.nextInt(999999);
//        return String.format("%06d", number);
//    }
//
//    public void sendEmail() {
//
//        final String from = "minhduy.pbl5.test12345@gmail.com";
//        final String password = "cybbmrmdpbnyyzla";
//
//// Properties : khai báo các thuộc tính
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
//        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        // create Authenticator
//        Authenticator auth = new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                // TODO Auto-generated method stub
//                return new PasswordAuthentication(from, password);
//            }
//        };
//
//        // Phiên làm việc
//        Session session = Session.getInstance(props, auth);
//
//        // Tạo một tin nhắn
//        MimeMessage msg = new MimeMessage(session);
//            try {
//                // connect
//                // type of content
////                // Kiểu nội dung
////                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//                // Kiểu nội dung
//                msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//
//                // Người gửi
//                msg.setFrom(new InternetAddress(from));
//
//                // Người nhận
//                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("minhduyle081003@gmail.com", false));
////                msg.setSentDate(new Date());
////
//                msg.setText("Mã xác minh ","UTF-8");
////
////                msg.setSubject("Xác minh đăng kí pbl5 " );
////
//                Transport.send(msg);
//            } catch (Exception e) {
//                System.out.println("Create Message Error");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    ;
//}

import com.pbl5.models.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class SendEmail {
    //generate vrification code
    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        return String.format("%04d", number);
    }

    //send email to the user email
    public boolean sendEmail(String toEmailUser,String subject,String content) {
        boolean test = false;

        String toEmail = toEmailUser;
        String fromEmail = "minhduyle081003@gmail.com";
        String password = "fibebpgltnsesbda";

        try {

            // your host email smtp server details
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
            props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");


            //get session to authenticate the host email address and password
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

            //set email message details
            Message mess = new MimeMessage(session);

            //set from email address
            mess.setFrom(new InternetAddress(fromEmail));
            //set to email address or destination email address
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

            //set email subject
//            mess.setSubject("PBL5 User Email Verification");
            mess.setSubject(subject);

            //set message text
//            mess.setText("Registered successfully.Please verify your account using this code: " + user.getVertificationCode());
mess.setText(content);
            //send the message
            Transport.send(mess);

            test = true;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }

        return test;
    }
}
