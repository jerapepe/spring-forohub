package com.jera.forohub.dto;

import java.time.LocalDateTime;

import com.jera.forohub.model.Curso;
import com.jera.forohub.model.Topico;
public record DataDetailsTopic(String titulo, String mensaje, LocalDateTime fechaCreacion, Curso curso) {
    public DataDetailsTopic(Topico topic) {
        this(topic.getTitulo(), topic.getMensaje(), topic.getFechaCreacion(), topic.getCurso());
    }
}
