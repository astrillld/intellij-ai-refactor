package com.astrild.airefactor;

import com.astrild.airefactor.context.MethodContext;
import com.astrild.airefactor.context.PsiMethodContextExtractor;
import com.astrild.airefactor.prompt.PromptBuilder;
import com.astrild.airefactor.service.AiRefactoringService;
import com.astrild.airefactor.service.MockAiRefactoringService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiMethod;

public class RefactorMethodAction extends AnAction {

    private final PsiMethodContextExtractor extractor = new PsiMethodContextExtractor();
    private final PromptBuilder promptBuilder = new PromptBuilder();
    private final AiRefactoringService aiService = new MockAiRefactoringService();

    public RefactorMethodAction() {
        super("AI Refactor Method");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);

        if (project == null || editor == null || psiFile == null) {
            Messages.showErrorDialog("No editor or file is available.", "AI Refactor Method");
            return;
        }

        int offset = editor.getCaretModel().getOffset();
        PsiElement element = psiFile.findElementAt(offset);

        while (element != null && !(element instanceof PsiMethod)) {
            element = element.getParent();
        }

        if (!(element instanceof PsiMethod method)) {
            Messages.showErrorDialog("Place the cursor inside a Java method.", "AI Refactor Method");
            return;
        }

        MethodContext context = extractor.extract(method);
        String prompt = promptBuilder.buildRefactoringPrompt(context);
        String suggestion = aiService.suggestRefactoring(context, prompt);

        String message = buildResultMessage(context, prompt, suggestion);
        Messages.showInfoMessage(project, message, "AI Refactoring Suggestion");
    }

    private String buildResultMessage(MethodContext context, String prompt, String suggestion) {
        return """
                Class: %s
                Method: %s
                Signature: %s

                ===== Original Method =====
                %s

                ===== AI Prompt =====
                %s

                ===== Suggested Refactoring =====
                %s
                """.formatted(
                context.getClassName(),
                context.getMethodName(),
                context.getMethodSignature(),
                context.getMethodText(),
                prompt,
                suggestion
        );
    }
}