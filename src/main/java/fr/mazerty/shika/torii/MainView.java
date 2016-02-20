package fr.mazerty.shika.torii;

import com.vaadin.cdi.CDIView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

import static com.vaadin.ui.Alignment.MIDDLE_CENTER;

@CDIView(MainView.VIEW_NAME)
public class MainView extends MyView {

    public static final String VIEW_NAME = "main";

    public MainView() {
        Panel panel = new Panel(new Label("ok"));
        panel.setSizeUndefined();

        setSizeFull();
        addComponent(panel);
        setComponentAlignment(panel, MIDDLE_CENTER);
    }

}
