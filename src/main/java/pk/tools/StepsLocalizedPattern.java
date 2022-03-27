package pk.tools;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StepsLocalizedPattern {
    element("element", "элемент"),
    widget("widget", "виджет");

    String english;
    String russian;

    public String getLocalizedValue(String localization) {
        switch (localization) {
            case "russian":
                return russian;
            default:
                return english;
        }
    }
}
