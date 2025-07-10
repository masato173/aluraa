package com.alura.forum.repository;

import com.alura.forum.core.crud.CrudRepository;
import com.alura.forum.model.entity.Topic;
import com.alura.forum.model.entity.enums.TopicStatus;
import com.alura.forum.model.projections.TopicCompleteDTO;
import com.alura.forum.model.projections.TopicSlimDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

    Page<TopicSlimDTO> findAllBy(Pageable pageable);

    Optional<TopicCompleteDTO> findTopicCompleteById(Long aLong);

    @Query("SELECT t.status FROM Topic t WHERE t.id = :id")
    Integer findStatusById(Long id);

    List<TopicSlimDTO> findAllTopicSlimByCategoryId(Long id);

    List<TopicSlimDTO> findAllByAuthor(UserDetails user);

    @Override
    Optional<Topic> findById(Long aLong);
    
    // Busca por título contendo o texto
    Page<TopicSlimDTO> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    
    // Busca por mensagem contendo o texto
    Page<TopicSlimDTO> findByMessageContainingIgnoreCase(String message, Pageable pageable);
    
    // Busca por status
    Page<TopicSlimDTO> findByStatus(TopicStatus status, Pageable pageable);
    
    // Busca por categoria
    Page<TopicSlimDTO> findByCategoryId(Long categoryId, Pageable pageable);
    
    // Busca por curso
    Page<TopicSlimDTO> findByCourseId(Long courseId, Pageable pageable);
    
    // Busca por data de criação entre um período
    Page<TopicSlimDTO> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
    
    // Busca combinada com vários filtros
    @Query("SELECT t FROM Topic t WHERE " +
           "(:title IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
           "(:categoryId IS NULL OR t.category.id = :categoryId) AND " +
           "(:courseId IS NULL OR t.course.id = :courseId) AND " +
           "(:status IS NULL OR t.status = :status) AND " +
           "(:startDate IS NULL OR t.createdAt >= :startDate) AND " +
           "(:endDate IS NULL OR t.createdAt <= :endDate)")
    Page<Topic> findByFilters(
            @Param("title") String title,
            @Param("categoryId") Long categoryId,
            @Param("courseId") Long courseId,
            @Param("status") TopicStatus status,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable);
}

