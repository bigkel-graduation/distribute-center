package com.itchenyang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CenterGateWayApplication {
    private static final Logger LOG = LoggerFactory.getLogger(CenterGateWayApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CenterGateWayApplication.class, args);
        LOG.info("distribute center gateway application started!");
    }
}
