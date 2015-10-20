package com.pivotal.fe.cups.ora;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringConnectorOracleApplication {
	
//	@Autowired
	JdbcTemplate jdbcTemplate = null;

	@Autowired
	@Qualifier("mysqlDS")
	DataSource mysqlDSUPS = null;
	
	@RequestMapping("/")
	public Map<String,String> home()
	{
		HashMap<String,String> retVal = new HashMap<String,String>();
		retVal.put("response", "hello, world");
		
		if(jdbcTemplate == null) { retVal.put("jdbcTemplate", "is null"); }
		else
		{
			retVal.put("jdbcTemplate", "Exists!");
			SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select count(*) from xf_thread");
			int c = 0;
			while(rowSet.next())
			{
				retVal.put("row"+(c++), rowSet.getString(1));
			}
		}

		if(mysqlDSUPS == null) { retVal.put("mysqlDS", "is null :-("); }
		else
		{
			retVal.put("mysqlDS", "exists :-)");
			try {
				Connection conn = mysqlDSUPS.getConnection();
				Statement s = conn.createStatement();
				ResultSet rowSet = s.executeQuery("select count(*) from xf_thread");
				int c = 0;
				while(rowSet.next())
				{
					retVal.put("row"+(c++), rowSet.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return retVal;
	}

    public static void main(String[] args) {
        SpringApplication.run(SpringConnectorOracleApplication.class, args);
    }
}
