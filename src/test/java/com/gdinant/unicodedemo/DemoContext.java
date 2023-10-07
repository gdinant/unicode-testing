package com.gdinant.unicodedemo;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;

@TestConfiguration(proxyBeanMethods = false)
public class DemoContext {

	@Bean
	@ServiceConnection
	public AzureContainer<?> mssqlContainer() {

		var mssql = new AzureContainer<>();

		mssql.acceptLicense()
			.withExposedPorts(1433)
			.withReuse(true);

		return mssql;
	}

}
