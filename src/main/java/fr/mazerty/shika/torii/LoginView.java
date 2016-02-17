package fr.mazerty.shika.torii;

import com.vaadin.cdi.CDIView;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@CDIView(LoginView.VIEW_NAME)
public class LoginView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "login";

    private static final String FLD_EMAIL_CAPTION = "Email";
    private static final String FLD_PASSWORD_CAPTION = "Password";
    private static final String BTN_LOGIN_CAPTION = "Log in";

    private final TextField fldEmail;
    private final PasswordField fldPassword;
    private final Button btnLogin;

    public LoginView() {
        fldEmail = new TextField(FLD_EMAIL_CAPTION);
        fldPassword = new PasswordField(FLD_PASSWORD_CAPTION);

        btnLogin = new Button(BTN_LOGIN_CAPTION);
        btnLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        btnLogin.addStyleName(ValoTheme.BUTTON_PRIMARY);

        FormLayout formLayout = new FormLayout(fldEmail, fldPassword, btnLogin);
        formLayout.setMargin(true);

        Panel panel = new Panel(formLayout);
        panel.setSizeUndefined();

        setSizeFull();
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        fldEmail.focus();
    }

}
