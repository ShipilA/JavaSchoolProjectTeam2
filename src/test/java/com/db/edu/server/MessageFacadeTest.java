package com.db.edu.server;

import com.db.edu.server.exception.ServerException;
import com.db.edu.server.message.HistoryMessage;
import com.db.edu.server.message.Message;
import com.db.edu.server.message.SendMessage;
import com.db.edu.server.message.SetUserNameMessage;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MessageFacadeTest {
    SendMessage sendMessageStub;

    @BeforeEach
    void setUp() {
        sendMessageStub = mock(SendMessage.class);
    }

    @Test
    void shouldSendMessage() throws ServerException {
        String userTest = "testClient";
        String itemsTest = "/snd This is a test message!";
        MessageFacade messageFacade = new MessageFacade();

        Message result = messageFacade.processIncomingMessage(itemsTest, userTest);

        Assertions.assertEquals(SendMessage.class, result.getClass());
    }

    @Test
    void shouldSetUserNameMessage() throws ServerException {
        String userTest = "testClient";
        String itemsTest = "/chid This is a test message!";
        MessageFacade messageFacade = new MessageFacade();

        Message result = messageFacade.processIncomingMessage(itemsTest, userTest);

        Assertions.assertEquals(SetUserNameMessage.class, result.getClass());
    }

    @Test
    void shouldReturnHistoryMessage() throws ServerException {
        String userTest = "testClient";
        String itemsTest = "/hist";
        MessageFacade messageFacade = new MessageFacade();

        Message result = messageFacade.processIncomingMessage(itemsTest, userTest);

        Assertions.assertEquals(HistoryMessage.class, result.getClass());
    }
}
