package com.alura.forum.core.crud.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<StandardError> handlerFieldsError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        String error = "Erro de validação";
        String message = "Verifique os campos informados";
        StandardError err = new StandardError(
            HttpStatus.BAD_REQUEST.value(), 
            error, 
            message,
            request.getRequestURI(),
            LocalDateTime.now(),
            errors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<StandardError> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        String error = "Recurso não encontrado";
        StandardError err = new StandardError(
            HttpStatus.NOT_FOUND.value(), 
            error, 
            ex.getMessage(),
            request.getRequestURI(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<StandardError> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        String error = "Parâmetro inválido";
        StandardError err = new StandardError(
            HttpStatus.BAD_REQUEST.value(), 
            error, 
            ex.getMessage(),
            request.getRequestURI(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<StandardError> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        String error = "Acesso negado";
        StandardError err = new StandardError(
            HttpStatus.FORBIDDEN.value(), 
            error, 
            "Você não tem permissão para acessar este recurso",
            request.getRequestURI(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<StandardError> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
        String error = "Erro de autenticação";
        StandardError err = new StandardError(
            HttpStatus.UNAUTHORIZED.value(), 
            error, 
            "Credenciais inválidas",
            request.getRequestURI(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
    
    @ExceptionHandler(AuthenticationException.class)
    private ResponseEntity<StandardError> handleAuthentication(AuthenticationException ex, HttpServletRequest request) {
        String error = "Erro de autenticação";
        StandardError err = new StandardError(
            HttpStatus.UNAUTHORIZED.value(), 
            error, 
            ex.getMessage(),
            request.getRequestURI(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<StandardError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        String error = "Erro no formato da requisição";
        StandardError err = new StandardError(
            HttpStatus.BAD_REQUEST.value(), 
            error, 
            "O corpo da requisição está mal formatado ou contém valores inválidos",
            request.getRequestURI(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<StandardError> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String error = "Erro de tipo de parâmetro";
        StandardError err = new StandardError(
            HttpStatus.BAD_REQUEST.value(), 
            error, 
            "O parâmetro '" + ex.getName() + "' deve ser do tipo " + ex.getRequiredType().getSimpleName(),
            request.getRequestURI(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<StandardError> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        String error = "Erro de integridade de dados";
        StandardError err = new StandardError(
            HttpStatus.CONFLICT.value(), 
            error, 
            "Operação não pode ser concluída devido a restrições de integridade",
            request.getRequestURI(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }
    
    @ExceptionHandler(Exception.class)
    private ResponseEntity<StandardError> handleGenericException(Exception ex, HttpServletRequest request) {
        String error = "Erro interno do servidor";
        StandardError err = new StandardError(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            error, 
            "Ocorreu um erro inesperado no servidor",
            request.getRequestURI(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
