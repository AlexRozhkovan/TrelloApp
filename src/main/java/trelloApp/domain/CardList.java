package trelloApp.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CardList {
    private String name;
    private boolean isArchived;
    private List<Card> cards;
}
