package it.corvallis.diario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {

    	OpenAPI openapi = new OpenAPI();

    	Info info = new Info();
    	info.title("Diario Personale");
		info.version("1.0.0");
		info.description("Set di API che consentono di aggiungere, modificare, eliminare e cercare frasi.");
    	
    	Server serversItem =  new Server();
    	serversItem.url("http://localhost:8080");
    	
    	openapi.addServersItem(serversItem);
    	openapi.info(info);
    	
    	return openapi;
    }
}