package com.alura.forum.controller;

import com.alura.forum.converter.AnswerConverter;
import com.alura.forum.converter.TopicConverter;
import com.alura.forum.core.crud.CrudController;
import com.alura.forum.model.dto.request.AnswerDTO;
import com.alura.forum.model.dto.request.TopicDTO;
import com.alura.forum.model.dto.request.TopicFilterDTO;
import com.alura.forum.model.dto.response.AnswerResponseDTO;
import com.alura.forum.model.dto.response.TopicResponseDTO;
import com.alura.forum.model.entity.Answer;
import com.alura.forum.model.entity.Topic;
import com.alura.forum.model.entity.enums.TopicStatus;
import com.alura.forum.model.projections.AnswerSlim;
import com.alura.forum.model.projections.TopicCompleteDTO;
import com.alura.forum.model.projections.TopicSlimDTO;
import com.alura.forum.service.AnswerService;
import com.alura.forum.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topics")
@AllArgsConstructor
@Tag(name = "Tópicos", description = "Endpoints para gerenciamento de tópicos do fórum")
public class TopicController extends CrudController<Topic, Long, TopicDTO, TopicResponseDTO> {

    private TopicService topicService;
    private AnswerService answerService;
    private AnswerConverter answerConverter;
    private TopicConverter topicConverter;

    @Override
    @Operation(summary = "Listar todos os tópicos", description = "Retorna uma lista paginada de todos os tópicos do fórum")
    public ResponseEntity<Page<TopicSlimDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(topicService.findAllSlim(pageable));
    }
    
    @GetMapping("/filter")
    @Operation(summary = "Buscar tópicos com filtros", description = "Permite buscar tópicos com diversos filtros combinados")
    public ResponseEntity<Page<TopicResponseDTO>> findByFilters(TopicFilterDTO filters, Pageable pageable) {
        Page<Topic> topics = topicService.findByFilters(filters, pageable);
        Page<TopicResponseDTO> dtoPage = topics.map(topicConverter::entityToDTOResponse);
        return ResponseEntity.ok(dtoPage);
    }
    
    @GetMapping("/search")
    @Operation(summary = "Buscar tópicos por título", description = "Busca tópicos que contenham o texto informado no título")
    public ResponseEntity<Page<TopicSlimDTO>> findByTitle(@RequestParam String title, Pageable pageable) {
        return ResponseEntity.ok(topicService.findByTitle(title, pageable));
    }
    
    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Buscar tópicos por categoria", description = "Busca tópicos de uma categoria específica")
    public ResponseEntity<Page<TopicSlimDTO>> findByCategory(@PathVariable Long categoryId, Pageable pageable) {
        return ResponseEntity.ok(topicService.findByCategory(categoryId, pageable));
    }
    
    @GetMapping("/course/{courseId}")
    @Operation(summary = "Buscar tópicos por curso", description = "Busca tópicos de um curso específico")
    public ResponseEntity<Page<TopicSlimDTO>> findByCourse(@PathVariable Long courseId, Pageable pageable) {
        return ResponseEntity.ok(topicService.findByCourse(courseId, pageable));
    }
    
    @GetMapping("/status/{status}")
    @Operation(summary = "Buscar tópicos por status", description = "Busca tópicos com um status específico")
    public ResponseEntity<Page<TopicSlimDTO>> findByStatus(@PathVariable TopicStatus status, Pageable pageable) {
        return ResponseEntity.ok(topicService.findByStatus(status, pageable));
    }
    
    @GetMapping("/date-range")
    @Operation(summary = "Buscar tópicos por período", description = "Busca tópicos criados em um período específico")
    public ResponseEntity<Page<TopicSlimDTO>> findByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            Pageable pageable) {
        return ResponseEntity.ok(topicService.findByDateRange(start, end, pageable));
    }

    @Override
    @Operation(summary = "Buscar tópico por ID", description = "Retorna os detalhes completos de um tópico específico")
    public ResponseEntity<TopicCompleteDTO> findById(@PathVariable("id") Long aLong) {
        return ResponseEntity.ok(topicService.findComplete(aLong));
    }

    @GetMapping("/{id}/answers")
    @Operation(summary = "Listar respostas de um tópico", description = "Retorna todas as respostas associadas a um tópico específico")
    public ResponseEntity<Page<AnswerSlim>> findAnswersByTopic(@PathVariable("id") Long id, Pageable pageable) {
        Page<AnswerSlim> answers = answerService.findAllByTopicId(id, pageable);
        return ResponseEntity.ok(answers);
    }

    @Transactional
    @PostMapping("/{id}/answers")
    @Operation(summary = "Adicionar resposta a um tópico", description = "Adiciona uma nova resposta a um tópico específico")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<AnswerResponseDTO> addAnswerToPost(@PathVariable("id") Long id, @RequestBody @Valid AnswerDTO answerDTO) {
        Answer answer = answerConverter.dtoCadToEntity(answerDTO);
        Answer answer1 = topicService.addAnswerToPost(answer, id);
        return ResponseEntity.ok(answerConverter.entityToDTOResponse(answer1));
    }

    @Override
    @PostMapping
    @Transactional
    @Operation(summary = "Criar novo tópico", description = "Cria um novo tópico no fórum")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<?> create(@RequestBody @Valid TopicDTO dto) {
        return super.create(dto);
    }

    @Override
    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualizar tópico", description = "Atualiza as informações de um tópico existente")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<TopicResponseDTO> edit(@RequestBody @Valid TopicDTO dto, @PathVariable Long id) {
        return super.edit(dto, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Excluir tópico", description = "Remove um tópico do fórum")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return super.delete(id);
    }
}
