package com.astrild.airefactor.context;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;

public class PsiMethodContextExtractor {

    public MethodContext extract(PsiMethod method) {
        String className = extractClassName(method);
        String methodName = method.getName();
        String methodSignature = buildSignature(method);
        String methodText = method.getText();

        return new MethodContext(className, methodName, methodSignature, methodText);
    }

    private String extractClassName(PsiMethod method) {
        PsiClass psiClass = method.getContainingClass();
        return psiClass != null ? psiClass.getName() : "UnknownClass";
    }

    private String buildSignature(PsiMethod method) {
        return method.getName() + method.getParameterList().getText();
    }
}