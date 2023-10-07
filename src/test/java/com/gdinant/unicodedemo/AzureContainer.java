package com.gdinant.unicodedemo;

import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.LicenseAcceptance;

public class AzureContainer<T extends AzureContainer<T>> extends JdbcDatabaseContainer<T> {

	private static final String PASSWORD = "A_Str0ng_Required_Password";

	public AzureContainer() {
		super(DockerImageName.parse("mcr.microsoft.com/azure-sql-edge:latest"));
	}

	@Override
	public String getDriverClassName() {
		return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	}

	@Override
	public String getJdbcUrl() {
		var additionalUrlParams = constructUrlParameters(";", ";");
		return "jdbc:sqlserver://" + getHost() + ":" + getMappedPort(1433) + additionalUrlParams;
	}

	@Override
	public String getUsername() {
		return "SA";
	}

	@Override
	public String getPassword() {
		return PASSWORD;
	}

	@Override
	protected String getTestQueryString() {
		return "SELECT 1";
	}

	@Override
	protected void configure() {
		// If license was not accepted programatically, check if it was accepted via resource file
		if (!getEnvMap().containsKey("ACCEPT_EULA")) {
			LicenseAcceptance.assertLicenseAccepted(this.getDockerImageName());
			acceptLicense();
		}

		addEnv("SA_PASSWORD", PASSWORD);
	}

	@Override
	protected String constructUrlForConnection(String queryString) {
		// The JDBC driver of MS SQL Server enables encryption by default for versions > 10.1.0.
		// We need to disable it by default to be able to use the container without having to pass extra params.
		// See https://github.com/microsoft/mssql-jdbc/releases/tag/v10.1.0
		if (urlParameters.keySet().stream().map(String::toLowerCase).noneMatch("encrypt"::equals)) {
			urlParameters.put("encrypt", "false");
		}
		return super.constructUrlForConnection(queryString);
	}

	public T acceptLicense() {
		addEnv("ACCEPT_EULA", "Y");
		return self();
	}
}
