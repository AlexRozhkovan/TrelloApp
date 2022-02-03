package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;

@Getter
@Setter
@Generated
@Entity
@Table(name = "attachments")
public class Attachment extends Resource {

    private String name;
    private String link;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    @JsonIgnoreProperties("attachment")
    private Card card;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    @JsonIgnoreProperties("attachment")
    private Comment comment;
}
