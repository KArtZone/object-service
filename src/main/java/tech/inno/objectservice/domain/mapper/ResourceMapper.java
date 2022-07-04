package tech.inno.objectservice.domain.mapper;

import org.mapstruct.Mapper;
import tech.inno.objectservice.domain.entity.Resource;
import tech.inno.objectservice.domain.dto.ResourceDto;

@Mapper
public interface ResourceMapper {

    ResourceDto toDto(Resource resource);

    Resource toEntity(ResourceDto resourceDto);

}
