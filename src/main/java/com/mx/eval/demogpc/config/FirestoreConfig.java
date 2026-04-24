package com.mx.eval.demogpc.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class FirestoreConfig {

    @Bean
    public Firestore firestore() throws Exception {

        InputStream serviceAccount = getClass()
                .getClassLoader()
                .getResourceAsStream("service-account.json");

        FirestoreOptions.Builder builder = FirestoreOptions.newBuilder();

        if (serviceAccount != null) {
            builder.setCredentials(GoogleCredentials.fromStream(serviceAccount));
        }

        return builder.build().getService();
    }
}
