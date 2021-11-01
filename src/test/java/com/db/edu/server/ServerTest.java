package com.db.edu.server;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import com.db.edu.SysoutCaptureAndAssertionAbility;

import java.io.IOException;

public class ServerTest implements SysoutCaptureAndAssertionAbility {
    @BeforeEach
    public void setUpSystemOut() {
        resetOut();
        captureSysout();
    }

    @AfterEach
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldReturnHistory() {
        ServerImpl TestServer = new ServerImpl();
        TestServer.getRequest("//snd Hello, world!");
        TestServer.getRequest("//hist");
        TestServer.sendResponse();

        assertSysoutContains("Hello, world!");
    }
}
