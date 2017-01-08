package fr.mazerty.shika.torii.vaadin;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import fr.mazerty.shika.torii.session.Session;

import javax.inject.Inject;

@CDIUI("")
@Title("Torii")
@Theme("mytheme")
@PreserveOnRefresh
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
                getWindows().forEach(Window::close);

                boolean goingToLoginView = LoginView.VIEW_NAME.equals(event.getViewName());
                boolean alreadyLoggedIn = session.isLoggedIn();

                if (goingToLoginView && alreadyLoggedIn) {
                    session.logOut();
                    return true; // on accepte la transition vers la loginview
                } else if (goingToLoginView || alreadyLoggedIn) {
                    return true; // on accepte la transition vers la view demandée
                } else {
                    navigator.navigateTo(LoginView.VIEW_NAME);
                    return false; // on refuse la transition et on redirige vers la loginview
                }
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {
            }
        });

        navigator.navigateTo(LoginView.VIEW_NAME);
    }

}
