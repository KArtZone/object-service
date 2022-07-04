package tech.inno.objectservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.inno.objectservice.domain.enums.ResourceState;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto {

    private UUID id;

    private String title;

    private String description;

    private ResourceState state;

    private String createdByName;

}
