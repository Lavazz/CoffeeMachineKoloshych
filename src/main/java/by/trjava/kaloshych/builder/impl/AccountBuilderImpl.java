package by.trjava.kaloshych.builder.impl;

import by.trjava.kaloshych.builder.AccountBuilder;
import by.trjava.kaloshych.entity.Account;
import by.trjava.kaloshych.entity.AccountUser;
import by.trjava.kaloshych.entity.PaymentMethod;

import java.util.Date;

public class AccountBuilderImpl implements AccountBuilder {

    private int idAccount;
    private AccountUser accountUser;
    private PaymentMethod paymentMethod;
    private Date paymentDate;
    private double amountOfMoney;

   public AccountBuilderImpl(){}

    public AccountBuilderImpl(int idAccount){
       this.idAccount=idAccount;
    }

    @Override
    public Account build(){
       final Account account=new Account();
       account.setIdAccount(idAccount);
       account.setAccountUser(accountUser);
       account.setPaymentMethod(paymentMethod);
       account.setPaymentDate(paymentDate);
       account.setAmountOfMoney(amountOfMoney);
       return account;
    }

    @Override
    public  AccountBuilder withAccountUser(AccountUser accountUser){
       this.accountUser=accountUser;
       return this;
    }

    @Override
    public  AccountBuilder withPaymentMethod(PaymentMethod paymentMethod){
        this.paymentMethod=paymentMethod;
        return this;
    }

    @Override
    public  AccountBuilder withPaymentDate(Date paymentDate){
        this.paymentDate=paymentDate;
        return this;
    }

    @Override
    public  AccountBuilder withAmountOfMoney(double amountOfMoney){
        this.amountOfMoney=amountOfMoney;
        return this;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }


}
