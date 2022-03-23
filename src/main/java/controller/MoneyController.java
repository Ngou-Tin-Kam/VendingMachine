package controller;

import model.Money;
import view.PriceInputView;

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

    public void showPriceInput(){
        view.printPriceInput();
    }

    public void askUserForMoneyInput() {
        model.userInputMoney();
    }

    public void displayUserMoneyInput() {
        int moneyInput = model.getInsertedMoney();
        view.printMoneyInput(moneyInput);
    }

    public boolean calculatePriceDifference(int selectedProductPrice){
        model.setSelectedProductPrice(selectedProductPrice);
        boolean isEnoughMoney = model.calculateEnoughMoney();
        if (isEnoughMoney) {
            view.printChange(model.getChange());
        } else {
            view.printInsufficientFunds(model.getInsufficientFunds(), model.getChange());
        }
        return isEnoughMoney;
    }

    public void printInvalidChoiceToReturnMoney(){
        view.printInvalidChoiceToReturnMoney(model.getInsertedMoney());
    }
}
