package org.acme.cashback;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum EventOperation {
    CREATE_OPERATION('c', "create"),
    UPDATE_OPERATION('u', "update");

    private final String name;
    private final Character code;

    private static final Map<Character,EventOperation> EVENT_OPERATIONS_MAP;

    EventOperation(Character operationCode, String operationName) {
        this.name = operationName;
        this.code = operationCode;
    }

    static {
        Map<Character, EventOperation> map = new ConcurrentHashMap<Character, EventOperation>();
        for (EventOperation instance : EventOperation.values()) {
            map.put(instance.getCode(),instance);
        }
        EVENT_OPERATIONS_MAP = Collections.unmodifiableMap(map);
    }

    public static EventOperation get (Character code) {
        return EVENT_OPERATIONS_MAP.get(code);
    }

    public String getName() {
        return name;
    }

    public Character getCode() {
        return code;
    }

}
