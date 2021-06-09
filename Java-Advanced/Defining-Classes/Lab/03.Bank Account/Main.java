import java.util.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, BankAccount> accountMap = new HashMap<>();

        String line = scanner.nextLine();
        while (!line.equals("end")) {
            String[] tokens = line.split("\\s+");
            String command = tokens[0];
            String output = null;
            switch (command) {
                case "Create":
                    BankAccount bankAccount = new BankAccount();
                    accountMap.put(bankAccount.getId(), bankAccount);
                    output = String.format("Account ID%d created", bankAccount.getId());
                    break;
                case "Deposit": {
                    int id = Integer.parseInt(tokens[1]);
                    int amount = Integer.parseInt(tokens[2]);
                    BankAccount bankAccount1 = accountMap.get(id);
                    output = executeIfNotNull(bankAccount1, b -> {
                        b.deposit(amount);
                        return String.format("Deposited %d to ID%d", amount, id);
                    });
                    break;
                }
                case "SetInterest":
                    BankAccount.setInterestRate(Double.parseDouble(tokens[1]));
                    break;
                case "GetInterest":
                    int id = Integer.parseInt(tokens[1]);
                    int years = Integer.parseInt(tokens[2]);
                    BankAccount bankAccount1 = accountMap.get(id);
                    output = executeIfNotNull(bankAccount1, b -> {
                        double interest = b.getInterest(years);
                        return String.format("%.2f", interest);
                    });
                    break;
            }
            if (output != null) {
                System.out.println(output);
            }
            line = scanner.nextLine();
        }

    }

    public static String executeIfNotNull(BankAccount account,
                                          Function<BankAccount, String> func) {
        if (account == null) {
            return "Account does not exist";
        }
        return func.apply(account);
    }
}
