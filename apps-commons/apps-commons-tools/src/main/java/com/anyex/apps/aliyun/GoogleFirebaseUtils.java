package com.anyex.apps.aliyun;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

public class GoogleFirebaseUtils {

    private static FirebaseApp firebaseApp = null;
    private String path;

    public GoogleFirebaseUtils(){

    }

    public GoogleFirebaseUtils(String path){
        this.path = path;
        if(firebaseApp == null) {
            try {
                FileInputStream serviceAccount = new FileInputStream(this.path);

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                firebaseApp = FirebaseApp.initializeApp(options);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void init(){
        if(firebaseApp == null) {
            File f = null;
            try {
                //FileInputStream a = this.getClass().getResourceAsStream("");
                InputStream is = GoogleFirebaseUtils.class.getResourceAsStream("/aloan-1c235-firebase-adminsdk.json");
                //System.out.println(is);
                f = new File(UUID.randomUUID() + ".json");
                FileUtils.copyInputStreamToFile(is, f);
                FileInputStream serviceAccount = new FileInputStream(f);
                //FileInputStream serviceAccount = new FileInputStream(new File(".").getCanonicalPath() + "\\loan-main\\src\\main\\resources\\skyloan-firebase-adminsdk.json");

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                firebaseApp = FirebaseApp.initializeApp(options);
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if(f != null){
                    f.delete();
                }
            }
        }
    }

    public static void send(String deviceToken, String title, String body){
        if(deviceToken == null || deviceToken.equals("") || title == null || body == null){
            return;
        }

        // Send a message to the device corresponding to the provided
        // registration token.
        try {
            if(firebaseApp == null){
                init();
            }
            Notification notification = Notification.builder().setTitle(title).setBody(body).build();

            Message message = Message.builder()
                    .setNotification(notification)
                    .setToken(deviceToken)
                    .build();
            String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
            System.out.println(response);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        GoogleFirebaseUtils utils = new GoogleFirebaseUtils();
        utils.send("iofjy9t5t5BhdePcCQ9oL3CXG78BQQHSKOxI0KzHT0OmbI08sRhwobMoy5aX_Ul8_5oIyTWGcqfR1l86N", "235", "35");
    }
}
