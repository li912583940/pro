package myEureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		//SpringApplication.run(MyApplication.class, args);
		new SpringApplicationBuilder(EurekaApplication.class).web(true).run(args);
	}

}
