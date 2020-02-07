package stock;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Share {
    private String name;
    private int quantity = 0;
    private BigDecimal amount;

    public Share(String name) {
        this.name = name;
    }

    public void buy(int quantity, LocalDate date) {
        this.quantity += quantity;
    }

    public int balance() {
        return this.quantity;
    }

    public void sell(int quantity, LocalDate date) {
        this.quantity -= quantity;
    }

    public void setCurrentPrice(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal value() {
        return BigDecimal.valueOf(balance()).multiply(amount);
    }
}
