package com.db.edu.server;

import com.db.edu.ServerImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import com.db.edu.SysoutCaptureAndAssertionAbility;

import java.io.IOException;

public class ServerTest implements SysoutCaptureAndAssertionAbility {
    @BeforeEach
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @AfterEach
    public void tearDown() {
        resetOut();
    }

    @Test
    public void shouldReturnHistory() throws IOException {
        ServerImpl.getRequest("//snd Hello, world!");
        ServerImpl.getRequest("//hist");
        ServerImpl.sendResponse();

        assertSysoutContains("Hello, world!");
    }
}
