package trelloApp.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CheckableItem {
    private String name;
    private boolean checked;
}
