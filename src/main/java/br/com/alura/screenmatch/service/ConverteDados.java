package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component //
public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> tipo) {
        try {
            return mapper.readValue(json, tipo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
