package by.trjava.kaloshych.service.validation;

public class FillingOperationValidator {

    private static final FillingOperationValidator instance=new FillingOperationValidator();
    private FillingOperationValidator(){}

    public static FillingOperationValidator getInstance(){
        return instance;
    }
}
