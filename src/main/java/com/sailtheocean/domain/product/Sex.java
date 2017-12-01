package com.sailtheocean.domain.product;

/**
 * Created by fan on 24/08/15.
 * Product for gender
 */
public enum Sex {
    NONE {
        public String getName() {
            return "Both";
        }
    },
    MAN {
        public String getName() {
            return "Man";
        }
    },
    WOMEN {
        public String getName() {
            return "Woman";
        }
    };
    public abstract String getName();
}
