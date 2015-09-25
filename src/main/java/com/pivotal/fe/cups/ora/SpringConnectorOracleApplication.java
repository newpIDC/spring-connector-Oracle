package com.pivotal.fe.cups.ora;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringConnectorOracleApplication {
	
	@Autowired JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/")
	public Map<String,String> home()
	{
		HashMap<String,String> retVal = new HashMap<String,String>();
		retVal.put("response", "hello, world");
		
		if(jdbcTemplate == null) { retVal.put("jdbcTemplate", "is null"); }
		else
		{
			retVal.put("jdbcTemplate", "Exists!");
			SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from wells_test");
			int c = 0;
			while(rowSet.next())
			{
				retVal.put("row"+(c++), rowSet.getString(1));
			}
		}

		return retVal;
	}

    public static void main(String[] args) {
        SpringApplication.run(SpringConnectorOracleApplication.class, args);
    }
}
