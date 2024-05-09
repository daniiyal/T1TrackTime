package com.t1school.tracktime.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Track Time Aspect Api",
                description = "Track Time System", version = "1.0.0",
                contact = @Contact(
                        name = "Daniyal Magomedov"
                )
        )
)
public class OpenAPIConfig {
}
