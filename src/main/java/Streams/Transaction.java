package Streams;

import java.util.Objects;

public class Transaction {
    private int transactionId;
    private String type;
    private int value;

    public Transaction(int transactionId, String type, int value) {
        this.transactionId = transactionId;
        this.type = type;
        this.value = value;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", type='" + type + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionId == that.transactionId && value == that.value && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, type, value);
    }
}
