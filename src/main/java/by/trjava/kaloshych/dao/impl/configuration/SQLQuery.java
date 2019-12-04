package by.trjava.kaloshych.dao.impl.configuration;

public class SQLQuery {

    //User
  public   static final String QUERY_CHECK_USER = "SELECT u.email, u.id_user, u.name, u.login, u.password, s.status" +
            " FROM users u " +
            " INNER JOIN user_statuses s ON u.id_status=s.id_user_status" +
            " WHERE login=? AND password=?";
    public   static final String QUERY_USER_EXISTS_CHECK = "SELECT id_user FROM users WHERE login=?";
    public   static final String QUERY_REGISTER_USER = "INSERT INTO users(login, password, email, name) VALUES(?, ?, ?, ?)";
    public   static final String QUERY_DELETE_LOGIN = "DELETE FROM users where login=?";
    public  static final String QUERY_UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE id_user=?";
    public  static final String QUERY_USER_ALL =  "SELECT u.email, u.id_user, u.name, u.login, u.password, s.status" +
            " FROM users u INNER JOIN user_statuses s ON u.id_status=s.id_user_status";
    public  static final String QUERY_USER_GET_ID = "SELECT id_user FROM users WHERE login=?";
    public    static final String QUERY_CHECK_USER_ID =  "SELECT u.email, u.id_user, u.name, u.login," +
            " u.password, s.status FROM users u " +
            " INNER JOIN user_statuses s ON u.id_status=s.id_user_status" +
            " WHERE u.id_user=?";
    public   static final String QUERY_GET_PASSWORD =  "SELECT password FROM users  WHERE id_user=?";
    public   static final String QUERY_USER_BY_LOGIN =  "SELECT u.email, u.id_user, u.name, u.login, u.password, s.status" +
            " FROM users u " +
            " INNER JOIN user_statuses s ON u.id_status=s.id_user_status" +
            " WHERE  login=?";

    //Account
    public     static final String QUERY_ACCOUNT_ADD= "INSERT INTO accounts ( id_account_user, id_payment_method, payment_date, amount_Of_Money) " +
            "VALUES(?, ?, ?, ?)";
    public   static final String QUERY_GET_BALANCE = "SELECT a.amount_of_money FROM accounts a" +
            " INNER JOIN account_users au" +
            " ON a.id_account_user=au.id_account_user WHERE id_user=?";
    public   static final String QUERY_ADD_ACCOUNT_USER = "INSERT INTO account_users( id_user) VALUES(?)";


    //AccountUser
    public  static final String QUERY_GET_ACCOUNT_USER="SELECT * FROM account_users WHERE id_user=?";

    //Payment Method
    public    static final String   QUERY_GET_PAYMENT_METHOD="SELECT name_payment_method FROM payment_methods WHERE id_payment_method=?";
    public   static final String   QUERY_ALL_PAYMENT_METHOD="SELECT id_payment_method, name_payment_method FROM payment_methods";

    //Drink
    public  static final String QUERY_DRINK = "SELECT * FROM drinks WHERE id_Drink=?";
    public   static final String QUERY_DRINK_PRICE = "SELECT Price FROM drinks WHERE Drink=?";
    public   static final String QUERY_ALL_DRINKS = "SELECT * FROM drinks";
    public   static final String QUERY_DRINK_GET_PRICE = "SELECT price FROM drinks WHERE id_Drink=?";
    public  static final String QUERY_DRINK_GET_PORTION = "SELECT portion FROM drinks WHERE id_Drink=?";
    public    static final String QUERY_DRINK_DECREASE_PORTION="UPDATE drinks SET portion=? WHERE id_drink=?";
    public   static final String QUERY_DRINK_GET_BY_ID="SELECT * FROM drinks WHERE id_drink=?";
    public   static final String QUERY_DRINK_ADD_NEW="INSERT INTO drinks(drink, price, description) values(?, ?, ?)";
    public   static final String QUERY_DRINK_DELETE="DELETE FROM drinks WHERE id_drink=?" ;
    public  static final String QUERY_DRINK_CHANGE_PRICE="UPDATE drinks SET price=? WHERE drink=?";
    public  static final String QUERY_DRINK_ADD_FILLING = "INSERT INTO filling_operations (id_drink) value(?)";


    //Additional ingredient
    public   static final String QUERY_ADDITIONAL_INGREDIENT = "SELECT * FROM additional_Ingredients";
    public   static final String QUERY_ADDITIONAL_INGREDIENT_BY_ID = "SELECT * FROM additional_Ingredients WHERE id_additional_Ingredient=?";
    public   static final String QUERY_ADDITIONAL_INGREDIENT_ADD = "INSERT INTO additional_Ingredients (additional_Ingredient, calories) values(?, ?)";
    public   static final String QUERY_ADDITIONAL_INGREDIENT_DELETE = "DELETE FROM additional_Ingredients WHERE id_additional_Ingredient=?";
    public   static final String QUERY_ADDITIONAL_INGREDIENT_DECREASE_PORTION="UPDATE additional_Ingredients SET portion=? WHERE id_additional_Ingredient=?";
    public   static final String QUERY_ADDITIONAL_INGREDIENT_ADD_FILLING = "INSERT INTO filling_Operations (id_additional_ingredient) value(?)";


    //FillingOperation
    public   static final String QUERY_FILLING_OPERATION = "SELECT * FROM filling_operations";
    public   static final String QUERY_FILLING_OPERATION_ADDITIONAL_INGREDIENT = "SELECT * FROM filling_Operations" +
            " WHERE id_additional_Ingredient=?";
    public  static final String QUERY_FILLING_OPERATION_DRINK = "SELECT MAXPortion FROM filling_operations" +
            " WHERE id_drink=?";
    public   static final String QUERY_FILLING_DRINK = "UPDATE drinks SET portion=? WHERE id_drink=?";
    public   static final String QUERY_FILLING_ADDITIONAL = "UPDATE additional_ingredients SET portion=?" +
            " WHERE id_additional_ingredient=?";
    public   static final String QUERY_FILLING_GET_ADDITIONAL_PORTION = "SELECT * FROM additional_Ingredients" +
            " WHERE id_additional_Ingredient=?";
    public   static final String QUERY_DELETE_DRINK_FROM_FILLING_OPERATION="DELETE FROM filling_operations WHERE id_drink=?";
    public   static final String QUERY_DELETE_ADDITIONAL_INGREDIENT_FROM_FILLING_OPERATION="DELETE FROM filling_operations" +
            " WHERE id_additional_ingredient=?";


    //Order
    public   static final String QUERY_ORDER_CREATE = "INSERT INTO orders (id_cart_user, total_cost, date_order) VALUES(?, ?, ?)";
    public   static final String QUERY_ORDER_MAKE="SELECT * FROM Carts WHERE id_order=?";
    public   static final String QUERY_GET_DATE_ORDER="SELECT date_Order FROM orders WHERE id_Cart=?";
    public   static final String QUERY_GET_TOTAL_COST="SELECT total_Cost FROM orders WHERE id_Cart=?";
    public   static final String QUERY_GET_ORDERS_BY_USER="SELECT orders.id_order, orders.id_cart_user," +
            " orders.date_order, orders.total_cost FROM orders INNER JOIN cart_users ON " +
            " orders.id_cart_user=cart_users.id_cart_user" +
            " WHERE cart_users.id_user=?";

    public   static final String QUERY_ID_ORDER = "SELECT * FROM orders";
    public   static final String QUERY_DELETE_ORDER = "DELETE FROM orders where id_order = ?";
    public   static final String QUERY_CHECK_ID_ORDER = "SELECT * FROM orders WHERE id_order=?";
    public   static final String QUERY_ALL_ORDERS = "SELECT * FROM orders WHEN id_user=?";

    //CartUser
    public   static final String QUERY_ORDER_LIST = "SELECT * FROM Carts INNER JOIN orders " +
            "ON Carts.id_order=orders.id_order WHERE id_user=?";
    public  static final String QUERY_GET_USER_BY_ORDER = "SELECT cart_users.id_user FROM orders " +
            "INNER JOIN cart_users ON orders.id_cart_user=cart_users.id_cart_user WHERE id_order=?";

    public   static final String  QUERY_CART_USER_CREATE="INSERT INTO cart_users (id_user) VALUES(?)";
    public   static final String QUERY_CART_USER_DELETE = "DELETE FROM cart_users WHERE id_user = ?";
    public   static final String  QUERY_CART_USER_BY_ID_USER="SELECT id_cart_user FROM cart_users WHERE id_user=?";
    public   static final String  QUERY_CART_USER_BY_ID="SELECT * FROM Cart_Users WHERE id_cart_user=?";

    //Cart

    public   static final String  QUERY_CART_ADD="INSERT INTO carts " +
            "(id_Cart_User, id_drink, portion) VALUES (?, ?, ?)";


    public   static final String QUERY_GET_ALL_INGREDIENTS="SELECT * FROM Cart_additional_ingredients WHERE id_Cart=?";


    public   static final String QUERY_CART_DELETE="DELETE FROM Carts WHERE id_Cart=?";

    public   static final String  QUERY_CART_CHANGE_PORTION="UPDATE carts SET portion=? WHERE id_Cart=?";

    public   static final String QUERY_ALL_INGREDIENTS="SELECT * FROM Cart_additional_ingredients";
    public   static final String QUERY_ALL_CARTS_BY_CART_USER="SELECT * FROM carts WHERE id_cart_user=?";
    public   static final String QUERY_ALL_CARTS_BY_USER="SELECT carts.id_cart, carts.id_cart_user, carts.id_drink, " +
            "carts.portion FROM carts INNER JOIN cart_users ON carts.id_cart_user=cart_users.id_cart_user" +
            " WHERE id_user=?";
    public   static final String QUERY_GET_DRINK_BY_CART="SELECT id_drink FROM Carts WHERE id_Cart=?";
    public   static final String QUERY_GET_PORTION_BY_CART="SELECT portion FROM carts WHERE id_cart=?";
    public   static final String QUERY_GET_CART_BY_ID="SELECT * FROM carts WHERE id_cart=?";

    //CartAdditionalIngredient
    public   static final String  QUERY_CART_ADDITIONAL_INGREDIENT_ADD="INSERT INTO cart_additional_ingredients " +
            "(id_Cart, id_additional_Ingredient) VALUES (?, ?)";
    public   static final String QUERY_CART_ADDITIONAL_INGREDIENT_DELETE="DELETE FROM cart_additional_ingredients" +
            " WHERE id_cart_additional_ingredient=?";

    public   static final String  QUERY_INGREDIENTS_BY_USER="SELECT a.id_cart_additional_ingredient," +
            " a.id_cart, a.id_additional_ingredient FROM cart_additional_ingredients a INNER JOIN carts c" +
            " ON c.id_cart=a.id_cart" +
            " INNER JOIN cart_users u ON u.id_cart_user=c.id_cart_user" +
            " WHERE id_user=?";



}
