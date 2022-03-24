package controller;

import model.Money;
import view.PriceInputView;

import java.math.BigDecimal;

public class MoneyController {
    private Money model;
    private PriceInputView view;

    public MoneyController(Money model, PriceInputView view) {
        this.model = model;
        this.view = view;
    }

    public Money getModel() {
        return model;
    }

    public void setModel(Money model) {
        this.model = model;
    }

    public PriceInputView getView() {
        return view;
    }

    public void setView(PriceInputView view) {
        this.view = view;
    }

    public void showPriceInput() {
        view.printPriceInput();
    }

    public void askUserForMoneyInput() {
        view.printPriceInput();
        model.userInputMoney();
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
    }

    public void returnMoneyAndShowInsufficientFunds() {
        BigDecimal insertedMoney = model.getInsertedMoney();
        BigDecimal selectedProductPrice = model.getSelectedProductPrice();
        model.setChange(insertedMoney);
        model.setInsufficientFunds(selectedProductPrice.subtract(insertedMoney));
        view.printInsufficientFunds(model.getInsufficientFunds(), model.getChange());
    }

    public void printInvalidChoiceToReturnMoney() {
        view.printInvalidChoiceToReturnMoney(model.getInsertedMoney());
    }
}
