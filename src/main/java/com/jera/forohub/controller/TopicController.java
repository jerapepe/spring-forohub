package com.jera.forohub.controller;

import com.jera.forohub.dto.DataUpdateTopic;
import com.jera.forohub.dto.DataDetailsTopic;
import com.jera.forohub.dto.DataListTopic;
import com.jera.forohub.dto.DataRegTopic;
import com.jera.forohub.model.Topico;
import com.jera.forohub.repository.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DataRegTopic datosRegistroTopico,
                                          UriComponentsBuilder uriComponentsBuilder) {
        var topico = new Topico(datosRegistroTopico);
        topicoRepository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DataDetailsTopic(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DataListTopic>> datosListaTopicos(@PageableDefault(size = 5, sort = {"curso"}) Pageable pageable) {
        var page = topicoRepository.findAll(pageable).map(DataListTopic::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DataUpdateTopic datosActualizarTopico) {
        var topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarInformacion(datosActualizarTopico);
        return ResponseEntity.ok(new DataDetailsTopic(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).orElse(null);
        if (topico == null) {
            return ResponseEntity.noContent().build();
        }
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailsTopic(topico));
    }
}