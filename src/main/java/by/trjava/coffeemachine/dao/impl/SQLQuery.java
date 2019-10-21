package by.trjava.coffeemachine.dao.impl;

public class SQLQuery {

    //User
    static final String QUERY_CHECK_USER = "SELECT * FROM users WHERE login=? and password=?";
    static final String QUERY_CHECK_LOGIN = "SELECT * FROM users WHERE login=?";
    static final String QUERY_USER_EXISTS_CHECK = "SELECT id_user FROM users WHERE login=?";
    static final String QUERY_REGISTER_USER = "INSERT INTO users(email, login, password, name) VALUES(?, ?, ?, ?)";
    static final String QUERY_DELETE_LOGIN="DELETE FROM users where login = ?";
    static final String QUERY_UPDATE_PASSWORD="UPDATE users SET password = ? WHERE login = ?";
    static final String QUERY_USER_ALL = "SELECT * FROM users";
    static final String QUERY_USER_GET_ID="SELECT id_user FROM users WHERE login=?";
    static final String QUERY_CHECK_USER_ID="SELECT * FROM users WHERE id_user=?";
    public static final String QUERY_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

    //Account
    static final String QUERY_CREATE_ACCOUNT = "INSERT INTO accounts( id_user, PaymentMethod, PaymentDate, " +
            "AmountOfMoney) VALUES( ?, ?, ?, ?)";
    static final String QUERY_ACCOUNT_RECHARGE = "INSERT INTO accounts (id_user, PaymentMethod, PaymentDate, AmountOfMoney) " +
           "VALUES(?, ?,? ?, ?";
    static final String QUERY_ACCOUNT_CHECK_BALANCE = "SELECT AmountOfMoney FROM accounts WHERE id_user=?";
    static final String  QUERY_ACCOUNT_DECREASE="UPDATE accounts SET  AmountOfMoney=? WHERE id_user=?";
   static final String QUERY_GET_BALANCE="SELECT amountOfMoney FROM accounts";


    //Drink
    static final String QUERY_DRINK = "SELECT Price FROM drinks WHERE Drink=?";
    static final String QUERY_ALL_DRINKS = "SELECT * FROM drinks";
    static final String QUERY_DRINK_GET_PRICE="SELECT price FROM drinks WHERE id_Drink=?";

    //Additional ingredient
    static final String QUERY_ADDITIONAL_INGREDIENT = "SELECT * FROM additionalIngredients";
    static final String QUERY_GET_ADDITIONAL_INGREDIENT="SELECT price from additionalIngredients WHERE id_additionalIngredient=?";

    //FillingOperation
    static final String QUERY_FILLING_OPERATION = "UPDATE fillingOperations SET maxFillingPortion WHERE BasicIngredient=?"+
            "UPDATE fillingOperations SET maxFillingPortion WHERE AdditionalIngredient=?";

    //Order
    static final String QUERY_ORDER_CREATE = "INSERT INTO orders (id_user) VALUES(?)";

//static final String QUERY_ORDER_LAST_ID="SELECT  LAST_INSERT_ID()";
    static final String QUERY_ID_ORDER= "SELECT * FROM orders";
    static final String QUERY_DELETE_ORDER="DELETE FROM orders where id_order = ?";
    static final String QUERY_CHECK_ID_ORDER= "SELECT * FROM orders WHERE id_order=?";
    static final String QUERY_ALL_ORDERS= "SELECT * FROM orders WHEN id_user=?";

    //orderJournal
    static final String QUERY_ORDER_JOURNAL_LIST="SELECT * FROM orderJournals INNER JOIN orders " +
            "ON orderJournals.id_order=orders.id_order WHERE id_user=?";
    static final String  QUERY_ORDER_JOURNAL_GET_USER="SELECT id_user FROM orders WHERE id_order=?";
    static final String QUERY_CREATE_ORDER_BASKET= "INSERT INTO OrderJournals (id_order, id_drink, id_additionalIngredient, portion)" +
            " VALUES(?, ?, ? , ?)";
}
