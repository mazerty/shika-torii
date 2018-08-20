package fr.mazerty.torii.vaadin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Common structure for the views
 */
public abstract class MyView extends VerticalLayout implements View {

    /**
     * Override if some code has to be executed upon showing the view.
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    void navigateTo(String navigationState) {
        UI.getCurrent().getNavigator().navigateTo(navigationState);
    }

}
