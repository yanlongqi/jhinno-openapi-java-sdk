package com.jhinno.sdk.openapi.api.app;

import com.jhinno.sdk.openapi.utils.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class AppTypeConstant {

    @Getter
    @AllArgsConstructor
    public enum AppType {
        /**
         * 系统应用
         */
        SYSTEM_APP(StringUtils.EMPTY),
        BATCH_APP("batch"),
        DESKTOP_APP("desktop"),
        ;

        private final String type;

        public List<AppInfo> getAppList(List<AppInfo> list) {
            if (CollectionUtil.isEmpty(list)) {
                return list;
            }
            return list.stream()
                    .filter(t -> StringUtils.equals(t.getType(), this.type))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @AllArgsConstructor
    public enum AppOsType {
        SYSTEM(StringUtils.EMPTY),
        LINUX("linux"),
        WINDOWS("windows"),
        ;

        private final String os;

        public List<AppInfo> getAppList(List<AppInfo> list) {
            if (CollectionUtil.isEmpty(list)) {
                return list;
            }
            return list.stream()
                    .filter(t -> StringUtils.equals(t.getOs(), this.os))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @AllArgsConstructor
    public enum AppCategory {
        SYSTEM("system"),
        APP(StringUtils.EMPTY);

        private final String category;

        public List<AppInfo> getAppList(List<AppInfo> list) {
            if (CollectionUtil.isEmpty(list)) {
                return list;
            }
            return list.stream()
                    .filter(t -> StringUtils.equals(t.getCategory(), this.category))
                    .collect(Collectors.toList());
        }
    }
}
