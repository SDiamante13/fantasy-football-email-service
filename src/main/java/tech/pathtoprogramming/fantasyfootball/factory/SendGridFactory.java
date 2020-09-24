package tech.pathtoprogramming.fantasyfootball.factory;

import com.sendgrid.Client;
import com.sendgrid.SendGrid;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.HttpClients;

import static java.lang.Integer.parseInt;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SendGridFactory {

    public static SendGrid getSendGridInstance() {
        String hostname = System.getProperty("PROXY_HOST_NAME");
        String proxyPort = System.getProperty("PROXY_PORT");
        String apiKey = System.getProperty("SENDGRID_API_KEY");

        if (hostname != null && proxyPort != null) {
            return new SendGrid(apiKey,
                    new Client(HttpClients.custom()
                            .useSystemProperties()
                            .setProxy(new HttpHost(
                                    hostname,
                                    parseInt(proxyPort)
                            ))
                            .build()));
        } else {
            return new SendGrid(apiKey);
        }
    }
}
