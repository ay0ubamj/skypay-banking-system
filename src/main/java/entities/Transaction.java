package entities;

import enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
public class Transaction {
    private TransactionType type;
    private int amount;
    private String date;
    private int currentBalance;

    @Override
    public String toString() {
        String transactionAmount = TransactionType.DEPOSIT.equals(type) ? String.valueOf("+" + amount) : String.valueOf("-" + amount);

        return String.format("%s || %s || %d", this.date, transactionAmount, this.currentBalance);
    }
}
