package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void withdrawReducesBalance() {
        BankAccount account = new BankAccount(100);

        account.withdraw(30);

        assertEquals(70, account.getBalance());
    }

    @Test
    void withdrawThrowsWhenAmountExceedsBalance() {
        BankAccount account = new BankAccount(50);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(51));
        assertEquals(50, account.getBalance(), "Баланс не должен меняться при ошибке");
    }

    @Test
    void depositIncreasesBalance() {
        BankAccount account = new BankAccount(10);

        account.deposit(5);

        assertEquals(15, account.getBalance());
    }

    @Test
    void constructorRejectsNegativeBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1));
    }

    @Test
    void depositRejectsNonPositiveAmount() {
        BankAccount account = new BankAccount(10);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-1));
        assertEquals(10, account.getBalance());
    }

    @Test
    void withdrawRejectsNonPositiveAmount() {
        BankAccount account = new BankAccount(10);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-5));
        assertEquals(10, account.getBalance());
    }
}
