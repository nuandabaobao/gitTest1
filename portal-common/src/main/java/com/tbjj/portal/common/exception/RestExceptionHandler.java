package com.tbjj.portal.common.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tbjj.portal.common.entity.TransResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Fu JinHui
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value={ServiceException.class})
    public ResponseEntity<?> handleExceptionInternal(ServiceException ex,WebRequest webRequest) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ErrorInfo errorInfo = new ErrorInfo(ex.getErrCode(),ex.getMessage(),ex.getErrData());
        TransResult transResult = new TransResult();
        transResult.success();
        transResult.setContent(errorInfo);
        String body = new ObjectMapper().writeValueAsString(transResult);

        return handleExceptionInternal(ex,body,headers,HttpStatus.OK,webRequest);
    }

    /**
     *
     * */
    protected ResponseEntity<?> handleExceptionInternal(Throwable ex,Object body,HttpHeaders headers,HttpStatus status,WebRequest request){

        logger.warn(ex.getMessage(), ex);
        if(HttpStatus.INTERNAL_SERVER_ERROR.equals(status)){
            request.setAttribute("java.servlet.error.exception",ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<Object>(body,headers,status);
    }
}
