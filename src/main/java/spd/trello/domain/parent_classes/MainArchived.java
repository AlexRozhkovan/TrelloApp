package spd.trello.domain.parent_classes;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class MainArchived extends Resource {
    private Boolean archived = false;
}
