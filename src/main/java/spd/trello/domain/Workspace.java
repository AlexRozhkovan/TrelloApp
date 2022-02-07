package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Generated
@Entity
@Table(name = "workspaces")
public class Workspace extends Resource {

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private WorkspaceVisibility visibility = WorkspaceVisibility.PUBLIC;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "workspaces_members",
            joinColumns=@JoinColumn(name= "workspace_id")
    )
    @Column(name = "member_id")
    Set<UUID> memberIds = new HashSet<>();;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "workspace", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("workspace")
    private List<Board> boards = new ArrayList<>();

}