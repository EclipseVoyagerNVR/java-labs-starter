package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class SampleIdTest {

    @Test
    void rejectsNullOrBlankValue() {
        assertThrows(IllegalArgumentException.class, () -> new SampleId(null));
        assertThrows(IllegalArgumentException.class, () -> new SampleId(""));
        assertThrows(IllegalArgumentException.class, () -> new SampleId("   "));
    }

    @Test
    void exposesValue() {
        assertEquals("s-42", new SampleId("s-42").value());
        assertEquals("s-42", new SampleId("s-42").toString());
    }

    @Test
    void comparesByValue() {
        SampleId first = new SampleId("s-42");
        SampleId second = new SampleId("s-42");
        SampleId other = new SampleId("s-43");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertNotEquals(first, other);
        assertNotEquals(first, "s-42");
    }
}
