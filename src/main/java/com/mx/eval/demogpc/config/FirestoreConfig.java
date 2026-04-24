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

        if (serviceAccount == null) {
            throw new IllegalStateException("No se encontró service-account.json en resources");
        }

        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

        FirestoreOptions options = FirestoreOptions.newBuilder()
                .setCredentials(credentials)
                .build();

        return options.getService();
    }
}
