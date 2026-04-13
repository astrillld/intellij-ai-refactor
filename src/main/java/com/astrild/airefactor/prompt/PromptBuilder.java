package com.astrild.airefactor.prompt;

import com.astrild.airefactor.context.MethodContext;

public class PromptBuilder {

    public String buildRefactoringPrompt(MethodContext context) {
        return """
                You are an AI assistant helping with Java refactoring.

                Refactor the following Java method to improve:
                - readability
                - naming clarity
                - simplicity
                - basic code style

                Preserve the original behavior.
                Do not add external libraries.
                Return only the refactored Java method.

                Class:
                %s

                Method signature:
                %s

                Original method:
                %s
                """.formatted(
                context.getClassName(),
                context.getMethodSignature(),
                context.getMethodText()
        );
    }
}