package ua.voloschenko.theme13;

public class UnsafeInventory implements Inventory {

    private int availableAmount;

    public UnsafeInventory(int avail) {
        this.availableAmount = avail;
    }

    @Override
    public void reserve(int amount) {
        if (availableAmount >= amount) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            availableAmount -= amount;
        }
    }

    @Override
    public int available() {
        return availableAmount;
    }
}