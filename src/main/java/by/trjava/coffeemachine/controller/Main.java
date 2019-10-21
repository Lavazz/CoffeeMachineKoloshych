package by.trjava.coffeemachine.controller;


import by.trjava.coffeemachine.dao.exception.DAOException;
import by.trjava.coffeemachine.dao.exception.WrongAuthorizationDataException;
import by.trjava.coffeemachine.entity.Drink;
import by.trjava.coffeemachine.service.DrinkService;
import by.trjava.coffeemachine.service.ServiceFactory;
import by.trjava.coffeemachine.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main (String [] args) throws SQLException, DAOException, WrongAuthorizationDataException, ServiceException {
        ServiceFactory s=ServiceFactory.getInstance();
        DrinkService u=s.getDrinkService();
      List<Drink> d= u.listAllDrink();

        System.out.println();
//        DAOFactory daoFactory=DAOFactory.getInstance();
//        UserDAO userDAO=daoFactory.getUserDAO();
//        AccountDAO accountDAO=daoFactory.getAccountDAO();
//        User user = userDAO.authorization("Буратино", "333");
//        System.out.println(user.getName());

//       List<User> l=userDAO.listAllUsers();
//       for(User u:l)
//           System.out.println(u.getName());

//    boolean b=  userDAO.registration( "2555kot", "2Матроскин",
//            "2kot@mail.ru"  , "2Максим");
//

//        boolean b=userDAO.updateUserPassword("Артемон", "888");
// System.out.println(b);
//Date date=new Date("2019-10-12");
//        System.out.println(accountDAO.createAccount(new Account(9, 9, "Пополнение в банке",
//                date, 12)));

    }
}
