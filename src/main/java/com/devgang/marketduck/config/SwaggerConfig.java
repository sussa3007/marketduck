package com.devgang.marketduck.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Server server = new Server();
        server.setDescription("dev");
        server.setUrl("https://marketduck.server-su.site");
        Server local = new Server();
        local.setDescription("local");
        local.setUrl("http://192.168.75.163:8987");

        Server product = new Server();
        product.setDescription("product");
        product.setUrl("https://api.marketduck.site");

        Info info = new Info()
                .version("v0.0.1")
                .title("Market Duck API 명세서")
                .description("Market Duck 백엔드 서버 API 명세서");

        // SecuritySecheme명
        String jwtSchemeName = "JWT Access Token";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("Bearer")
                        .bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

        OpenAPI result = new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
        result.setServers(List.of(server, product, local));

        return result;
    }

}
