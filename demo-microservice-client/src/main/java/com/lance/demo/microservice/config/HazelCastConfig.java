package com.lance.demo.microservice.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ListConfig;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by perdonare on 2016/10/25.
 */
@Configuration
public class HazelCastConfig {
    @Bean
    public Config hazelcastConfig() {
        return new Config().setProperty("hazelcast.jmx", "true")
                .addMapConfig(new MapConfig("spring-boot-admin-application-store").setBackupCount(1)
                        .setEvictionPolicy(EvictionPolicy.NONE))
                .addListConfig(new ListConfig("spring-boot-admin-event-store").setBackupCount(1)
                        .setMaxSize(1000));
    }
}
