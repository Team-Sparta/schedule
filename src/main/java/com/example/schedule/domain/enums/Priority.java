package com.example.schedule.domain.enums;

import com.example.schedule.common.exception.BaseException;
import com.example.schedule.common.exception.code.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Slf4j
public enum Priority {
    LOW("l"), MEDIUM("m"), HIGH("h");

    private final String value;

    Priority(String s) {
        this.value = s;
    }

    private static final Map<String, Priority> PRIORITY_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Priority::getValue, Function.identity())));

    @JsonCreator
    public static Priority from(String value) {
        Priority priority = PRIORITY_MAP.get(value.toLowerCase()); // Accept case-insensitive input
        if (priority == null) {
            log.error("Invalid Priority value: notify to activity monitor");
            throw new BaseException(ErrorCode.INVALID_PRIORITY_TYPE);
        }
        return priority;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}