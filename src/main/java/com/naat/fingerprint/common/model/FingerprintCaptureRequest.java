package com.naat.fingerprint.common.model;

public class FingerprintCaptureRequest {

    private Fingerprint[] mFingerprints;
    private String mReference;
    private boolean mRecapture;
    private boolean mValidateDuplicates;
    private int mMaxValidNfiqScore;

    private FingerprintCaptureRequest(final Builder builder) {
        this.mReference = builder.mReference;
        this.mFingerprints = builder.mFingerprints;
        this.mRecapture = builder.mRecapture;
        this.mValidateDuplicates = builder.mValidateDuplicates;
        this.mMaxValidNfiqScore = builder.mMaxValidNfiqScore;
    }

    public Fingerprint[] getmFingerprints() {
        return mFingerprints;
    }

    public String getmReference() {
        return mReference;
    }

    public int getmMaxValidNfiqScore() {
        return mMaxValidNfiqScore;
    }

    public boolean ismValidateDuplicates() {
        return mValidateDuplicates;
    }

    public boolean ismRecapture() {
        return mRecapture;
    }

    public static class Builder {

        public static final int DEFAULT_NFIQ_SCORE = 1;

        private Fingerprint[] mFingerprints;
        private String mReference;
        private boolean mRecapture;
        private boolean mValidateDuplicates;
        private int mMaxValidNfiqScore = DEFAULT_NFIQ_SCORE;

        public Builder forFingerprints(Fingerprint... fingerprints) {
            this.mFingerprints = fingerprints;
            return this;
        }

        public Builder reference(String reference) {
            this.mReference = reference;
            return this;
        }

        public Builder recapture(boolean recapture) {
            this.mRecapture = recapture;
            return this;
        }

        public Builder validateDuplicates(boolean validateDuplicates) {
            this.mValidateDuplicates = validateDuplicates;
            return this;
        }

        public Builder maxValidNfiqScore(int maxValidNfiqScore) {
            this.mMaxValidNfiqScore = maxValidNfiqScore;
            return this;
        }

        public FingerprintCaptureRequest create() {
            return new FingerprintCaptureRequest(this);
        }

    }
}
