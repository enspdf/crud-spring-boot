package me.shackox.util;

/**
 * Created by SHACKOX on 22/01/17.
 */
public enum ResponseType {
    RESPONSE_MESSAGE ("message", 0),
    RESPONSE_MESSAGE_OK ("ok", 1),
    RESPONSE_MESSAGE_ERROR ("error", 2),
    RESPONSE_MESSAGE_CUSTOM ("custom", 3),
    RESPONSE_STATUS ("status", 0),
    RESPONSE_STATUS_OK ("ok", 1),
    RESPONSE_STATUS_ERROR ("error", 2),
    RESPONSE_STATUS_CUSTOM ("custom", 3);

    private String keyResponse;
    private Integer valueResponse;

    ResponseType(String keyResponse, Integer valueResponse) {
        this.keyResponse = keyResponse;
        this.valueResponse = valueResponse;
    }

    public String getKeyResponse() {
        return keyResponse;
    }

    public Integer getValueResponse() {
        return valueResponse;
    }
}
