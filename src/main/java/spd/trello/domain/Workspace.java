package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.UniqueElements;
import spd.trello.domain.enums.WorkspaceVisibility;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "workspaces")
public class Workspace extends Resource {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "workspace_member",
            joinColumns = @JoinColumn(name = "workspace_id"))
    @Column(name = "member_id")
    @NotEmpty(message = "You can't create board without members")
    @UniqueElements(message = "Member is exist")
    private List<UUID> members = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private WorkspaceVisibility visibility = WorkspaceVisibility.PUBLIC;

}