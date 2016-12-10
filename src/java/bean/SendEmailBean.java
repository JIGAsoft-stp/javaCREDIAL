/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import mensagem.Mensagem;
import modelo.Email;
import org.primefaces.context.RequestContext;
import validacao.Validation;

/**
 *
 * @author AhmedJorge
 */
@ManagedBean
@ViewScoped
public class SendEmailBean implements Serializable
{
    private static final long SERIAL_VERSION = 1L;
    private Email email = new Email();
    
    public SendEmailBean()
    {    
    }

    private boolean validarEmail()
    {
        boolean valido = true;
         Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"); 
         Matcher m = p.matcher(email.getEmailReenvio()); 
         if (!m.find())
        {
            valido = false;
           RequestContext.getCurrentInstance().execute("emailInvalido()");
        }   
        return valido;
    }

    public Email getEmail() {
        return (email == null) ? email = new Email() : email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
    
    public void setMessage()
    {
        if(validarEmail() == false)
        {
            Mensagem.msgError("Email inválido");
                 RequestContext.getCurrentInstance().execute("emailInvalido()");
            Validation.AtualizarCompoente("formEmail", "gEmail");
        }
        else sendMessage();
    }
    private void sendMessage()
    {
        try {
            String smtpHost="smtp.gmail.com";
            String smtpUsername ="jigasoft.stp2015@gmail.com";
            String smtpPassword ="OurAccountEnterprise2015";
            String from ="jigasoft.stp2015@gmail.com";
            String to="jigasoft_stp@hotmail.com";
            String info = "Mensagem enviada de CREDIAL por "+email.getNome()+"\n\n"+email.getMesagem()+"\n\n"+email.getEmailReenvio();
      
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
                props.put("mail.debug", "true");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(smtpUsername, smtpPassword);
			}
		  });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(email.getAssunto());
            message.setText(info);
            Transport.send(message);
        
            Mensagem.msgInfo("Mensagem enviada com sucesso");
            Validation.AtualizarCompoente("formEmail", "gEmail");
            RequestContext.getCurrentInstance().execute("enviarMensagem()");
        } 
        catch (MessagingException ex)
        {  
            Mensagem.msgError("Mensagem não enviada");
           Validation.AtualizarCompoente("formEmail", "gEmail");
            Logger.getLogger(SendEmailBean.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    
  
}
