package fr.mazerty.shika.torii.vaadin;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener;
import fr.mazerty.shika.ishi.vaadin.MyView;

import javax.inject.Inject;

@CDIView(MainView.MAIN_VIEW_NAME)
public class MainView extends MyView {

    static final String MAIN_VIEW_NAME = "main";

    @Inject
    private MainWindow mainWindow;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        show(mainWindow);
    }

}
