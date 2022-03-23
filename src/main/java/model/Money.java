package model;

import java.math.BigDecimal;
import java.util.Scanner;

public class Money {
    private BigDecimal insertedMoney;
    private BigDecimal selectedProductPrice;
    private BigDecimal change;
    private BigDecimal insufficientFunds;

    public BigDecimal getInsertedMoney() {
        return insertedMoney;
    }

    public void setInsertedMoney(BigDecimal insertedMoney) {
        this.insertedMoney = insertedMoney;
    }

    public BigDecimal getSelectedProductPrice() {
        return selectedProductPrice;
    }

    public void setSelectedProductPrice(BigDecimal selectedProductPrice) {
        this.selectedProductPrice = selectedProductPrice;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getInsufficientFunds() {
        return insufficientFunds;
    }

    public void setInsufficientFunds(BigDecimal insufficientFunds) {
        this.insufficientFunds = insufficientFunds;
    }

    public void userInputMoney() {
        Scanner userInsertedMoneySc = new Scanner(System.in);
        int userInsertedMoney = userInsertedMoneySc.nextInt();
        setInsertedMoney(BigDecimal.valueOf(userInsertedMoney).divide(BigDecimal.valueOf(100)));
    }

    public boolean calculateEnoughMoney(){
        boolean isEnoughMoney = insertedMoney.compareTo(selectedProductPrice) >= 0;
        if (isEnoughMoney) {
            setChange(insertedMoney.subtract(selectedProductPrice));
        } else {
            setChange(insertedMoney);
            setInsufficientFunds(selectedProductPrice.subtract(insertedMoney));
        }
        return isEnoughMoney;
    }
}
