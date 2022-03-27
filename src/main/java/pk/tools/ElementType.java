package pk.tools;

import lombok.AllArgsConstructor;
import pk.tools.utils.PropertyLoader;

@AllArgsConstructor
public enum ElementType {

    BUTTON("button", "кнопка"),
    LINK("link", "ссылка"),
    TEXT("text", "текст"),
    ICON("icon", "иконка"),
    IMAGE("img", "изображение"),
    INPUT("input text field","поле ввода");

    String english;
    String russian;

    public String getType() {
        switch (PropertyLoader.loadSystemPropertyOrDefault("localization", "english")) {
            default:
                return english;
            case "ru":
                return russian;
        }
    }
}
