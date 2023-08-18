package likelion.Spring_Like_Farmer.file;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
public class FireBaseInitializer {


    @PostConstruct
    public void initialize() {
/*
        String n = String.valueOf(50 * 1024 * 1024);
        Long maxFileSize = Long.parseLong(n);
        Long maxRequestSize = Long.parseLong(n);

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("", maxFileSize, maxRequestSize, 0);
        multipartConfigElement = MultipartConfigElement.class.cast(org.springframework.util.StringUtils
                .replace(multipartConfigElement.toString(), "[]", "[" + maxFileSize + "]"));

*/

        String credentialsJson = "{\"type\": \"service_account\",\"project_id\": \"applepie-f030c\",\"private_key_id\": \"353d7fec447a44fd7581ddde478edbcfe244e45a\",\"private_key\": \"-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC/oX5HzjEx5kSf\nMMgmjL7yEbskIR1tF2L8kbixIvrLiOqrQmbf3LcFSdTtEow5ILbkqIx6o0mBvbgT\n4YgbfMQGs88L6ngufatlywXGHXYX+x79psYKYVjUWgcEJ0D/0HWMTyPdFaQ+oXVV\nvt54c92hx5iDM0ZzI/TgUlEwt1o15RKN7p6elLhkjPdyOhhsPDpP+TKqSefpKhR2\nHNEoNKMFuwSk1ragTwI25NEpdQU5vm1Xfyc9XddH5cGQ8SiO9/W9oQzXI/CNBlHl\nukEjmJxiQbjuioIklhh9NOXMYK168F5l0F9Rr7o2TDGyUyxjemMSYIzWKQK2bREz\nLignzlWTAgMBAAECggEABPbh3TbAdYKtCNOFskhd8spDM90CJ/JXWr2xScpJmJfa\ncUda5oL14HAyN1YOReiZp42dd6V4eqD3yz2oSs+a1tjPpoctFKwMX0QfYsUenkgG\nKU7c8IxNddFDP0Tl3S3nh0c0yArbr2riLeKwKfF7SDA+79JjPKMtoKYUey000nj3\nB+sfpPdnhXCtQQX75w2RmfXCFb0g73/35bBv8LkZObmZVt+C3g8LCTfrEdaj13ta\nSIgsoQizWRmmh71HAd7uW74h0kDc6533seP83OpatNvMBzsAaTYH/PKcBNsG1W7Y\nA6u8eJMOmN2ocqM3kkM0CmQspdj5jVTCxiO2RJ0zYQKBgQDv2eaXz/X2iY4nk6aI\nlu14LQjkVxt3XDt0r1Z9suvrZrizhgOiJp6WqC0UB6Ll96p3eKqX205D9exOxWaF\ntfAPkb0upFCTtl1Aw41G8WHp/gz2qZAwO/RFua+s8uL90I2M8iJ95DkcD4Da+ffF\nGi/tQH2p2u5JLqxYg2wPAxkuwwKBgQDMiHY5hF33rauQvIYXzdkjnRROZpmqYv+p\nv+irhYhbCV4xzpHrnhoLcFtbCfsAoXbhK5ICnEYZ+Dp2LJWmvuypyWKAUrBDpUfa\n5y1BeJEK/WLqZ2HPuG92N1KIomNJH7bEaCq1M4NZInGD2auebkwDSR+dxLudSaSo\n7f0VYoxw8QKBgQCSd9lmObu1KvkhV/wpiylG6xF0B3GhaTRxpCVIK3wAS8Fc9+i+\nG6Fa1SZ6BdPHZX+253wdsfHQdJwy2j8qRhlsGI6Trgn6ujGmPATJHtyxPNADzTZd\nDi+0aJlMHyLY5oOaX78Ka9UoOptwnZddyQkdc/WJRUWgcR0UDGVvoW++awKBgQDF\nrS32MQRQmL00ScCfyQeP8ATsMSVbFAtcmGnbPFepBMjRAoDtZARwAcuJlX/OAD/R\nKDcZ5sep2ctG45hPbD93aXeV5lUNS/TCkKOgn1IveO3c9sYF6I9xhc5y1991kP0n\nnjWIo5zSzQNBF2NS3+7/TfEMnlNllQNbcYgk3vY1QQKBgF7lfNbjByoNKQ8+2beM\nMVqf+D2cqTTvxxYD9BXY77PGFhDLZrEToormKbcPmmnRbaRfz1kUpifYJiVdeg98\nhdcOxahLc+P+ml9hYVdQrSOAuXQ+SZjCmu07Jdy5hddEOa2c52DURGIRX5ClhcL1\n+eWFGQqbwohUgrNfCh0zZ/NA\n-----END PRIVATE KEY-----\n\",\"client_email\": \"firebase-adminsdk-kcj9j@applepie-f030c.iam.gserviceaccount.com\",\"client_id\": \"103613235015855580641\",\"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\"token_uri\": \"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-kcj9j%40applepie-f030c.iam.gserviceaccount.com\",\"universe_domain\": \"googleapis.com\"}";
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(
                    new ByteArrayInputStream(credentialsJson.getBytes(StandardCharsets.UTF_8)));

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}