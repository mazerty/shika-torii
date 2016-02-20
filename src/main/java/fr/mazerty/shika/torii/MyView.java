package fr.mazerty.shika.torii;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;

public abstract class MyView extends VerticalLayout implements View {

    public void navigateTo(String navigationState) {
        getUI().getNavigator().navigateTo(navigationState);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
