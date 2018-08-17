package fr.mazerty.shika.ishi.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * A {@link ViewChangeListener} that closes all opened {@link Window}s before moving on to the next {@link View}.
 */
public class WindowCloserViewChangeListener implements ViewChangeListener {

    @Override
    public boolean beforeViewChange(ViewChangeEvent event) {
        // popups are not tied to the views, which is a shame, so we have to close them before moving on
        UI.getCurrent().getWindows().forEach(Window::close);

        return true;
    }

    @Override
    public void afterViewChange(ViewChangeEvent event) {
    }

}
