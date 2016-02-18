package fr.mazerty.shika.torii;

import com.vaadin.cdi.CDIView;
import com.vaadin.data.fieldgroup.FieldGroup;
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

    private final Field fldEmail;
    private final Field fldPassword;
    private final Button btnLogin;

    public LoginView() {
        MyBeanFieldGroup<User> mbfg = new MyBeanFieldGroup<>(User.class);

        fldEmail = mbfg.buildAndBind(FLD_EMAIL_CAPTION, "email", MyTextField.class);
        fldPassword = mbfg.buildAndBind(FLD_PASSWORD_CAPTION, "password", MyPasswordField.class);

        btnLogin = new Button(BTN_LOGIN_CAPTION);
        btnLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        btnLogin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnLogin.addClickListener(event -> {
            try {
                mbfg.commit();
                User bean = mbfg.getItemDataSource().getBean();
            } catch (FieldGroup.CommitException e) {
                e.printStackTrace();
            }
        });

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
