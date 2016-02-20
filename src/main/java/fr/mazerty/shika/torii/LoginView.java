package fr.mazerty.shika.torii;

import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import fr.mazerty.shika.ishi.MyBeanFieldGroup;
import fr.mazerty.shika.ishi.MyPasswordField;
import fr.mazerty.shika.ishi.MyTextField;
import fr.mazerty.shika.ishi.MyView;
import javaslang.control.Try;

import javax.inject.Inject;

import static com.vaadin.event.ShortcutAction.KeyCode.ENTER;
import static com.vaadin.ui.Alignment.MIDDLE_CENTER;
import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;
import static com.vaadin.ui.themes.ValoTheme.BUTTON_PRIMARY;

@CDIView(LoginView.VIEW_NAME)
public class LoginView extends MyView {

    public static final String VIEW_NAME = "login";

    private static final String FLD_EMAIL_CAPTION = "Email";
    private static final String FLD_PASSWORD_CAPTION = "Password";
    private static final String BTN_LOGIN_CAPTION = "Log in";

    private final Field fldEmail;
    private final Field fldPassword;
    private final Button btnLogin;

    @Inject
    private Session session;

    public LoginView() {
        MyBeanFieldGroup<User> bfg = new MyBeanFieldGroup<>(User.class);

        fldEmail = bfg.buildAndBind(FLD_EMAIL_CAPTION, "email", MyTextField.class);
        fldPassword = bfg.buildAndBind(FLD_PASSWORD_CAPTION, "password", MyPasswordField.class);

        btnLogin = new Button(BTN_LOGIN_CAPTION);
        btnLogin.setClickShortcut(ENTER);
        btnLogin.addStyleName(BUTTON_PRIMARY);
        btnLogin.addClickListener(event -> Try
                .of(bfg::getBean)
                .andThenTry(session::login)
                .onFailure(e -> Notification.show(e.getMessage(), ERROR_MESSAGE))
                .andThen(() -> navigateTo(MainView.VIEW_NAME)));

        FormLayout formLayout = new FormLayout(fldEmail, fldPassword, btnLogin);
        formLayout.setMargin(true);

        Panel panel = new Panel(formLayout);
        panel.setSizeUndefined();

        setSizeFull();
        addComponent(panel);
        setComponentAlignment(panel, MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        fldEmail.focus();
    }

}
