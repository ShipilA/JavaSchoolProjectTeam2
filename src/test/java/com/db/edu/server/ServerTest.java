package com.db.edu.server;

import com.db.edu.SysoutCaptureAndAssertionAbility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
//        testServer.getRequest("/snd Hello, world!");
//        testServer.getRequest("/hist");
//        testServer.sendResponse();

//        assertSysoutContains("Hello, world!");
    }
}
