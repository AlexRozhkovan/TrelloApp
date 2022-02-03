package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Generated
@Entity
@Table(name = "comments")
public class Comment extends Resource {

    private String text;
    private UUID userId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    @JsonIgnoreProperties("comment")
    private Card card;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "comment", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("comment")
    private List<Attachment> attachments = new ArrayList<>();
}
