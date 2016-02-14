package fr.mazerty.shika.torii;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import javax.inject.Inject;

@CDIUI("")
@Theme("mytheme")
@Title("Torii")
public class MyUI extends UI {

    @Inject
    private CDIViewProvider cdiViewProvider;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator = new Navigator(this, this);
        navigator.addProvider(cdiViewProvider);
        navigator.navigateTo(LoginView.VIEW_NAME);
    }

}
