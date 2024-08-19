package com.jhinno.sdk.openapi.example.api.extend;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileSystemType {

    SYSTEM_TYPE_LINUX {
        @Override
        public String getType() {
            return "linux";
        }
    },
    SYSTEM_TYPE_WINDOWS {
        @Override
        public String getType() {
            return "windows";
        }
    };

    public abstract String getType();
}
