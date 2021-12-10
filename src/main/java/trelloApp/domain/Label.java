package trelloApp.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@RequiredArgsConstructor
public class Label {

    private String name;
    private Color color;
}
