package tech.inno.objectservice.service;

import tech.inno.objectservice.domain.dto.ResourceDto;
import tech.inno.objectservice.domain.entity.Resource;

import java.util.UUID;

public interface ResourceService {

    ResourceDto getById(UUID id);

    ResourceDto save(Resource resource);

}
