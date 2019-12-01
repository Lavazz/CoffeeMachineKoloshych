package by.trjava.kaloshych.service.validation;

import java.util.Collection;
import java.util.Optional;

public class InputDataValidator {
    private static final InputDataValidator instance=new InputDataValidator();
    private InputDataValidator(){}

    public static InputDataValidator getInstance(){
        return instance;
    }

    public boolean isEmpty(Object object) {
        if (!Optional.ofNullable(object).isPresent()) {
            return true;
        }
        if (object instanceof String && ((String) object).length() == 0) {
            return true;
        }
        if (object instanceof Collection && ((Collection) object).isEmpty()) {
            return true;
        }
        return false;
    }


}