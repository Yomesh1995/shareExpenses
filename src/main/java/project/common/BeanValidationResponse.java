package project.common;

import java.util.List;
import java.util.Map;

import project.model.CommonResponse;

public class BeanValidationResponse extends CommonResponse {

    private List<Map<String,String>> errorFields;

    public List<Map<String, String>> getErrorField() {
        return errorFields;
    }

    public void setErrorField(List<Map<String, String>> errorFields) {
        this.errorFields = errorFields;
    }
}
