package edu.course.lab02;


public class DataSample {

    private final SampleId id;
    private final String label;
    private SampleStatus status;
    private final double[] features;


    public DataSample(String id, String label, SampleStatus status, double[] features) {
        this(new SampleId(id), label, status, features);
    }


    public DataSample(SampleId id, String label, SampleStatus status, double[] features) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (label == null || label.isBlank()) {
            throw new IllegalArgumentException("Label cannot be null or blank");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        if (features == null || features.length == 0) {
            throw new IllegalArgumentException("Features cannot be null or empty");
        }
        for (double feature : features) {
            if (!Double.isFinite(feature)) {
                throw new IllegalArgumentException("Features must be finite numbers");
            }
        }

        this.id = id;
        this.label = label;
        this.status = status;
        this.features = features.clone();
    }


    public SampleId getId() {
        return id;
    }


    public String getLabel() {
        return label;
    }


    public SampleStatus getStatus() {
        return status;
    }


    public double[] getFeatures() {
        return features.clone();
    }


    public void changeStatus(SampleStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        status = newStatus;
    }


    public boolean isReady() {
        return status == SampleStatus.READY;
    }


    public double averageFeatureValue() {
        double sum = 0.0;
        for (double feature : features) {
            sum += feature;
        }
        return sum / features.length;
    }


    public double[] normalizedFeatures() {
        double min = features[0];
        double max = features[0];
        for (double feature : features) {
            if (feature < min) {
                min = feature;
            }
            if (feature > max) {
                max = feature;
            }
        }

        double[] normalized = new double[features.length];
        double range = max - min;
        for (int i = 0; i < features.length; i++) {
            if (range == 0.0) {
                normalized[i] = 0.0;
            } else {
                normalized[i] = (features[i] - min) / range;
            }
        }
        return normalized;
    }
}
