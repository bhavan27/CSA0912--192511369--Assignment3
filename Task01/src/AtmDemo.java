import java.io.*;
class InvalidWithdrawalException extends RuntimeException {
    InvalidWithdrawalException(String m) { super(m); }
}
class InsufficientFundsException extends RuntimeException {
    InsufficientFundsException(String m) { super(m); }
}
public class AtmDemo {
    static final int BALANCE = 5000;
    static void withdraw(String accountText, String amountText, String cardFile) {
        System.out.println("\n--- Withdrawal ---");
        try {
            int account = Integer.parseInt(accountText);
            int amount = Integer.parseInt(amountText);
            if (account <= 0) throw new InvalidWithdrawalException("Account must be positive");
            if (amount <= 0) throw new InvalidWithdrawalException("Amount must be positive");
            if (amount > BALANCE) throw new InsufficientFundsException("Insufficient balance");
            try (BufferedReader r = new BufferedReader(new FileReader(cardFile))) {
                if (r.readLine() == null) throw new IOException("Empty card-reader data");
                System.out.println("Account " + account + ": cash dispensed = " + amount);
            }
        } catch (NumberFormatException e) {
            System.out.println("Input error: enter whole numbers");
        } catch (InvalidWithdrawalException | InsufficientFundsException e) {
            System.out.println("Rejected: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Card-reader error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } finally {
            System.out.println("ATM session closed (finally)");
        }
    }
    public static void main(String[] args) throws Exception {
        try (FileWriter w = new FileWriter("input/card-reader.txt")) { w.write("CARD-OK\n"); }
        withdraw("123456","1000","input/card-reader.txt");
        withdraw("ABC","1000","input/card-reader.txt");
        withdraw("123456","-50","input/card-reader.txt");
        withdraw("123456","7000","input/card-reader.txt");
        withdraw("123456","1000","input/missing-card.txt");
        try { throw new IllegalStateException("Simulated device failure"); }
        catch (IllegalStateException e) { System.out.println("Stack trace demonstration:"); e.printStackTrace(System.out); }
    }
}
