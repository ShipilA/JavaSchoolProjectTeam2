package com.db.edu.server;

import com.db.edu.ServerImpl;
import com.acme.edu.SysoutCaptureAndAssertionAbility;

public class ServerTest implements SysoutCaptureAndAssertionAbility {
    @Test
    public void shouldReturnHistory() throws IOException {
        ServerImpl.getRequest("//snd Hello, world!");
        ServerImpl.getRequest("//hist");

        assertSysoutContains("Hello, world!");
    }
}
