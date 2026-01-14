package tn.itbs.maintenance.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI maintenanceOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8070");
        server.setDescription("Serveur de développement");

        Contact contact = new Contact();
        contact.setName("Équipe Maintenance");
        contact.setEmail("maintenance@example.com");

        Info info = new Info()
                .title("API Microservice Maintenance")
                .version("1.0.0")
                .description("API REST pour la gestion des interventions et des techniciens dans le système de maintenance prédictive")
                .contact(contact);

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}