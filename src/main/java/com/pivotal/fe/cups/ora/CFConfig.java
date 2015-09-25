package com.pivotal.fe.cups.ora;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

//If cloud, load the xml bean definition ... this doesn't seem to make
//any difference.
@Configuration
@Profile("cloud-ignore")
@ImportResource({"classpath*:application-context.xml"})
public class CFConfig {

}
