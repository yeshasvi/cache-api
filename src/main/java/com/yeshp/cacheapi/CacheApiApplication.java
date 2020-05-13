package com.yeshp.cacheapi;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication
public class CacheApiApplication {

	public static void main(String[] args) throws SQLException {
		H2ServerConfiguration.server();
		SpringApplication.run(CacheApiApplication.class, args);
	}
}
