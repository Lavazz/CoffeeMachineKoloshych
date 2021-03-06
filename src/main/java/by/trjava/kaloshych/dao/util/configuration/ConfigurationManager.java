package by.trjava.kaloshych.dao.util.configuration;

public class ConfigurationManager {

    //User

    public static final String PARAMETER_ID_USER = "id_user";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_STATUS = "status";

    //Account
    public static final String PARAMETER_ID_ACCOUNT = "id_account";

    //AccountUser
    public static final String PARAMETER_ID_ACCOUNT_USER = "id_account_user";

    //AdditionalIngredient
    public static final String PARAMETER_ID_ADDITIONAL_INGREDIENT = "id_additional_Ingredient";
    public static final String PARAMETER_ADDITIONAL_INGREDIENT = "additional_Ingredient";
    public static final String PARAMETER_PORTION = "portion";
    public static final String PARAMETER_CALORIES = "calories";

    //CartAdditionalIngredient
    public static final String PARAMETER_ID_CART_ADDITIONAL_INGREDIENT = "id_cart_additional_ingredient";

    //Cart
    public static final String PARAMETER_ID_CART = "id_cart";

    // CartUser
    public static final String PARAMETER_ID_CART_USER = "id_cart_user";

    //Drink
    public static final String PARAMETER_ID_DRINK = "id_drink";
    public static final String PARAMETER_PRICE = "price";
    public static final String PARAMETER_DRINK = "drink";
    public static final String PARAMETER_PICTURE_PATH = "picture_path";
    public static final String PARAMETER_DESCRIPTION = "description";

    //FillingOperation
    public static final String PARAMETER_MAX_PORTION = "MAXPortion";
    public static final int PARAMETER_DEFALT_MAX_PORTION = 50;


    //PaymentMethod
    public static final String PARAMETER_ID_PAYMENT_METHOD = "id_payment_method";
    public static final String PARAMETER_MONEY = "amount_of_money";
    public static final String PARAMETER_PAYMENT_DATE = "payment_date";
    public static final String PARAMETER_NAME_PAYMENT_METHOD = "name_payment_method";
    public static final int PAYMENT_METHOD_REMOVAL = 2;
    public static final int PAYMENT_METHOD_REGISTER = 1;
    public static final double REGISTRATION_BONUS = 5.00;

    //Order
    public static final String PARAMETER_ID_ORDER = "id_order";
    public static final String PARAMETER_DATE_ORDER = "date_order";
    public static final String PARAMETER_TOTAL_COST = "total_cost";

    //Common
    public static final int PARAMETER_COLUMN_INDEX = 1;
    public static final int NOT_PARAMETERS = 0;


}