package project.common;

import project.model.common.CommonResponse;

import java.util.List;
import java.util.Map;

public class BeanValidationResponse extends CommonResponse {

    private List<Map<String,String>> errorFields;

    public List<Map<String, String>> getErrorField() {
        return errorFields;
    }

    public void setErrorField(List<Map<String, String>> errorFields) {
        this.errorFields = errorFields;
    }
}
