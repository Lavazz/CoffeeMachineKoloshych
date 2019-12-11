package by.trjava.kaloshych.dao.util;

import by.trjava.kaloshych.builder.impl.*;
import by.trjava.kaloshych.entity.*;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CreatorTest {

    private final String INVALID_STRING = "invalid";
    private final int INVALID_INT = 0;
    private final String LOGIN = "login";
    private final String PASSWORD = "password";
    private final String EMAIL = "email@email.by";
    private final String NAME = "name";
    private final UserStatus userStatus = UserStatus.ADMIN;
    private final int ID = 1;

    private final String PICTURE_PATH = "path";
    private final int CALORIES = 666;
    private final String DESCRIPTION = "description";
    private final int PORTION=10;
private final double MONEY=100;

    private User mockUser = mock(User.class);
    private Drink mockDrink = mock(Drink.class);
    private CartUser mockCartUser = mock(CartUser.class);
    private AdditionalIngredient mockAdditionalIngredient = mock( AdditionalIngredient.class);
private Date mockDate=mock(Date.class);
private PaymentMethod mockPaymentMethod=mock(PaymentMethod.class);
private AccountUser mockAccountUser=mock(AccountUser.class);



    @Test
    public void createUserValid() {
        User user = new User(ID, LOGIN, PASSWORD, EMAIL, NAME, userStatus);

        UserBuilderImpl builder = new UserBuilderImpl(ID);
        User userBuilder = builder.withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withEmail(EMAIL)
                .withName(NAME)
                .withUserStatus(userStatus)
                .build();
        assertEquals(user, userBuilder);
    }

    @Test
    public void createUserInvalid() {
        User user = new User(ID, INVALID_STRING, PASSWORD, EMAIL, NAME, userStatus);

        UserBuilderImpl builder = new UserBuilderImpl(ID);
        User userBuilder = builder.withLogin(LOGIN)
                .withPassword(PASSWORD)
                .withEmail(EMAIL)
                .withName(NAME)
                .withUserStatus(userStatus)
                .build();
        assertNotEquals(user, userBuilder);
    }

    @Test
    public void createAccountUserValid() {
        AccountUser accountUser = new AccountUser(ID, mockUser);

        AccountUserBuilderImpl builder = new AccountUserBuilderImpl(ID);
        AccountUser accountUserBuilder = builder.withUser(mockUser)
                .build();
        assertEquals(accountUser.getIdAccountUser(), accountUserBuilder.getIdAccountUser());
    }

    @Test
    public void createAccountUserInvalid() {
        AccountUser accountUser = new AccountUser(INVALID_INT, mockUser);

        AccountUserBuilderImpl builder = new AccountUserBuilderImpl(ID);
        AccountUser accountUserBuilder = builder.withUser(mockUser)
                .build();
        assertNotEquals(accountUser.getIdAccountUser(), accountUserBuilder.getIdAccountUser());
    }


    @Test
    public void createAdditionalIngredientValid() {
        AdditionalIngredient additionalIngredient=new AdditionalIngredient(ID, NAME, PORTION, PICTURE_PATH, CALORIES );

        AdditionalIngredientBuilderImpl builder=new AdditionalIngredientBuilderImpl(ID);
        AdditionalIngredient additionalIngredientBuilder=builder.withNameComponent(NAME)
                .withPortion(PORTION)
                .withPicturePath(PICTURE_PATH)
                .withCalories(CALORIES)
                .build();
        assertEquals(additionalIngredient, additionalIngredientBuilder);
    }

    @Test
    public void createAdditionalIngredientInvalid() {
        AdditionalIngredient additionalIngredient=new AdditionalIngredient(ID, INVALID_STRING, PORTION, PICTURE_PATH, CALORIES );

        AdditionalIngredientBuilderImpl builder=new AdditionalIngredientBuilderImpl(ID);
        AdditionalIngredient additionalIngredientBuilder=builder.withNameComponent(NAME)
                .withPortion(PORTION)
                .withPicturePath(PICTURE_PATH)
                .withCalories(CALORIES)
                .build();
        assertNotEquals(additionalIngredient, additionalIngredientBuilder);
    }



    @Test
    public void createCartValid() {
        Cart cart=new Cart(ID, mockCartUser, mockDrink, PORTION);

        CartBuilderImpl builder=new CartBuilderImpl(ID);
        Cart cartBuilder=builder.withCartUser(mockCartUser)
                .withDrink(mockDrink)
                .withPortion(PORTION)
                .build();

        assertEquals(cart, cartBuilder);
    }

    @Test
    public void createCartInvalid() {
        Cart cart=new Cart(INVALID_INT, mockCartUser, mockDrink, PORTION);

        CartBuilderImpl builder=new CartBuilderImpl(ID);
        Cart cartBuilder=builder.withCartUser(mockCartUser)
                .withDrink(mockDrink)
                .withPortion(PORTION)
                .build();

        assertNotEquals(cart, cartBuilder);
    }



    @Test
    public void createCartUserValid() {
        CartUser cartUser=new CartUser(ID, mockUser);

        CartUserBuilderImpl builder=new CartUserBuilderImpl(ID);
        CartUser cartUserBuilder=builder.withUser(mockUser)
                .build();

        assertEquals(cartUser, cartUserBuilder);
    }


    @Test
    public void createCartUserInvalid() {
        CartUser cartUser=new CartUser(INVALID_INT, mockUser);

        CartUserBuilderImpl builder=new CartUserBuilderImpl(ID);
        CartUser cartUserBuilder=builder.withUser(mockUser)
                .build();

        assertNotEquals(cartUser, cartUserBuilder);
    }


    @Test
    public void createDrinkValid() {
        Drink drink=new Drink(ID, NAME, PORTION, PICTURE_PATH, DESCRIPTION, MONEY);

        DrinkBuilderImpl builder=new DrinkBuilderImpl(ID);
        Drink drinkBuilder=builder.withNameComponent(NAME)
                .withPortion(PORTION)
                .withPicturePath(PICTURE_PATH)
                .withDescription(DESCRIPTION)
                .withPrice(MONEY)
                .build();

        assertEquals(drink, drinkBuilder);
    }

    @Test
    public void createDrinkInvalid() {
        Drink drink=new Drink(ID,INVALID_STRING, PORTION, PICTURE_PATH, DESCRIPTION, MONEY);

        DrinkBuilderImpl builder=new DrinkBuilderImpl(ID);
        Drink drinkBuilder=builder.withNameComponent(NAME)
                .withPortion(PORTION)
                .withPicturePath(PICTURE_PATH)
                .withDescription(DESCRIPTION)
                .withPrice(MONEY)
                .build();

        assertNotEquals(drink, drinkBuilder);
    }


    @Test
    public void createOrderValid() {
        Order order=new Order(ID, mockCartUser, mockDate ,PORTION);

        OrderBuilderImpl builder=new OrderBuilderImpl(ID);
        Order orderBuilder=builder.withCartUser(mockCartUser)
                .withDateOrder(mockDate)
                .withTotalCost(PORTION)
                .build();

        assertEquals(order, orderBuilder);

    }

    @Test
    public void createOrderInvalid() {
        Order order=new Order(ID, mockCartUser, mockDate ,INVALID_INT);

        OrderBuilderImpl builder=new OrderBuilderImpl(ID);
        Order orderBuilder=builder.withCartUser(mockCartUser)
                .withDateOrder(mockDate)
                .withTotalCost(PORTION)
                .build();

        assertNotEquals(order, orderBuilder);

    }

    @Test
    public void createPaymentMethod() {
        PaymentMethod paymentMethod=new PaymentMethod(ID, NAME);

        PaymentMethodBuilderImpl builder=new PaymentMethodBuilderImpl(ID);
        PaymentMethod paymentMethodBuilder=builder.withNamePaymentMethod(NAME)
                .build();

        assertEquals(paymentMethod, paymentMethodBuilder);
    }

    @Test
    public void createPaymentMethodInvalid() {
        PaymentMethod paymentMethod=new PaymentMethod(ID, INVALID_STRING);

        PaymentMethodBuilderImpl builder=new PaymentMethodBuilderImpl(ID);
        PaymentMethod paymentMethodBuilder=builder.withNamePaymentMethod(NAME)
                .build();

        assertNotEquals(paymentMethod, paymentMethodBuilder);
    }

    @Test
    public void createAccountValid() {
        Account account=new Account(ID, mockAccountUser, mockPaymentMethod, mockDate, MONEY);

        AccountBuilderImpl builder=new AccountBuilderImpl(ID);
        Account accountBuilder=builder.withAccountUser(mockAccountUser)
                .withPaymentMethod(mockPaymentMethod)
                .withPaymentDate(mockDate)
                .withAmountOfMoney(MONEY)
                .build();

        assertEquals(account, accountBuilder);
    }

    @Test
    public void createAccountInvalid() {
        Account account=new Account(INVALID_INT, mockAccountUser, mockPaymentMethod, mockDate, MONEY);

        AccountBuilderImpl builder=new AccountBuilderImpl(ID);
        Account accountBuilder=builder.withAccountUser(mockAccountUser)
                .withPaymentMethod(mockPaymentMethod)
                .withPaymentDate(mockDate)
                .withAmountOfMoney(MONEY)
                .build();

        assertNotEquals(account, accountBuilder);
    }
}