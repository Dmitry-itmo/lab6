package laba.utility;

import java.io.Serializable;

public class HeartbeatPacket implements Serializable {
    private static final long serialVersionUID = 2L;
    private final String message = "PING";
}
