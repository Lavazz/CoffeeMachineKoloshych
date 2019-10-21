package by.trjava.coffeemachine.dao;

import by.trjava.coffeemachine.dao.impl.*;

public class DAOFactory {

private static final DAOFactory instance=new DAOFactory();
private final AccountDAO accountDAO=new SQLAccountDAO();
private final AdditionalIngredientDAO additionalDAO=new SQLAdditionalIngredientDAO();
private final DrinkDAO drinkDAO=new SQLDrinkDAO();
private final FillingOperationDAO fillingDAO=new SQLFillingOperationDAO();
private final OrderDAO orderDAO=new SQLOrderDAO();
private final UserDAO userDAO=new SQLUserDAO();
private final OrderJournalDAO orderJournalDAO=new SQLOrderJournalDAO();

    private DAOFactory(){};

// private static volatile DAOFactory instance;
//   public static DAOFactory getInstance() {
//       if (instance == null) {
//           instance = new DAOFactory();
//       }
//       return instance;
//   }


    public static DAOFactory getInstance(){
        return instance;
    }
public AccountDAO getAccountDAO(){
        return accountDAO;
}

public AdditionalIngredientDAO getAdditionalIngredientDAO(){
        return additionalDAO;
}

public DrinkDAO getDrinkDAO(){
        return drinkDAO;
}

public FillingOperationDAO getFillingOperationDAO(){
        return fillingDAO;
}

public OrderDAO getOrderDAO(){
        return orderDAO;
}

public UserDAO getUserDAO(){
        return userDAO;
}
public OrderJournalDAO getOrderJournalDAO(){return orderJournalDAO;}
}