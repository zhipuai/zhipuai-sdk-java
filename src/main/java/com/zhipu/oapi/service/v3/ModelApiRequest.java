package com.zhipu.oapi.service.v3;

import javafx.beans.binding.ObjectExpression;

import java.util.List;
import java.util.Map;

public class ModelApiRequest {

    // path variables
    private String invokeMethod;
    private String modelId;

    // 模型控制参数，提供默认值
    // 如果您想自定义请务必先了解参数说明
    private float temperature = 0.9f;
    private float topP = 0.7f;

    // 给模型的输入，用户输入的内容role=user
    // 如果要带历史会话，模型生成的内容role=assistant
    private List<Prompt> prompt;
    private String requestId;

    // sse-params
    private boolean incremental = true;
    private ModelEventSourceListener sseListener;

    // 用于控制每次返回内容的类型，空或者没有此字段时默认按照json_string返回
    private String returnType;

    private Integer maxTokens;

    public Integer getMaxTokens(){
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens){
        this.maxTokens = maxTokens;
    }

    private Map<String, Object> ref;

    public Map<String, Object> getRef(){
        return ref;
    }

    public void setRef(Map<String, Object> ref){
        this.ref = ref;
    }

    public String getReturnType(){
        return returnType;
    }

    public void setReturnType(String returnType){
        this.returnType = returnType;
    }

    public String getInvokeMethod() {
        return invokeMethod;
    }

    public void setInvokeMethod(String invokeMethod) {
        this.invokeMethod = invokeMethod;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTopP() {
        return topP;
    }

    public void setTopP(float topP) {
        this.topP = topP;
    }

    public List<Prompt> getPrompt() {
        return prompt;
    }

    public void setPrompt(List<Prompt> prompt) {
        this.prompt = prompt;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ModelEventSourceListener getSseListener() {
        return sseListener;
    }

    public boolean isIncremental() {
        return incremental;
    }

    public void setIncremental(boolean incremental) {
        this.incremental = incremental;
    }

    public void setSseListener(ModelEventSourceListener sseListener) {
        this.sseListener = sseListener;
    }

    public static class Prompt {
        private String role;
        private String content;

        public Prompt(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
