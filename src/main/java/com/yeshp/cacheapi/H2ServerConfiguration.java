package com.yeshp.cacheapi;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2ServerConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public static Server server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers").start();
    }
}
