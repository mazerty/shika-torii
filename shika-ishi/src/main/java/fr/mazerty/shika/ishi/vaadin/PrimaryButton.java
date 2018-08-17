package fr.mazerty.shika.ishi.vaadin;

import com.vaadin.ui.Button;

import static com.vaadin.event.ShortcutAction.KeyCode.ENTER;
import static com.vaadin.ui.themes.ValoTheme.BUTTON_PRIMARY;

/**
 * Custom {@link Button} with "primary" style and behavior
 */
public class PrimaryButton extends Button {

    public PrimaryButton() {
        addStyleName(BUTTON_PRIMARY);
        setClickShortcut(ENTER);
    }

    public PrimaryButton(String caption) {
        this();
        setCaption(caption);
    }

}
