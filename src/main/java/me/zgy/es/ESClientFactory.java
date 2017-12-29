package me.zgy.es;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import me.zgy.utils.ConfigUtils;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rayee on 2017/12/28.
 */
public class ESClientFactory {

    private static String hosts = ConfigUtils.getString("es.hosts");

    private static final JestClient client;

    static {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(Arrays.asList(hosts.split(",")))
                .defaultCredentials("elastic", "changeme")
                .multiThreaded(true)
                .discoveryEnabled(true)
                .discoveryFrequency(1, TimeUnit.SECONDS)
                .build());
        client = factory.getObject();
    }

    private ESClientFactory(){

    }

    public static JestClient getClient(){
        return client;
    }

}
