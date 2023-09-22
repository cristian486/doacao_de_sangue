package br.com.application.blooddonation.infra.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErroAdviceController {

    @ExceptionHandler(DoacaoException.class)
    ResponseEntity<String> tratarErroAplicacao(DoacaoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<CampoIncorreto>> tratarErroValidacaoDados(MethodArgumentNotValidException ex) {
        List<CampoIncorreto> camposIncorretos = ex.getFieldErrors().stream().map(CampoIncorreto::new).toList();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(camposIncorretos);
    }

    private record CampoIncorreto(String campo, String mensagem) {
        private CampoIncorreto(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
