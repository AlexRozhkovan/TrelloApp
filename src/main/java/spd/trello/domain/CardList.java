package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import spd.trello.domain.parent_classes.ArchivedResource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "card_lists")
public class CardList extends ArchivedResource {

    @Column(name = "name")
    private String name;

    @Column(name = "board_id")
    private UUID boardId;
}