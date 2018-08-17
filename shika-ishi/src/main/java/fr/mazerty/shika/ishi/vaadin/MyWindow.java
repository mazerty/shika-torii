package fr.mazerty.shika.ishi.vaadin;

import com.vaadin.ui.Window;

/**
 * Common structure for the popups.
 *
 * @see UiManager
 */
public abstract class MyWindow extends Window implements UiManager {

    /**
     * Override if some code has to be executed upon showing the window.
     */
    protected void enter() {
    }

}
