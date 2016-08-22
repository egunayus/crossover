package com.crossover.techtrial.domain.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailClient {

	@Autowired
	private JavaMailSender mailSender;
	
	public void prepareAndSend(String recipient, String message) {
	    MimeMessagePreparator messagePreparator = mimeMessage -> {
	        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	        messageHelper.setFrom("e.gunay@gmail.com");
	        messageHelper.setTo(recipient);
	        messageHelper.setSubject("Sample mail subject");
	        messageHelper.setText(message, true);
	    };
	    try {
	        mailSender.send(messagePreparator);
	    } catch (MailException e) {
	        e.printStackTrace();
	    }
	}	
}
