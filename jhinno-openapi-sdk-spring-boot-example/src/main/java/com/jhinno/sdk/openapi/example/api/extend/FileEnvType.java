package com.jhinno.sdk.openapi.example.api.extend;

public enum FileEnvType {

    HOME_ENV{
        @Override
        public String getEnv() {
            return "home";
        }
    },
    SPOOLER_ENV{
        @Override
        public String getEnv() {
            return "spooler";
        }
    };

    public abstract String getEnv();
}
