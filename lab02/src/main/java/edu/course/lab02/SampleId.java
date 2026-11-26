package edu.course.lab02;


public final class SampleId {

    private final String value;


    public SampleId(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Sample id cannot be null or blank");
        }
        this.value = value;
    }


    public String value() {
        return value;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SampleId)) {
            return false;
        }
        SampleId that = (SampleId) other;
        return value.equals(that.value);
    }


    @Override
    public int hashCode() {
        return value.hashCode();
    }


    @Override
    public String toString() {
        return value;
    }
}
