package trelloApp.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckableItem {
    private String name;
    private boolean checked;
}
