package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjc.school.service.dto.AuthorDtoRequest;

public class Main {
    public static void main(String[] args) {
        AuthorDtoRequest newsDtoRequest = new AuthorDtoRequest( "Serephim");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String json = objectMapper.writeValueAsString(newsDtoRequest);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

