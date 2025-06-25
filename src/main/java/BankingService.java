import services.impl.AccountServiceImpl;

public class BankingService {
    public static void main(String[] args) {

        System.out.println("\n---- Banking Service is running ---- \n");

        AccountServiceImpl accountService = new AccountServiceImpl();

        System.out.println("### DEPOSIT OF 1000 ####");
        accountService.deposit(1000);
        System.out.println("### DEPOSIT OF 2000 ####");
        accountService.deposit(2000);
        System.out.println("### WITHDRAW OF 500 #### \n");
        accountService.withdraw(500);

        accountService.printStatement();

        System.out.println("\n---- Running cases test ---- \n");

        try {
            accountService.deposit(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Negative deposit caught: " + e.getMessage());
        }

        try {
            accountService.withdraw(0);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Zero withdrawal caught: " + e.getMessage());
        }

        try {
            accountService.withdraw(10000);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Insufficient funds caught: " + e.getMessage());
        }
    }
}
