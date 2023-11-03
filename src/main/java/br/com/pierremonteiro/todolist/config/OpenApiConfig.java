package br.com.pierremonteiro.todolist.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Pierre Monteiro",email = "marcos.pierre@upe.br",url = "https://www.linkedin.com/in/pierremont/"),description = "ToDoList usando java + Spring para estudos",title = "Java ToDoList",version = "1.0"),servers = {@Server(description = "production enviroment",url = "https://todolist-nz2u.onrender.com"),@Server(description = "local enviroment",url = "http://localhost:8080")},security = {@SecurityRequirement(name = "basicAuth")})
@SecurityScheme(name = "basicAuth", description = "JWT auth description", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)

public class OpenApiConfig {
}
