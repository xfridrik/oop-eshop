package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Getter;

@Getter
public class AmountResponse {
    private final long amount;

    public AmountResponse(long amount) {
        this.amount = amount;
    }
}
