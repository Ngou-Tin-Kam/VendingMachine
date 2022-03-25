package controller;

import model.Money;
import view.MoneyView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class MoneyController {
    private Money model;
    private MoneyView view;

    // Using a constructor to set the model and view, so getters and setters are not needed
    public MoneyController(Money model, MoneyView view) {
        this.model = model;
        this.view = view;
    }

    // A method to call the view and model
    public void askUserForMoneyInput() {
        view.printPriceInput(); // Calling the view to printPriceInput sout
        int userMoneyInput = model.askUserInputMoney(); //Calling the model to get a userInputMoney
        model.userInputMoney(userMoneyInput); // Setting the value to the attribute
        view.printMoneyInput(model.getInsertedMoney()); // Passing in insertedMoney into the view and display it
    }

    // A method to call the model to calculate the difference from insertedMoney and selectedProductPrice
    public boolean calculatePriceDifference(BigDecimal selectedProductPrice) {
        model.setSelectedProductPrice(selectedProductPrice);
        return model.calculateEnoughMoney();
    }

    // A method to call the model to subtract the insertedMoney from the productPrice to find out change
    public void subtractMoneyFromProductPrice() {
        BigDecimal insertedMoney = model.getInsertedMoney();
        BigDecimal selectedProductPrice = model.getSelectedProductPrice();
        model.setChange(insertedMoney.subtract(selectedProductPrice)); // setChange is to subtract insertedMoney from the selectedProductPrice to find out what change to give back to the user
        view.printChange(model.getChange());
        ArrayList<BigInteger> coinList = model.workOutCoinChange(model.getChange()); // Creating an ArrayList with BigInteger called coinList that calls the model to work out the change which then calls another model to work out the change in coins
        view.printChangeListMessage(coinList);
    }

    // A method to call the model to subtract productPrice from the insertedMoney to find out the difference needed
    public void returnMoneyAndShowInsufficientFunds() {
        BigDecimal insertedMoney = model.getInsertedMoney();
        BigDecimal selectedProductPrice = model.getSelectedProductPrice();
        model.setChange(insertedMoney);
        model.setInsufficientFunds(selectedProductPrice.subtract(insertedMoney));
        view.printInsufficientFunds(model.getInsufficientFunds(), model.getChange());
        ArrayList<BigInteger> coinList = model.workOutCoinChange(model.getChange());
        view.printChangeListMessage(coinList);
    }

    // A method to call the model to return money if an invalid choice was selected and prints it out as coins
    public void printInvalidChoiceToReturnMoney() {
        view.printInvalidChoiceToReturnMoney(model.getInsertedMoney());
        ArrayList<BigInteger> coinList = model.workOutCoinChange(model.getInsertedMoney());
        view.printChangeListMessage(coinList);
    }
}
