package tech.inno.objectservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import tech.inno.objectservice.domain.dto.OpenDto;
import tech.inno.objectservice.domain.dto.UserDto;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class HrClient {

    private WebClient client;

    @PostConstruct
    public void init() {
        client = WebClient.create("http://localhost:8083");
    }

    public UserDto getById(OpenDto openDto) {
        return client.post()
                .uri("/openUser")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(openDto))
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }
}
