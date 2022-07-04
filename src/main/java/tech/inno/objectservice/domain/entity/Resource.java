package tech.inno.objectservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.inno.objectservice.domain.enums.ResourceState;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    @Enumerated(STRING)
    private ResourceState state;

    private UUID createdBy;
}
