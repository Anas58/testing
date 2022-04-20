package utilities;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Email {
    /**
     * This method allows you to send emails
     * Usage: adding emails to receive to list, provide String subject for email,
     * provide path for attachment file if needed, else set to null
     * provide name for file, else set to null
     * trigger boolean flag if email has html content, provide path for html file
     * provide String Text to be main email body
     *
     * @param Recipients     ArrayList of Strings containing emails of recipients
     * @param EmailSubject   Email Subject Text
     * @param AttachmentPath Absolute path to the attached file
     * @param FileName       file name with extension ex: 1.pdf
     * @param HtmlBody       flag weather user desire html email or not
     * @param EmailBodyHtml  Absolute path of the HTML file .html
     * @param EmailText      String text to be inside Email body.
     * @return boolean
     */

    public static boolean SendEmail(List<String> Recipients, String EmailSubject,
                                    String AttachmentPath, String FileName, boolean HtmlBody, String EmailBodyHtml, String EmailText) {

//        final String senderEmail = "AutomationReport2@gmail.com"; // sender email
//        final String senderEmailPassword = "Auto@123"; // sender email password

        final String senderEmail = "abdulkarim-c@zatca.gov.sa";
        final String senderEmailPassword = "AAA16797020800*";

        try {
            Properties props = new Properties();
//            props.setProperty("mail.smtp.proxy.host", "fp.myzatca.gov.sa");
//            props.setProperty("mail.smtp.proxy.port", "25");

            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.starttls.enable", true);
            //props.put("mail.smtp.EnableSSL.enable", true);
            props.put("mail.smtp.host", "smtp.office365.com"); // smtp.gmail.com, smtp-mail.outlook.com
//            props.put("mail.smtp.socketFactory.port", "25");
//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.port", "465");


            Session session = Session.getInstance(props,
                    new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(senderEmail, senderEmailPassword);
                        }
                    });

            String RecipientsString = String.join(",", Recipients);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(RecipientsString));
            message.setSubject(EmailSubject);

            BodyPart attachmentBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            BodyPart htmlBodyPart = new MimeBodyPart();
            BodyPart textBodyPart = new MimeBodyPart();

            if (HtmlBody) // if the user desires to send email as HTML body
            {
                StringBuilder contentBuilder = new StringBuilder();

                BufferedReader in = new BufferedReader(new FileReader(EmailBodyHtml));
                String str;
                while ((str = in.readLine()) != null) {
                    contentBuilder.append(str);
                }
                in.close();

                String htmlBody = contentBuilder.toString();
                htmlBodyPart.setContent(htmlBody, "text/html; charset=utf-8");
                multipart.addBodyPart(htmlBodyPart);

            } else if (EmailText != null) { // fill the email body with normal text
                textBodyPart.setContent(EmailText, "text/plain; charset=utf-8");
                multipart.addBodyPart(textBodyPart);
            } else {
                textBodyPart.setContent("Default", "text/plain; charset=utf-8");
                multipart.addBodyPart(textBodyPart);
            }

            if (AttachmentPath != null & FileName != null) {
                FileDataSource fileDataSource = new FileDataSource(AttachmentPath);
                attachmentBodyPart.setDataHandler(new DataHandler(fileDataSource));
                attachmentBodyPart.setFileName(FileName);
                multipart.addBodyPart(attachmentBodyPart);
            }

            message.setContent(multipart);
            System.out.println("Sending Email..");
            Transport.send(message);


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Email was sent successfully");
        return true;
    }

    public static void main(String[] arguments){
        List<String> emails = new ArrayList<>();
        //emails.add(String.valueOf(data.get("email")));
        emails.add("mjabr-c@zatca.gov.sa");
        //emails.add("abdulkarim-c@zatca.gov.sa");
        Email.SendEmail(emails,
                //emailSubject,
                "subject",
                "C:\\Users\\abdulkarim-c\\IdeaProjects\\tests\\resources\\Index.html",
                //emailSubject + ".html",
                "file.html",
                false,
                null,
                "The report is attached");
    }
}



/*
Main Usage

        List<String> rec = new ArrayList<String>();
        rec.add("email@email.com");
        rec.add("email@email.com");
        String subject = "Test";
        String filepath = "C:\\Users\\Anas\\Downloads\\Documents\\1.py";
        String filename = "1.py";
        String html = "C:\\Users\\Anas\\Downloads\\Documents\\test.html";
        String text = "Testing text";

        Email.SendEmail(rec, subject, filepath, filename,true, html, null);

 */