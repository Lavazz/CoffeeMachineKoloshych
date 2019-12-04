package by.trjava.kaloshych.service.impl;

import by.trjava.kaloshych.dao.CartDAO;
import by.trjava.kaloshych.dao.DAOFactory;
import by.trjava.kaloshych.dao.DrinkDAO;
import by.trjava.kaloshych.dao.exception.DAOException;
import by.trjava.kaloshych.entity.Cart;
import by.trjava.kaloshych.entity.CartUser;
import by.trjava.kaloshych.entity.Drink;
import by.trjava.kaloshych.entity.User;
import by.trjava.kaloshych.service.CartService;
import by.trjava.kaloshych.service.exception.*;
import by.trjava.kaloshych.service.validation.CartValidator;
import by.trjava.kaloshych.service.validation.InputDataValidator;

import java.util.List;

public class CartServiceImpl implements CartService {

    private static final int PORTION_MODIFICATION=1;
  private  final   CartDAO cartDAO = DAOFactory.getInstance().getCartDAO();
  private  final DrinkDAO drinkDAO = DAOFactory.getInstance().getDrinkDAO();
    private  final InputDataValidator dataValidator=InputDataValidator.getInstance();

    @Override
    public Cart addDrinkToCart(CartUser cartUser, String idDrink, String portion) throws ServiceException {
        int currentPortion;
        Cart cart;
        Drink drink;

        if (dataValidator.isEmpty(idDrink)||dataValidator.isEmpty(portion)) {
            throw new EmptyDataException("Empty data");
        }
        if(!CartValidator.getInstance().validate(portion)){
            throw new WrongPortionException("Wrong number of portions");
        }

        try {
             drink=drinkDAO.createDrink(Integer.parseInt(idDrink));
            currentPortion= drinkDAO.getPortion(drink);
        } catch (DAOException e) {
            throw  new ServiceException(e);
        }
            if (!CartValidator.getInstance().isSufficientPortion(currentPortion, Integer.parseInt(portion))) {
                throw new InsufficientPortionException("In coffee machine insufficient portion of this drink");
            }

            try{
            drinkDAO.decreasePortion(drink, Integer.parseInt(portion));
            cart=cartDAO.addDrinkToCart(cartUser, drink, Integer.parseInt(portion));
        } catch (DAOException e) {
            throw  new ServiceException(e);
        }
        return cart;
    }

    @Override
    public void deleteDrinkFromCart(String idCart) throws ServiceException {
        if (dataValidator.isEmpty(idCart)) {
            throw new EmptyDataException("Empty data");
        }
        try {
            Cart cart= cartDAO.createCartById(Integer.parseInt(idCart));
            cartDAO.deleteDrinkFromCart(cart);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public boolean changePortion(String idCart, String sign) throws ServiceException {
        try {
            Cart cart= cartDAO.createCartById(Integer.parseInt(idCart));
            int portion=cart.getPortion();
            if(sign.equals("plus")){
                portion+=PORTION_MODIFICATION;
            }else if(sign.equals("minus")){
                portion-=PORTION_MODIFICATION;
            }
if(!CartValidator.getInstance().validate(portion)){
    throw new WrongPortionException("Wrong number of portions");
}

         int   currentPortion= drinkDAO.getPortion(cart.getDrink());
if(!CartValidator.getInstance().isSufficientPortion(currentPortion, portion)){
                throw new InsufficientPortionException("In coffee machine insufficient portion of this drink");
            }
            return cartDAO.changePortion(cart, portion);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public List<Cart> getAllCarts(CartUser cartUser) throws ServiceException {
        try {
            return cartDAO.getAllCarts(cartUser);
        } catch (DAOException e) {
            throw  new ServiceException(e);
        }
    }

    @Override
    public Cart createCartById(int idCart) throws ServiceException {
        try {
            return cartDAO.createCartById(idCart);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getPortionByCart(Cart cart) throws ServiceException {
        try {
            return cartDAO.getPortionByCart(cart);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


@Override
  public double getTotalCost(CartUser cartUser) throws ServiceException {
        double totalCost=0;
        List<Cart> carts=getAllCarts(cartUser);
        for (Cart cart:carts){
            totalCost+=cart.getDrink().getPrice()*cart.getPortion();
        }
        return totalCost;
  }

    @Override
    public List<Cart> getAllCartsByUser(User user) throws ServiceException {
        try {
            return cartDAO.getAllCartsByUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
