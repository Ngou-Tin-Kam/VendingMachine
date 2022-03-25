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
    private Audit audit;

    public Money(Audit audit) {
        this.audit = audit;
    }

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

    // Method to include scanner for user input for money
    public int askUserInputMoney() {
        Scanner userInsertedMoneySc = new Scanner(System.in);
        return userInsertedMoneySc.nextInt();
    }

    // Method to take in userInputMoney in pennies which is converted into BigDecimal and doing calculations with it to convert it into pounds
    public void userInputMoney(int userInsertedMoney) {
        BigDecimal insertedMoneyInBigDecimal = BigDecimal.valueOf(userInsertedMoney).divide(BigDecimal.valueOf(100)); // Convert pennies into pound and store it into insertedMoneyInBigDecimal variable
        setInsertedMoney(insertedMoneyInBigDecimal);
        audit.logAction("User inserted money £" + insertedMoneyInBigDecimal); // Logging the action to the auditList
    }

    // Method to check if userInsertedMoney is more than the selectedProductPrice
    public boolean calculateEnoughMoney(){
        return insertedMoney.compareTo(selectedProductPrice) >= 0;
    }

    // Method to work out the change given back to the user in coins
    public ArrayList<BigInteger> workOutCoinChange(BigDecimal changeToGive) {
        ArrayList<BigInteger> coinCounterList = new ArrayList<>(); // New ArrayList to get collection of counters per coins
        ArrayList<Coin> coinList = new ArrayList<>(); // New ArrayList to get collection of coins

        BigDecimal tempChangeToGive = changeToGive; // tempChangeToGive to allow repetitive modification, to work out the remainder of change needed

        // Adding the enum coins in order highest to lowest in value
        coinList.add(Coin.TWO_POUND);
        coinList.add(Coin.ONE_POUND);
        coinList.add(Coin.FIFTY_PENNY);
        coinList.add(Coin.TWENTY_PENNY);
        coinList.add(Coin.TEN_PENNY);
        coinList.add(Coin.ONE_PENNY);

        // For loop to loop through each coin to find out the modula of each coin that can go into the remainder change and add the modula as the counter for that coin
        for (int i = 0; i < coinList.size(); i++) {
            if (!tempChangeToGive.equals(BigDecimal.valueOf(0))) {
                BigDecimal changeDividedByCoin = tempChangeToGive.divide(coinList.get(i).getValue());
                BigInteger changeModula = changeDividedByCoin.setScale(2, RoundingMode.DOWN).toBigInteger();
                coinCounterList.add(changeModula);
                tempChangeToGive = tempChangeToGive.remainder(coinList.get(i).getValue());
            } else {
                coinCounterList.add(BigInteger.valueOf(0));
            }
        }
        return coinCounterList;
    }

    // enum of coins and their values
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
