package com.animaisparaadocao.animaisparaadocao.configuracoes;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Animais para Adoção API")
                        .version("v1")
                        .description(
                                "A API foi desenvolvida para gerenciar o cadastro de animais para adoção. Seu contexto de" +
                                        " desenvolvimento é o exercício\n de boas práticas de programação orientada a objeto, bem como a " +
                                        "aplicação das arquiteturas de mercado. \n Boas práticas utilizando padrões de projeto, " +
                                        "princípios SOLID e conceitos de Clean Code, além do desenvolvimento de testes unitários e " +
                                        "automatizados utilizando JUnit e Mockito."
                        )
                        .contact(new Contact()
                                .name("Saulo Henrique Rodrigues")
                                .email("saulo.rodrigues@db.tec.br"))
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local")
                ));
    }
}
