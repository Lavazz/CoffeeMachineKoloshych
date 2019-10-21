package by.trjava.coffeemachine.service.validation;

import by.trjava.coffeemachine.service.ServiceFactory;
import by.trjava.coffeemachine.service.UserService;

public class UserValidator {
    private static final String LOGIN_FORMAT = "^[a-zA-Z][a-zA-Z0-9-_.]{3,12}$";

    private static final String PASSWORD_FORMAT = "^[a-zA-Z][a-zA-Z0-9-_.]{6,12}$";

    private static final String NAME_FORMAT = "[a-zA-Z]{2,12}";
    private static final String EMAIL_FORMAT =
            "[a-z][[a-z][0-9][-][_]]{3,12}[@][a-z]{2,10}[.][a-z]{2,4}";

    private static final UserValidator instance=new UserValidator();
    private UserValidator(){}

    public boolean validate(String login, String password) {
       return  validateLogin(login)&&validatePassword(password);
    }

    public boolean validate(String login, String password, String email, String name){
        return validateLogin(login)&&validatePassword(password)
        &&validateEmail(email)&&validateName(name);
    }


    public  boolean validatePassword(String password){
        return password.matches(PASSWORD_FORMAT);
    }
    public  boolean validateLogin(String login) {
        return login.matches(LOGIN_FORMAT);
    }
    public  boolean validateEmail(String email){
        return email.matches(EMAIL_FORMAT);
    }
    public  boolean validateName(String name){
        return name.matches(NAME_FORMAT);
    }

    public boolean validateIdUser(int idUser){
        UserService userService=ServiceFactory.getInstance().getUserService();
       return userService.checkId(idUser);
    }

    public static UserValidator getInstance(){
        return instance;
    }
}
