package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "workspaces")
public class Workspace extends Resource {

    private String name;
    private String description;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "space_member",
            joinColumns = @JoinColumn(name = "space_id"))
    @Column(name = "member_id")
    @NotEmpty(message = "workspace must contain at least one member")
    private Set<UUID> workspaceMembers = new LinkedHashSet<>();

    @Enumerated(EnumType.STRING)
    private WorkspaceVisibility visibility = WorkspaceVisibility.PUBLIC;

}
