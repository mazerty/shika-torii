package fr.mazerty.shika.torii;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import javax.inject.Inject;

@CDIUI("")
@Theme("mytheme")
@Title("Torii")
public class MyUI extends UI {

    @Inject
    private CDIViewProvider cdiViewProvider;
    @Inject
    private Session session;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator = new Navigator(this, this);

        navigator.addProvider(cdiViewProvider);
        navigator.addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                boolean goingToLoginView = LoginView.VIEW_NAME.equals(event.getViewName());
                boolean alreadyLoggedIn = session.isLoggedIn();

                if (goingToLoginView && alreadyLoggedIn) {
                    session.logOut();
                    return true;
                } else if (goingToLoginView || alreadyLoggedIn) {
                    return true;
                } else {
                    navigator.navigateTo(LoginView.VIEW_NAME);
                    return false;
                }
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {
            }
        });

        navigator.navigateTo(LoginView.VIEW_NAME);
    }

}
