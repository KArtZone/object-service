package tech.inno.objectservice.function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import tech.inno.objectservice.domain.dto.ResourceDto;
import tech.inno.objectservice.domain.entity.Resource;
import tech.inno.objectservice.domain.mapper.ResourceMapper;
import tech.inno.objectservice.service.ResourceService;

import java.util.UUID;
import java.util.function.Function;

import static tech.inno.objectservice.config.Constants.REQUEST_ID;
import static tech.inno.objectservice.config.Constants.USER_ID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterResource implements Function<Message<ResourceDto>, Message<ResourceDto>> {

    private final ResourceService service;

    private final ResourceMapper mapper;

    @Override
    public Message<ResourceDto> apply(Message<ResourceDto> message) {
        final ResourceDto resourceDto = message.getPayload();
        final String userId = message.getHeaders().get(USER_ID, String.class);
        final String requestId = message.getHeaders().get(REQUEST_ID, String.class);
        log.info("RequestId: {}", requestId);
        log.info("Saving resource: {} to db by user with id: {}", resourceDto, userId);
        final Resource resource = mapper.toEntity(resourceDto);
        resource.setCreatedBy(UUID.fromString(userId));
        resource.setState(resourceDto.getState());
        final ResourceDto saved = service.save(resource);
        return MessageBuilder.withPayload(saved)
                .setHeader(REQUEST_ID, requestId)
                .build();
    }
}
