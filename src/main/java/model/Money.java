package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
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
        return insertedMoney.compareTo(selectedProductPrice) >= 0;
    }

    public ArrayList<BigInteger> workOutCoinChange(BigDecimal changeToGive) {
        ArrayList<BigInteger> coinCounterList = new ArrayList<>();
        ArrayList<Coin> coinList = new ArrayList<>();

        BigDecimal tempChangeToGive = changeToGive;

        coinList.add(Coin.TWO_POUND);
        coinList.add(Coin.ONE_POUND);
        coinList.add(Coin.FIFTY_PENNY);
        coinList.add(Coin.TWENTY_PENNY);
        coinList.add(Coin.TEN_PENNY);
        coinList.add(Coin.ONE_PENNY);

        for (int i = 0; i < coinList.size(); i++) {
            if (!tempChangeToGive.equals(BigDecimal.valueOf(0))) {
                BigDecimal y = tempChangeToGive.divide(coinList.get(i).getValue());
                BigDecimal x = y.setScale(2, RoundingMode.DOWN);
                BigInteger z = x.toBigInteger();
                coinCounterList.add(z);
                tempChangeToGive = tempChangeToGive.remainder(coinList.get(i).getValue());
            } else {
                coinCounterList.add(BigInteger.valueOf(0));
            }
        }
        return coinCounterList;
    }

    public enum Coin {
        ONE_PENNY(new BigDecimal("0.01")),
        TEN_PENNY(new BigDecimal("0.10")),
        TWENTY_PENNY(new BigDecimal("0.20")),
        FIFTY_PENNY(new BigDecimal("0.50")),
        ONE_POUND(new BigDecimal("1.00")),
        TWO_POUND(new BigDecimal("2.00"));

        private BigDecimal value;

        Coin(BigDecimal value) {
            this.value = value;
        }

        public BigDecimal getValue() {
            return value;
        }
    }
}
