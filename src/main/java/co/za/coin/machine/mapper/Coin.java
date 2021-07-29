package co.za.coin.machine.mapper;

import lombok.Getter;
import lombok.Setter;


public class Coin {
    private int change;
    private  String denominations;

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String getDenominations() {
        return denominations;
    }

    public void setDenominations(String denominations) {
        this.denominations = denominations;
    }
}
