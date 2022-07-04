package tech.inno.objectservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.inno.objectservice.client.HrClient;
import tech.inno.objectservice.domain.dto.OpenDto;
import tech.inno.objectservice.domain.dto.ResourceDto;
import tech.inno.objectservice.domain.dto.UserDto;
import tech.inno.objectservice.domain.entity.Resource;
import tech.inno.objectservice.domain.mapper.ResourceMapper;
import tech.inno.objectservice.repository.ResourceRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.UUID;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository repository;

    private final ResourceMapper mapper;

    private final HrClient hrClient;

    @Override
    @Transactional
    public ResourceDto getById(UUID id) {
        final Resource byId = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        format("Resource with id %s not found", id)));
        //todo  Добавить handler ошибок с отправкой в очередь ошибок
        final UserDto userDto = hrClient.getById(new OpenDto(byId.getCreatedBy()));
        final ResourceDto resourceDto = mapper.toDto(byId);
        resourceDto.setState(byId.getState());
        resourceDto.setCreatedByName(String.format("%s %s", userDto.getFirstName(), userDto.getLastName()));
        return resourceDto;
    }

    @Override
    public ResourceDto save(Resource resource) {
        final Resource saved = repository.save(resource);
        final UserDto userDto = hrClient.getById(new OpenDto(saved.getCreatedBy()));
        final ResourceDto resourceDto = mapper.toDto(saved);
        resourceDto.setState(saved.getState());
        resourceDto.setCreatedByName(String.format("%s %s", userDto.getFirstName(), userDto.getLastName()));
        return resourceDto;
    }
}
