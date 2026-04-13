package ua.voloschenko.theme13;

public class SynchronizedInventory implements Inventory {
    private int available;

    public SynchronizedInventory(int avail) {
        this.available = avail;
    }

    @Override
    public synchronized void reserve(int amount) {
        if (available >= amount) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            available -= amount;
        }
    }

    @Override
    public synchronized int available() {
        return available;
    }
}