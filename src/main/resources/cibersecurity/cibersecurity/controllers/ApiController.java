import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final OpenAIApiService openaiService;

    public ApiController(OpenAIApiService openaiService) {
        this.openaiService = openaiService;
    }

    @PostMapping("/api/completion")
    public String getCompletion(@RequestBody String prompt) {
        return openaiService.getCompletion(prompt);
    }
}