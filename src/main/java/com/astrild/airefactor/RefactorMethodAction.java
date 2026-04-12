package com.astrild.airefactor;

import com.astrild.airefactor.context.MethodContext;
import com.astrild.airefactor.context.PsiMethodContextExtractor;
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

    public RefactorMethodAction() {
        super("AI Refactor Method");
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);

        if (project == null || editor == null || psiFile == null) {
            Messages.showErrorDialog("No editor or file", "AI Refactor Method");
            return;
        }

        int offset = editor.getCaretModel().getOffset();
        PsiElement element = psiFile.findElementAt(offset);

        while (element != null && !(element instanceof PsiMethod)) {
            element = element.getParent();
        }

        if (!(element instanceof PsiMethod)) {
            Messages.showErrorDialog("Place cursor inside a Java method", "AI Refactor Method");
            return;
        }

        PsiMethod method = (PsiMethod) element;
        MethodContext context = extractor.extract(method);

        String message = "Class: " + context.getClassName()
                + "\nMethod: " + context.getMethodName()
                + "\nSignature: " + context.getMethodSignature()
                + "\n\nCode:\n" + context.getMethodText();

        Messages.showInfoMessage(project, message, "Method Context");
    }
}