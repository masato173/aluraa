package com.alura.forum.util;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe de demonstração de recursos do Java 21
 */
public class Java21Features {

    /**
     * Exemplo de uso de Record Patterns (JEP 440)
     */
    public static String formatTopicInfo(Object topic) {
        if (topic instanceof TopicRecord tr) {
            return "Tópico: " + tr.title() + " (criado em " + tr.createdAt() + ")";
        } else if (topic instanceof TopicWithAuthor ta) {
            return "Tópico: " + ta.title() + " por " + ta.authorName();
        } else {
            return "Formato de tópico desconhecido";
        }
    }

    /**
     * Exemplo de uso de Text Blocks (Java 15+)
     */
    public static String createTopicSummary(String title, String author, int responseCount) {
        return """
               Tópico '%s' criado por %s tem %d resposta(s)
               """.formatted(title, author, responseCount);
    }

    /**
     * Exemplo de uso de Pattern Matching for instanceof (Java 16+)
     */
    public static String describeObject(Object obj) {
        if (obj instanceof String s) {
            return "String de tamanho: " + s.length();
        } else if (obj instanceof Integer i) {
            return "Número inteiro: " + i;
        } else if (obj instanceof List<?> list && !list.isEmpty()) {
            return "Lista não vazia com " + list.size() + " elementos";
        } else if (obj == null) {
            return "Objeto nulo";
        } else {
            return "Outro tipo de objeto: " + obj.getClass().getSimpleName();
        }
    }

    /**
     * Exemplo de uso de Sequenced Collections (JEP 431)
     */
    public static <E> E getFirstElement(List<E> list) {
        return list.isEmpty() ? null : list.getFirst();
    }

    public static <E> E getLastElement(List<E> list) {
        return list.isEmpty() ? null : list.getLast();
    }

    // Exemplos de records para uso com Pattern Matching
    public record TopicRecord(String title, String message, LocalDateTime createdAt) {}
    public record TopicWithAuthor(String title, String authorName) {}
} 