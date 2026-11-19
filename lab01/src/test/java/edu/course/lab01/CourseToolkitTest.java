package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CourseToolkitTest {

    // Подготовительная часть

    @Test
    void returnsTrueForPositiveEvenNumber() {
        assertTrue(CourseToolkit.isEven(4));
    }

    @Test
    void returnsFalseForPositiveOddNumber() {
        assertFalse(CourseToolkit.isEven(7));
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        assertTrue(CourseToolkit.isEven(-8)); // Тест из подготовительной части
    }

    // Тесты для isPrime

    @Test
    void isPrimeReturnsFalseForNumbersLessThanTwo() {
        assertFalse(CourseToolkit.isPrime(-5));
        assertFalse(CourseToolkit.isPrime(0));
        assertFalse(CourseToolkit.isPrime(1));
    }

    @Test
    void isPrimeReturnsTrueForTwo() {
        assertTrue(CourseToolkit.isPrime(2));
    }

    @Test
    void isPrimeReturnsTrueForPrimeNumbers() {
        assertTrue(CourseToolkit.isPrime(3));
        assertTrue(CourseToolkit.isPrime(17));
        assertTrue(CourseToolkit.isPrime(97));
    }

    @Test
    void isPrimeReturnsFalseForCompositeNumbers() {
        assertFalse(CourseToolkit.isPrime(4));
        assertFalse(CourseToolkit.isPrime(100));
    }

    @Test
    void isPrimeCorrectlyHandlesSquareOfPrime() {
        // 49 = 7 * 7. Проверяем, что цикл с d * d <= number работает корректно
        assertFalse(CourseToolkit.isPrime(49));
    }

    // Тесты для isPalindrome

    @Test
    void isPalindromeReturnsTrueForSimplePalindrome() {
        assertTrue(CourseToolkit.isPalindrome("racecar"));
        assertTrue(CourseToolkit.isPalindrome("abba"));
    }

    @Test
    void isPalindromeReturnsFalseForNonPalindrome() {
        assertFalse(CourseToolkit.isPalindrome("hello"));
    }

    @Test
    void isPalindromeIsCaseAndSpaceSensitive() {
        assertFalse(CourseToolkit.isPalindrome("Racecar")); // Регистр важен
        assertFalse(CourseToolkit.isPalindrome("race car")); // Пробелы важны
    }

    @Test
    void isPalindromeThrowsExceptionForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.isPalindrome(null));
    }

    // Тесты для average (обычный, отрицательный, ошибочный)

    @Test
    void averageReturnsCorrectResultForPositiveNumbers() {
        int[] values = {1, 2, 3, 4};
        assertEquals(2.5, CourseToolkit.average(values), 0.0001);
    }

    @Test
    void averageReturnsCorrectResultForNegativeNumbers() {
        int[] values = {-1, -2, -3};
        assertEquals(-2.0, CourseToolkit.average(values), 0.0001);
    }

    @Test
    void averageDoesNotModifyInputArray() {
        int[] values = {1, 2, 3};
        int[] original = values.clone();
        CourseToolkit.average(values);
        assertArrayEquals(original, values, "Метод не должен изменять входной массив");
    }

    @Test
    void averageThrowsExceptionForNull() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(null));
    }

    @Test
    void averageThrowsExceptionForEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[0]));
    }

    // Дополнительная часть: min и max

    @Test
    void minReturnsSmallestValue() {
        assertEquals(1, CourseToolkit.min(new int[]{5, 1, 9, 3}));
    }

    @Test
    void minWorksWithNegativeNumbers() {
        assertEquals(-7, CourseToolkit.min(new int[]{-2, -7, 0, 4}));
    }

    @Test
    void minWorksWithSingleElement() {
        assertEquals(42, CourseToolkit.min(new int[]{42}));
    }

    @Test
    void minThrowsExceptionForNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.min(null));
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.min(new int[0]));
    }

    @Test
    void maxReturnsLargestValue() {
        assertEquals(9, CourseToolkit.max(new int[]{5, 1, 9, 3}));
    }

    @Test
    void maxWorksWithNegativeNumbers() {
        assertEquals(-1, CourseToolkit.max(new int[]{-5, -1, -9}));
    }

    @Test
    void maxWorksWithSingleElement() {
        assertEquals(42, CourseToolkit.max(new int[]{42}));
    }

    @Test
    void maxThrowsExceptionForNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.max(null));
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.max(new int[0]));
    }

    @Test
    void minAndMaxDoNotModifyInputArray() {
        int[] values = {3, 1, 2};
        int[] original = values.clone();
        CourseToolkit.min(values);
        CourseToolkit.max(values);
        assertArrayEquals(original, values);
    }
}