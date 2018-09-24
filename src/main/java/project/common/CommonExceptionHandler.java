package project.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import project.model.common.CommonResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger=Logger.getLogger(CommonExceptionHandler.class.getName());

    @ExceptionHandler(CustomException.class)
    public CommonResponse customExceptionHandler(HttpServletRequest request, CustomException ex){
        CommonResponse resposne=new CommonResponse();
        logger.info("Request Path {} "+request.getRequestURI());
        resposne.setResponseCode(ex.getErrorCode());
        resposne.setResposneMessage(ex.getErrorMessage());
        return resposne;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Map<String,String>> errorFields = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError->{
            Map<String,String> error= new HashMap<>();
            error.put("errorField",fieldError.getField());
            error.put("errorMessage",fieldError.getDefaultMessage());
        	errorFields.add(error);
        });
        BeanValidationResponse response = new BeanValidationResponse();
        response.setResponseCode(ResponseMessages.BEAN_VALIDATION_ERROR_CODE);
        response.setResposneMessage(ResponseMessages.BEAN_VALIDATION_ERROR_MESSAGE);
        response.setErrorField(errorFields);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
