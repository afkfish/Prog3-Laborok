package JavaUtil;

import java.io.Serializable;

public record Beer(String name, String style, double strength) implements Serializable {
}