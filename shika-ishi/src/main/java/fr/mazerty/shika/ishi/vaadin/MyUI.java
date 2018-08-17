package fr.mazerty.shika.ishi.vaadin;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import javax.inject.Inject;

/**
 * Custom {@link UI} with default {@link Navigator}, theme and {@link CDIViewProvider}.
 */
@CDIUI("")
@Theme("mytheme")
@PreserveOnRefresh
public abstract class MyUI extends UI {

    @Inject
    private CDIViewProvider cdiViewProvider;

    protected Navigator navigator;

    @Override
    protected void init(VaadinRequest request) {
        // defines that the application consists of a set of annotated views you can access through the ui's navigator
        navigator = new Navigator(this, this);
        navigator.addProvider(cdiViewProvider);
        navigator.addViewChangeListener(new WindowCloserViewChangeListener());

        navigator.navigateTo(getDefaultView());
    }

    protected abstract String getDefaultView();

}
