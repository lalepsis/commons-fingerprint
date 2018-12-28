package com.naat.fingerprint.common.model;

public enum Fingerprint {

        UNKNOWN(0),
        RIGHT_THUMB(1),
        RIGHT_INDEX_FINGER(2),
        RIGHT_MIDDLE_FINGER(3),
        RIGHT_RING_FINGER(4),
        RIGHT_LITTLE_FINGER(5),
        LEFT_THUMB(6),
        LEFT_INDEX_FINGER(7),
        LEFT_MIDDLE_FINGER(8),
        LEFT_RING_FINGER(9),
        LEFT_LITTLE_FINGER(10);

        private final int code;

        Fingerprint(int code) {
            this.code = code;
        }

        public static Fingerprint fromCode(int code) {
            Fingerprint[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Fingerprint t = var1[var3];
                if (t.code == code) {
                    return t;
                }
            }

            return null;
        }

        public int toCode() {
            return this.code;
        }
    }