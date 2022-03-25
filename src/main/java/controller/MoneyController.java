package controller;

import model.Money;
import view.MoneyView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class MoneyController {
    private Money model;
    private MoneyView view;

    public MoneyController(Money model, MoneyView view) {
        this.model = model;
        this.view = view;
    }

    public Money getModel() {
        return model;
    }

    public void setModel(Money model) {
        this.model = model;
    }

    public MoneyView getView() {
        return view;
    }

    public void setView(MoneyView view) {
        this.view = view;
    }

    public void showPriceInput() {
        view.printPriceInput();
    }

    public void askUserForMoneyInput() {
        view.printPriceInput();
        int userMoneyInput = model.askUserInputMoney();
        model.userInputMoney(userMoneyInput);
        displayUserMoneyInput();
    }

    private void displayUserMoneyInput() {
        BigDecimal moneyInput = model.getInsertedMoney();
        view.printMoneyInput(moneyInput);
    }

    public boolean calculatePriceDifference(BigDecimal selectedProductPrice) {
        model.setSelectedProductPrice(selectedProductPrice);
        return model.calculateEnoughMoney();
    }

    public void subtractMoneyFromProductPrice() {
        BigDecimal insertedMoney = model.getInsertedMoney();
        BigDecimal selectedProductPrice = model.getSelectedProductPrice();
        model.setChange(insertedMoney.subtract(selectedProductPrice));
        view.printChange(model.getChange());
        ArrayList<BigInteger> coinList = model.workOutCoinChange(model.getChange());
        view.printChangeListMessage(coinList);
    }

    public void returnMoneyAndShowInsufficientFunds() {
        BigDecimal insertedMoney = model.getInsertedMoney();
        BigDecimal selectedProductPrice = model.getSelectedProductPrice();
        model.setChange(insertedMoney);
        model.setInsufficientFunds(selectedProductPrice.subtract(insertedMoney));
        view.printInsufficientFunds(model.getInsufficientFunds(), model.getChange());
        ArrayList<BigInteger> coinList = model.workOutCoinChange(model.getChange());
        view.printChangeListMessage(coinList);
    }

    public void printInvalidChoiceToReturnMoney() {
        view.printInvalidChoiceToReturnMoney(model.getInsertedMoney());
        ArrayList<BigInteger> coinList = model.workOutCoinChange(model.getInsertedMoney());
        view.printChangeListMessage(coinList);
    }
}
