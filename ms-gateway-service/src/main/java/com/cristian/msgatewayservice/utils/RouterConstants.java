package com.cristian.msgatewayservice.utils;

public class RouterConstants {
    public static final String USERS_SERVICE = "lb://ms-users-service";
    public static final String CONVERSATIONS_SERVICE = "lb://ms-conversations-service";
    public static final String MESSAGES_SERVICE = "lb://ms-messages-service";
    public static final String CHAT_SERVICE = "lb:ws://MS-CHAT-SERVICE";
    public static final String ACTUAL_SEGMENT = "/chat/(?<segment>.*)";
    public static final String RESPONSE_TIME_HEADER = "X-response-time";
    public static final String REPLACEMENT_SEGMENT = "/${segment}";
}
