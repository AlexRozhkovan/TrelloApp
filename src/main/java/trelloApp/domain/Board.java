package trelloApp.domain;

import lombok.RequiredArgsConstructor;
import trelloApp.enumerations.BoardVisibilityEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Board {
    private String name;
    private String description;
    private boolean favouriteStatus;
    private boolean isArchived;
    private BoardVisibilityEnum visibility;
    private List<Member> members;
    private List<CardList> cardLists;
}
