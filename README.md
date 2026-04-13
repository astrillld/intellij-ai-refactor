# AI Refactoring Assistant (IntelliJ Plugin)

A simple AI-oriented IntelliJ plugin that extracts a Java method from the editor, builds an LLM-style refactoring prompt, and shows a suggested improved version of the method.

## Features

- Detects the Java method under the cursor
- Extracts method context using PSI (Program Structure Interface)
- Builds an AI-style refactoring prompt
- Generates a suggested refactoring (mock AI service)
- Displays original code, prompt, and suggested version

## How it works

1. User places the cursor inside a Java method
2. The plugin extracts the method using IntelliJ PSI
3. A structured prompt is generated
4. The prompt is passed to an AI service layer
5. A suggested refactored method is returned and displayed

## Project Structure

context/ -> PSI extraction and data model
prompt/ -> prompt generation
service/ -> AI service abstraction
RefactorMethodAction -> entry point

## Usage of gen AI tools

I used generative AI tools (such as ChatGPT) to:
- explore IntelliJ Platform SDK usage,
- design project structure,
- generate and refine code examples,
- debug Gradle and plugin configuration issues.

All code was reviewed, tested, and adapted manually to fit the project requirements.