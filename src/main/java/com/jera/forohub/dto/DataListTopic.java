package com.jera.forohub.dto;

import com.jera.forohub.model.Curso;
import com.jera.forohub.model.Topico;

public record DataListTopic(Long id, String titulo, String mensaje, Curso curso) {
    public DataListTopic(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getCurso());
    }
}
