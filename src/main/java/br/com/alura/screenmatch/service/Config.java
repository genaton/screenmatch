package br.com.alura.screenmatch.service;

// CLASSE USADA PARA OCULTAR A APIKEY
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

// @Component
// @PropertySource("classpath:application.properties")
public class Config {
    // @Value("${api.key}")
    private String apiKey;
    
    // @Value("${openai.api.key}")
    private String openAiApiKey;
    

    public String getApiKey() {
        return apiKey;
    }

    public String getOpenAiApiKey(){
        return openAiApiKey;
    }
}
