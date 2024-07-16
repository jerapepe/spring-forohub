package com.jera.forohub.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.jera.forohub.model.Curso;

public record DataRegTopic(@NotBlank
                           String titulo,
                           @NotBlank
                           String mensaje,
                           @NotNull @Valid
                           Curso curso) {
}
