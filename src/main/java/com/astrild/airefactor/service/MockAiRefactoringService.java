package com.astrild.airefactor.service;

import com.astrild.airefactor.context.MethodContext;

public class MockAiRefactoringService implements AiRefactoringService {

    @Override
    public String suggestRefactoring(MethodContext context, String prompt) {
        String original = context.getMethodText();

        String improved = original
                .replace("==null", " == null")
                .replace("if(", "if (")
                .replace("){", ") {")
                .replace("}else{", "} else {")
                .replace("else{", "else {")
                .replace("return\"\";", "return \"\";")
                .replace("return \"\" ;", "return \"\";")
                .replace("== true", "")
                .replace("if (", "if (");

        if (improved.equals(original)) {
            return """
                    // AI suggestion:
                    // The method already looks mostly valid.
                    // Possible improvements:
                    // - simplify conditions
                    // - improve variable naming
                    // - reduce nested if statements
                    // - keep formatting consistent

                    %s
                    """.formatted(original);
        }

        return improved;
    }
}