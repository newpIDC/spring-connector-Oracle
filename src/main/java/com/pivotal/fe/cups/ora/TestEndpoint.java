package com.pivotal.fe.cups.ora;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndpoint {
	@Autowired
	@Qualifier("mysqlDS2")
	DataSource mysqlDS2UPS = null;

	@RequestMapping("/datasource")
	public Map<String, String> datasource() {
		BeanWrapper wrapper = new BeanWrapperImpl(mysqlDS2UPS);
		PropertyDescriptor[] properties = wrapper.getPropertyDescriptors();

		Map<String, String> propertiesMap = new HashMap<>(properties.length);

		for (PropertyDescriptor property : properties) {
			try {
				if (wrapper.isReadableProperty(property.getName())) {
					propertiesMap.put(property.getDisplayName(),
							wrapper.getPropertyValue(property.getName()).toString());
				}
			} catch (Exception e) {
				propertiesMap.put(property.getDisplayName(), e.getMessage());
			}
		}

		return propertiesMap;
	}
}
