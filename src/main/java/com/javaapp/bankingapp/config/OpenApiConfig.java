package com.javaapp.bankingapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				title = "Bank API",
				description = "Bank Api curd operations and security implimentations",
				summary = "this is demo project for learning purpose.",
				termsOfService = "TNC",
				contact = @Contact(
						name = "Ashish",
						email = "rathora34@gmail.com"
				),
				license = @License(
						name = "License no."
				),
				version = "v1"
		),
//		servers = {
//				@Server(
//						description = "Dev",
//						url = "http://localhost:8080"
//				),
//				@Server(
//						description = "Test",
//						url = "http://localhost:8080"
//				)
		security = @SecurityRequirement(
				name = "Bearer Authentication")  //enable all controllers authorization
)		
@SecurityScheme(
  name = "Bearer Authentication",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "bearer"
)
public class OpenApiConfig {

}
