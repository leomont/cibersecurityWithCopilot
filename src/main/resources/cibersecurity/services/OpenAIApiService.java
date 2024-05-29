package cibersecurity.services;

//import com.openai.api.OpenAIAPI;
//import com.openai.api.model.v1.Completion;
//import com.openai.api.model.v1.CompletionCreate;

public class OpenAIApiService {

//    private final OpenAIAPI openai;
//
//    public OpenAIApiService(String apiKey) {
//        this.openai = new OpenAIAPI(apiKey);
//    }
//
//    public String getCompletion(String prompt) {
//        CompletionCreate completionCreate = new CompletionCreate();
//        completionCreate.setPrompt(prompt);
//        Completion completion = openai.createCompletion(completionCreate);
//        return completion.getChoices().get(0).getText();
//    }

    public String getCompletion(String prompt) {
        String question = createJsonString(prompt);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/engines/davinci-codex/completions"))
                .header("Authorization", "Bearer ")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(question))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if (response.statusCode() == 200) {
            System.out.println("Response received: " + response.body());
        } else {
            System.out.println("Error: " + response.statusCode());
        }

        return response.body();
    }

    public String createJsonString(String prompt) {
        JSONObject message = new JSONObject();
        message.put("role", "assistant");
        message.put("content", prompt);

        JSONArray messages = new JSONArray();
        messages.put(message);

        JSONObject json = new JSONObject();
        json.put("model", "gpt-3.5-turbo");
        json.put("messages", messages);
        json.put("temperature", 0.7);

        return json.toString();
    }
}