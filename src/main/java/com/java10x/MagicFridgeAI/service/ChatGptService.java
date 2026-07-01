package com.java10x.MagicFridgeAI.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private String apiKey = System.getenv("API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    /*
    * curl "https://api.openai.com/v1/responses" \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer $OPENAI_API_KEY" \
    -d '{
        "model": "gpt-5.5",
        "reasoning": {"effort": "low"},
        "input": [
            {
                "role": "developer",
                "content": "Talk like a pirate."
            },
            {
                "role": "user",
                "content": "Are semicolons optional in JavaScript?"
            }
        ]
    }'
    * */
    public Mono<String> generateRecipe() {
        String prompt = "Me sugira uma receita simples com ingredientes comuns";
        Map requestBody = Map.of(
                "model", "gpt-5.5",
                "reasoning", Map.of("effort", "low"), //define baixo esforço de raciocínio,reduz latência e custo
                "input", List.of(
                        Map.of("role", "developer","content", "Você é um assistente que cria receitas"),
                        Map.of("role", "user", "content", prompt)
                )
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    // CORREÇÃO 1: Na nova API, o nó principal se chama "output" (e não mais "choices")
                    var output = (List<Map<String, Object>>) response.get("output");

                    if (output != null && !output.isEmpty()) {
                        // Pegamos o primeiro bloco do output
                        Map<String, Object> firstOutput = output.get(0);

                        // CORREÇÃO 2: O texto não fica direto no primeiro nível, ele fica dentro de "content" que é outra lista
                        var contentList = (List<Map<String, Object>>) firstOutput.get("content");

                        if (contentList != null && !contentList.isEmpty()) {
                            // Varre a lista de conteúdos para achar o tipo "output_text"
                            for (Map<String, Object> contentItem : contentList) {
                                if ("output_text".equals(contentItem.get("type"))) {
                                    return contentItem.get("text").toString();
                                }
                            }
                        }
                    }
                    return "Nenhuma receita foi gerada.";
                });
    }

}
