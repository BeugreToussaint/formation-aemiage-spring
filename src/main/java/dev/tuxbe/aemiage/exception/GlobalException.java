package dev.tuxbe.aemiage.exception;

import dev.tuxbe.aemiage.model.ResponseApi;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {

    private final static Logger log = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler(AccountAlreadyExistException.class)
    public ResponseEntity<ResponseApi> exception(AccountAlreadyExistException e) {
        log.info("Une erreur est survenue : {}", e.getMessage());
        return new ResponseEntity<>( new ResponseApi(true, e.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseApi> exception(ResourceNotFoundException e) {
        log.info("Une erreur est survenue : {}", e.getMessage());
        return new ResponseEntity<>(new ResponseApi(true, e.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ResponseApi> exception(InsufficientBalanceException e) {
        log.info("Une erreur est survenue : {}", e.getMessage());
        return new ResponseEntity<>(new ResponseApi(true, e.getMessage(), null), HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseApi> exception(IllegalArgumentException e) {
        log.info("Une erreur est survenue : {}", e.getMessage());
        return new ResponseEntity<>(new ResponseApi(true, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseApi> exception(DataIntegrityViolationException e) {
        log.info("Une erreur est l'intégrité des données : {}", e.getMostSpecificCause().getMessage());
        return new ResponseEntity<>(new ResponseApi(true, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseApi> exception(ConstraintViolationException e) {
        log.info("Violation des contraintes : {}", e.getLocalizedMessage());
        return new ResponseEntity<>(new ResponseApi(true, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TypeAccountDontExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseApi> exception(TypeAccountDontExistException e) {
        log.info("Une erreur est survenue : {}", e.getMessage());
        return new ResponseEntity<>(new ResponseApi(true, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseApi> exception(Exception e) {
//        log.info("Erreur global : {}", e.getMessage());
//        return new ResponseEntity<>(new ResponseApi(true, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
//    }


}
