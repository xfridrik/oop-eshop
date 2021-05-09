package sk.stuba.fei.uim.oop.assignment3.Product;

import lombok.Getter;

@Getter
public class AmountResponse {
    private final int amount;

    public AmountResponse(int amount) {
        this.amount = amount;
    }
}
