package fr.mazerty.shika.torii.vaadin;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener;
import fr.mazerty.shika.ishi.vaadin.MyUI;
import fr.mazerty.shika.ishi.vaadin.MyView;

import javax.inject.Inject;

@CDIView(MyUI.MAIN_VIEW_NAME)
public class MainView extends MyView {

    @Inject
    private MainWindow mainWindow;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        show(mainWindow);
    }

}
