package ua.voloschenko.theme13;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order(1, 1502),
                new Order(22, 42142),
                new Order(333, 8510),
                new Order(4444, 911),
                new Order(55555, 322)
        );

        Runnable sumTask = () -> {
            long sum = 0;
            for (Order order : orders) {
                sum += order.totalCents();
            }
            System.out.println("Sum is: " + sum);
        };

        Runnable maxTask = () -> {
            int max = 0;
            long maxId = -1;
            for (Order order : orders) {
                if (order.totalCents() > max) {
                    max = order.totalCents();
                    maxId = order.id();
                }
            }
            System.out.println("Max order is id " + maxId + " for " + max + " cents");
        };

        System.out.println("Doing parallel");

        runAndWait(List.of(sumTask, maxTask));

        System.out.println("All tasks done. Main thread continues.");

        System.out.println("task2");
        System.out.println("task2");
        System.out.println("task2");
        System.out.println("task2");
        System.out.println("task2");
        System.out.println("task2");

        runSingleTest(new UnsafeInventory(100), "UnsafeInventory");
        runSingleTest(new SynchronizedInventory(100), "SynchronizedInventory");

        runStressHarness(500);


        System.out.println("task3");
        System.out.println("task3");
        System.out.println("task3");
        System.out.println("task3");
        System.out.println("task3");
        System.out.println("task3");


        Account account1 = new Account(1, 10000);
        Account account2 = new Account(2, 10000);

        System.out.println("Start balance 1: " + account1.getBalance());
        System.out.println("Start balance 2: " + account2.getBalance());

        Runnable transferAtoB = () -> {
            for (int i = 0; i < 500; i++) {
                TransferService.transfer(account1, account2, 10);
            }
        };

        Runnable transferBtoA = () -> {
            for (int i = 0; i < 500; i++) {
                TransferService.transfer(account2, account1, 10);
            }
        };

        Thread t1 = new Thread(transferAtoB);
        Thread t2 = new Thread(transferBtoA);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Threads interruption");
        }

        System.out.println("Transfers resolved without deadlock");
        System.out.println("Final balance 1: " + account1.getBalance());
        System.out.println("Final balance 2: " + account2.getBalance());
    }

    public record Order(long id, int totalCents) {}

    public static void runAndWait(List<Runnable> tasks) {

        List<Thread> threads = new ArrayList<>();

        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println(": " + e.getMessage());
            }
        }
    }

    //task2
    //task2
    //task2
    //task2
    //task2
    //task2

    private static void runSingleTest(Inventory inventory, String name) {
        Runnable task = () -> inventory.reserve(60);

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(name+ ". Left items: " + inventory.available());
    }

    private static void runStressHarness(int iterations) {
        int violationsCount = 0;

        for (int i = 0; i < iterations; i++) {
            Inventory unsafe = new UnsafeInventory(100);

            Thread t1 = new Thread(() -> unsafe.reserve(60));
            Thread t2 = new Thread(() -> unsafe.reserve(60));

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (unsafe.available() < 0) {
                violationsCount++;
            }
        }

        System.out.println("Iterations did "+ iterations);
        System.out.println("available >= 0 is " + violationsCount + " times");
    }

}