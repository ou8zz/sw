/**
 * SQE SERVICE INC. All right reserved.
 */
package com.sw;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Properties;

/**
 * @description This class implements MailService.
 * @author OLE
 * @date 2017-03-01
 */
public class MailServiceImpl extends JFrame {

    private JLabel jLabel;          // 说明
    private JTextField jhost;       // host
    private JTextField jport;       // port
    private JTextField jauth;       // mail.smtp.auth
    private JTextField juser;       // Authenticator user
    private JTextField jpwd;        // Authenticator password
    private JTextField jsend;       // 发送邮件
    private JButton jsubmit;        // submit

    MailServiceImpl() {
        super();
        init();
    }

    public void init() {
        this.setBounds(100, 100, 300, 400);
        this.setLayout(null);
        this.setTitle("Send Mail Test");

        if (jLabel == null) {
            jLabel = new JLabel();
            jLabel.setBounds(50, 40, 200, 20);
            jLabel.setText("测试邮件发送配置");
        }
        if(jhost == null) {
            jhost = new JTextField();
            jhost.setBounds(50, 70, 200, 20);
            jhost.setText("smtp.126.com");
        }
        if(jport == null) {
            jport = new JTextField();
            jport.setBounds(50, 100, 200, 20);
            jport.setText("25");
        }
        if(jauth == null) {
            jauth = new JTextField();
            jauth.setBounds(50, 130, 200, 20);
            jauth.setText("true");
        }
        if(juser == null) {
            juser = new JTextField();
            juser.setBounds(50, 160, 200, 20);
            juser.setText("ou88zz@126.com");
        }
        if(jpwd == null) {
            jpwd = new JTextField();
            jpwd.setBounds(50, 190, 200, 20);
            jpwd.setText("378925498");
        }
        if(jsend == null) {
            jsend = new JTextField();
            jsend.setBounds(50, 220, 200, 20);
            jsend.setText("ole.ouyang@analyticservice.net");
        }
        if(jsubmit == null) {
            jsubmit = new JButton();
            jsubmit.setBounds(50, 250, 200, 20);
            jsubmit.setText("submit");
            jsubmit.addMouseListener(new MouseListener() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        init();
                        String res = sendMail("Test邮件主题", "这是一个测试邮件！收到邮件说明邮箱配置OK！");
                        JOptionPane.showMessageDialog(null, "发送成功！" + res, "提示", JOptionPane.WARNING_MESSAGE);
                    } catch (MessagingException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "发送失败：" + "\n" +e1.toString(), "提示", JOptionPane.WARNING_MESSAGE);
                    }
                }
                public void mousePressed(MouseEvent e) {}
                public void mouseReleased(MouseEvent e) {}
                public void mouseEntered(MouseEvent e) {}
                public void mouseExited(MouseEvent e) {}
            });
        }

        this.add(jLabel, null);
        this.add(jhost, null);
        this.add(jport, null);
        this.add(jauth, null);
        this.add(juser, null);
        this.add(jpwd, null);
        this.add(jsend, null);
        this.add(jsubmit, 6);
    }

    public static void main(String [] arge) {
        MailServiceImpl mailService = new MailServiceImpl();
        mailService.setEnabled(true);
        mailService.setVisible(true);
        mailService.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String sendMail(String subject, String mailText) throws MessagingException {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", jhost.getText());
        props.put("mail.smtp.auth", jauth.getText());
        props.put("mail.smtp.port", jport.getText());

        Session session = Session.getInstance(props, new MyPasswordAuthenticator(juser.getText(), jpwd.getText()));
        String email = jsend.getText();
        InternetAddress email_addresses[] = InternetAddress.parse(email, false);

        // Create and Initialize message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(juser.getText()));
        message.setRecipients(Message.RecipientType.TO, email_addresses);
        message.setSubject(subject, "UTF-8");
        System.out.println("Sending email to .. " + email);
        // message.setContent(strMessageText, "text/plain");
        String tt = "<br/> host:"+jhost.getText() + ", <br/> auth:"+jauth.getText() + ", <br/> port:"+jport.getText()+ ", <br/> user:"+juser.getText()+", <br/> pwd:"+jpwd.getText()+ ", <br/> email:"+email + "";
        message.setContent(mailText+tt, "text/html;charset=UTF-8");

        // send message
        Transport.send(message);
        String res = "\n host:"+jhost.getText() + ", \n auth:"+jauth.getText() + ", \n port:"+jport.getText()+ ", \n user:"+juser.getText()+", \npwd:"+jpwd.getText()+ ", \n email:"+email + "";
        return res;
    }

}


class MyPasswordAuthenticator extends Authenticator {
    private String user;
    private String pw;

    MyPasswordAuthenticator(String username, String password) {
        super();
        this.user = username;
        this.pw = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, pw);
    }
}