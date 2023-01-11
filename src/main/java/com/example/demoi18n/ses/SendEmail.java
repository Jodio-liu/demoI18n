package com.example.demoi18n.ses;
// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[SendMessage.java demonstrates how to send an email message by using the SesV2Client.]
// snippet-keyword:[AWS SDK for Java v2]
// snippet-keyword:[Amazon Simple Email Service]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

// snippet-start:[ses.java2.sendmessage.sesv2.import]

import com.example.demoi18n.Enum.LoginFrom;
import com.example.demoi18n.firebase.MailUtil;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sesv2.SesV2Client;
import software.amazon.awssdk.services.sesv2.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
// snippet-end:[ses.java2.sendmessage.sesv2.import]

/**
 * Before running this AWS SDK for Java (v2) example, set up your development environment, including your credentials.
 * <p>
 * For more information, see the following documentation topic:
 * <p>
 * https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */

public class SendEmail {
    static String ACCESS_KEY = "AKIAWFAVXKTCE6OGJWFC";
    static String SECRET_KEY = "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";
    static String REGION_NAME = "us-west-2";

    public static void main(String[] args) throws IOException {
        // 亚马逊ses
        awsSES();

//        Integer t = null;
//        LoginFrom byValue = LoginFrom.findByValue(t);
//        String email = "liu@qq.com";
//        String encryptEmail =  email.replaceAll("(^\\w{4})[^@]*(@.*$)", "$1****$2");
//        System.out.println(encryptEmail);
        // firebase
//        firebase();

    }

    private static void firebase() throws IOException {
        initSDK();

        String email = "thenextone163@163.com";
        try {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseAppMap.get("appName"));
            UserRecord user = firebaseAuth.getUserByEmail(email);
//            user.
            String link = firebaseAuth.generateEmailVerificationLink(email);
            // Construct email verification template, embed the link and send
            // using custom SMTP server.
            new Thread(new MailUtil(email, link)).start();
        } catch (FirebaseAuthException e) {
            System.out.println("Error generating email link: " + e.getMessage());
        }
    }

    //存放多个实例的Map
    private static Map<String, FirebaseApp> firebaseAppMap = new ConcurrentHashMap<>();

    /**
     * 初始化SDK
     *
     * @throws IOException
     */
    public static void initSDK() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("/Users/jodio/IdeaProjects/demoI18n/src/main/resources/firebase/serviceAccountKey.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        //初始化firebaseApp
        FirebaseApp firebaseApp = FirebaseApp.initializeApp(options, "appName");
        //存放
        firebaseAppMap.put("appName", firebaseApp);
    }


    private static void awsSES() {
        String sender = "Planet J <no_reply@mail.ltjmeta.com>";
        String recipient = "thenextone163@163.com";
        String subject = "Verify Email Address for Planet J";

        AwsCredentialsProvider awsCredentialsProvider = () -> new AwsCredentials() {
            @Override
            public String accessKeyId() {
                return ACCESS_KEY;
            }

            @Override
            public String secretAccessKey() {
                return SECRET_KEY;
            }
        };

        Region region = Region.US_WEST_2;
        SesV2Client sesv2Client = SesV2Client.builder()
                .region(region)
                .credentialsProvider(awsCredentialsProvider)
                .build();

        // The HTML body of the email.
        String bodyHTML = "<html>" + "<head></head>" + "<body>" + "<h1>Hello!</h1>"
                + "<p> See the list of customers.</p>" + "</body>" + "</html>";

        send(sesv2Client, sender, recipient, subject, bodyHTML);
    }

    // snippet-start:[ses.java2.sendmessage.sesv2.main]
    public static void send(SesV2Client client, String sender, String recipient, String subject, String bodyHTML) {

        Destination destination = Destination.builder()
                .toAddresses(recipient)
                .build();

        Content content = Content.builder()
                .data(bodyHTML)
                .build();

        Content sub = Content.builder()
                .data(subject)
                .build();

        Body body = Body.builder()
                .html(content)
                .build();

        Message msg = Message.builder()
                .subject(sub)
                .body(body)
                .build();

        EmailContent emailContent = EmailContent.builder()
                .simple(msg)
                .build();

        SendEmailRequest emailRequest = SendEmailRequest.builder()
                .destination(destination)
                .content(emailContent)
                .fromEmailAddress(sender)
                .build();

        try {
            System.out.println("Attempting to send an email through Amazon SES " + "using the AWS SDK for Java...");
            SendEmailResponse sendEmailResponse = client.sendEmail(emailRequest);
            System.out.println("email was sent:" + sendEmailResponse);

        } catch (SesV2Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    // snippet-end:[ses.java2.sendmessage.sesv2.main]
}
