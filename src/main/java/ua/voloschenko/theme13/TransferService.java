package ua.voloschenko.theme13;

public class TransferService {

    public static void transfer(Account from, Account to, long amount) {
        if (from == to) {
            System.out.println("you not pay to yourself");
            return;
        }

        Account firstLock  = from.getId() < to.getId() ? from :  to  ;
        Account secondLock = from.getId() < to.getId() ?  to  : from ;

        synchronized (firstLock) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (secondLock) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                } else {
                    System.out.println("Недостатньо коштів на рахунку " + from.getId());
                }
            }
        }
    }
}