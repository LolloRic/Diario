package it.corvallis.diario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//http://localhost:8080/RiccardoLollo/swagger-ui/index.html?url=/v3/api-docs#/
@SpringBootApplication
@ComponentScan("it.corvallis.diario")
public class DiarioPersonaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiarioPersonaleApplication.class, args);
	}

}
