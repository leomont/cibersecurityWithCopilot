package cibersecurity.services;

import com.openai.api.OpenAIAPI;
import com.openai.api.model.v1.Completion;
import com.openai.api.model.v1.CompletionCreate;

public class OpenAIApiService {

    private final OpenAIAPI openai;

    public OpenAIApiService(String apiKey) {
        this.openai = new OpenAIAPI(apiKey);
    }

    public String getCompletion(String prompt) {
        CompletionCreate completionCreate = new CompletionCreate();
        completionCreate.setPrompt(prompt);
        Completion completion = openai.createCompletion(completionCreate);
        return completion.getChoices().get(0).getText();
    }
}