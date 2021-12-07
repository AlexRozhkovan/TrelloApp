package trelloApp.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CheckList {
    private String name;
    private List<CheckableItem> items;
}
