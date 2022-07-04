package tech.inno.objectservice.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import tech.inno.objectservice.domain.dto.OpenDto;
import tech.inno.objectservice.domain.dto.ResourceDto;
import tech.inno.objectservice.service.ResourceService;

import java.util.UUID;
import java.util.function.Function;

import static tech.inno.objectservice.config.Constants.REQUEST_ID;
import static tech.inno.objectservice.config.Constants.USER_ID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenResource implements Function<Message<OpenDto>, Message<ResourceDto>> {

    private final ResourceService service;

    @Override
    public Message<ResourceDto> apply(final Message<OpenDto> message) {
        final MessageHeaders headers = message.getHeaders();
        final String userId = headers.get(USER_ID, String.class);
        final String requestId = message.getHeaders().get(REQUEST_ID, String.class);
        final UUID id = message.getPayload().getId();
        log.info("RequestId: {}", requestId);
        log.info("Search resource with id: {} from db by user with id: {}", id, userId);
        //todo  Добавить handler ошибок с отправкой в очередь ошибок
        final ResourceDto resourceDto = service.getById(id);
        return MessageBuilder.withPayload(resourceDto)
                .setHeader(REQUEST_ID, requestId)
                .build();
    }
}
