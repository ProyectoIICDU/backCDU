/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.proyecto2.crud_escenarios.bean;

import edu.proyecto2.crud_escenarios.data.Correo;
import java.util.List;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Tania
 */
@ManagedBean
@ViewScoped
public class CorreoBean {
    
    private final Properties props = new Properties();
    private Session session;
    private String remitente = "appReservasCDU@gmail.com";
    private String password = "P8mPaiflMp";

    private void init() {
        props.put("mail.smtp.host", "mail.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.fallback", "true");
        props.put("mail.smtp.port", "25");
        session = Session.getDefaultInstance(props);
    }
    
    /*
        * Método que recibe objeto de tipo Correo (destinatarios, asunto y cuerpo)
        * Esta función envía correos a los destinatarios especificados en la lista de destinatarios.
        * Retorna true o false de acuerdo a si el envío fue exitoso o no.
    */
    public boolean enviarCorreo(Correo correoObj){
        boolean emailEnviado = true;
        List<String> destinatarios = correoObj.getDestinatarios();
        this.init();
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", password);
        for (int i = 0; i < destinatarios.size(); i++) {
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(remitente));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatarios.get(i)));   
                message.setSubject(correoObj.getAsunto());
                message.setText(correoObj.getCuerpo());
                Transport t = session.getTransport("smtp");
                t.connect("smtp.gmail.com", remitente, password);
                t.sendMessage(message, message.getAllRecipients());
                t.close();
            } catch (MessagingException me) {
                System.out.println(me);
                emailEnviado = false;
            }
        }
        return emailEnviado;
    }
}
