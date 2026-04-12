package com.astrild.airefactor.context;

public class MethodContext {
    private final String className;
    private final String methodName;
    private final String methodSignature;
    private final String methodText;

    public MethodContext(String className, String methodName, String methodSignature, String methodText) {
        this.className = className;
        this.methodName = methodName;
        this.methodSignature = methodSignature;
        this.methodText = methodText;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getMethodSignature() {
        return methodSignature;
    }

    public String getMethodText() {
        return methodText;
    }
}