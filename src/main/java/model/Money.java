package model;

import java.util.Scanner;

public class Money {
    private int insertedMoney;
    private int selectedProductPrice;
    private int change;
    private int insufficientFunds;

    public int getInsertedMoney() {
        return insertedMoney;
    }

    public void setInsertedMoney(int insertedMoney) {
        this.insertedMoney = insertedMoney;
    }

    public int getSelectedProductPrice() {
        return selectedProductPrice;
    }

    public void setSelectedProductPrice(int selectedProductPrice) {
        this.selectedProductPrice = selectedProductPrice;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public int getInsufficientFunds() {
        return insufficientFunds;
    }

    public void setInsufficientFunds(int insufficientFunds) {
        this.insufficientFunds = insufficientFunds;
    }

    public void userInputMoney() {
        Scanner userInsertedMoneySc = new Scanner(System.in);
        int userInsertedMoney = userInsertedMoneySc.nextInt();
        setInsertedMoney(userInsertedMoney);
    }

    public boolean calculateEnoughMoney(){
        if (insertedMoney >= selectedProductPrice){
            setChange(insertedMoney - selectedProductPrice);
        }
        else {
            setChange(insertedMoney);
            setInsufficientFunds(selectedProductPrice - insertedMoney);
        }
        return insertedMoney >= selectedProductPrice;
    }
}
