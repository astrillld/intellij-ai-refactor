package com.astrild.airefactor.service;

import com.astrild.airefactor.context.MethodContext;

public interface AiRefactoringService {
    String suggestRefactoring(MethodContext context, String prompt);
}