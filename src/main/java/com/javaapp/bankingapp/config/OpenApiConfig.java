package com.javaapp.bankingapp.config;

import java.lang.annotation.Documented;

import org.aspectj.lang.annotation.control.CodeGenerationHint;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
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
				title = "Banking-App APIs",
				description = "## Banking-App Apis curd operations and security implimentation \n"
						+ "### Login credentials - \n"
						+ "### username - ashish@gmail.com \n"
						+ "### password - 1234 \n"
						+ "#### Go to user-controller and authenticate user \n",
				summary = "This is Test project for learning purpose.",
				termsOfService = "TNC",
				contact = @Contact(
						name = "Ashish Rathor",
						email = "rathora34@gmail.com"
				),
				license = @License(
						name = "License no."
				),
				version = "v1"
		),
		externalDocs = @ExternalDocumentation(url = "https://github.com/ASHISH3214/Banking-App-api"),
//		servers = {
//				@Server(
//						description = "Dev",
//						url = "https://banking-app-api-production.up.railway.app/"
//				)
//		},
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
