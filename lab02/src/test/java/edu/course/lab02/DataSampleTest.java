package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class DataSampleTest {

    @Test
    void createsValidSample() {
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{1.0, 3.0});

        assertEquals(new SampleId("s-1"), sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals(SampleStatus.NEW, sample.getStatus());
        assertArrayEquals(new double[]{1.0, 3.0}, sample.getFeatures());
        assertFalse(sample.isReady());
    }

    @Test
    void constructorRejectsBlankIdAndLabel() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample((String) null, "cat", SampleStatus.NEW, new double[]{1.0}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample((SampleId) null, "cat", SampleStatus.NEW, new double[]{1.0}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("  ", "cat", SampleStatus.NEW, new double[]{1.0}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", null, SampleStatus.NEW, new double[]{1.0}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", "", SampleStatus.NEW, new double[]{1.0}));
    }

    @Test
    void constructorRejectsNullStatus() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", "cat", null, new double[]{1.0}));
    }

    @Test
    void constructorRejectsNullOrEmptyOrNonFiniteFeatures() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", "cat", SampleStatus.NEW, null));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", "cat", SampleStatus.NEW, new double[0]));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{Double.NaN}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{Double.POSITIVE_INFINITY}));
    }

    @Test
    void changeStatusUpdatesStatusAndReadiness() {
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{1.0});

        sample.changeStatus(SampleStatus.READY);

        assertEquals(SampleStatus.READY, sample.getStatus());
        assertTrue(sample.isReady());

        sample.changeStatus(SampleStatus.REJECTED);
        assertFalse(sample.isReady());
    }

    @Test
    void changeStatusRejectsNull() {
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{1.0});

        assertThrows(IllegalArgumentException.class, () -> sample.changeStatus(null));
        assertEquals(SampleStatus.NEW, sample.getStatus());
    }

    @Test
    void computesAverageFeatureValue() {
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{1.0, 2.0, 6.0});

        assertEquals(3.0, sample.averageFeatureValue(), 0.0001);
    }

    @Test
    void copiesFeaturesOnConstructionAndOnAccess() {
        double[] features = {1.0, 2.0};
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, features);

        features[0] = 99.0;
        assertArrayEquals(new double[]{1.0, 2.0}, sample.getFeatures());

        double[] returned = sample.getFeatures();
        returned[1] = 99.0;
        assertArrayEquals(new double[]{1.0, 2.0}, sample.getFeatures());
    }

    @Test
    void normalizesFeaturesWithoutChangingOriginal() {
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{2.0, 4.0, 6.0});

        double[] normalized = sample.normalizedFeatures();

        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, normalized, 0.0001);
        assertArrayEquals(new double[]{2.0, 4.0, 6.0}, sample.getFeatures());
    }

    @Test
    void normalizationOfConstantFeaturesReturnsZeros() {
        DataSample sample = new DataSample("s-1", "cat", SampleStatus.NEW, new double[]{5.0, 5.0});

        assertArrayEquals(new double[]{0.0, 0.0}, sample.normalizedFeatures());
    }
}
