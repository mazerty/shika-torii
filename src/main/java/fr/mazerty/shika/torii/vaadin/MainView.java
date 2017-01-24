package fr.mazerty.shika.torii.vaadin;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import fr.mazerty.shika.ishi.vaadin.MyView;

import javax.annotation.PostConstruct;

@CDIView(MainView.VIEW_NAME)
public class MainView extends MyView {

    static final String VIEW_NAME = "main";

    @PostConstruct
    public void postConstruct() {
        Button admin = new Button("admin");
        admin.addClickListener(event -> navigateTo(AdminView.VIEW_NAME));
        addComponent(admin);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
