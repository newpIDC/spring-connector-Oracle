package com.pivotal.fe.cups.ora;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.PooledServiceConnectorConfig.PoolConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/** properties in .yml
pool:
  size:
    min: 5
    max: 10
  wait:
    time: 3000
  validation:
    testOnBorrow: false
    testOnCreate: false
    query: select 1
 */
@Configuration
@Profile("cloud")
public class MyCloudConfig extends AbstractCloudConfig {
	
	@Value("${pool.size.min:1}")
	private int poolSizeMin = 0;
	
	@Value("${pool.size.max:5}")
	private int poolSizeMax = 0;
	
	@Value("${pool.wait.time:1000}")
	private int poolWaitTime = 0;
	
	@Value("${pool.validation.query:default-will-fail}")
	private String poolValidationQuery;
	
	@Value("${pool.validation.testOnBorrow}")
	private boolean poolTestOnBorrow;
	
	@Value("${pool.validation.testOnCreate:true}")
	private boolean poolTestOnCreate;
	
	@Bean
	public DataSource mysqlDS()
	{
		PoolConfig poolConfig = new PoolConfig(poolSizeMin, poolSizeMax, poolWaitTime)
		{
			public String  getValidationQuery() { return poolValidationQuery; }
			public boolean getTestOnBorrow()    { return poolTestOnBorrow;    }
			public boolean getTestOnConnect()   { return poolTestOnCreate;    }
		};

		DataSourceConfig config = new DataSourceConfig(poolConfig, null);
		return connectionFactory().dataSource("mysqlDS", config);
	}
	
	@Bean
	public DataSource mysqlDS2()
	{
		PoolConfig poolConfig = new PoolConfig(poolSizeMin, poolSizeMax, poolWaitTime)
		{
			public String  getValidationQuery() { return poolValidationQuery; }
			public boolean getTestOnBorrow()    { return poolTestOnBorrow;    }
			public boolean getTestOnConnect()   { return poolTestOnCreate;    }
		};

		DataSourceConfig config = new DataSourceConfig(poolConfig, null);
		return connectionFactory().dataSource("mysqlDS2", config);
	}
}