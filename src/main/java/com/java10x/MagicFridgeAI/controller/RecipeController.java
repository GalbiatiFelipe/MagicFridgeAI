package com.java10x.MagicFridgeAI.controller;

import com.java10x.MagicFridgeAI.service.ChatGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final ChatGptService chatGptService;

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecipe() {
        return chatGptService.generateRecipe()
                .map(recipe -> ResponseEntity.ok(recipe))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
    /*
    * 1 - Mono:
    *       Uma forma de fazermos requisições assíncronas
    *  1.1 - Assincronismo:
    *          É como se fosse uma promessa, por exemplo quando mandamos um prompt para uma I.A não sabemos se ela vai conseguir
    *          entender completamente o que queremos dizer, mas mesmo assim ela vai responder. Uma conversa com outra pessoa por
    *          exemplo é uma coisa assíncrona.
    * */

}
