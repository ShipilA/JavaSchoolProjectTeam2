package com.db.edu.server;

import com.db.edu.server.exception.ServerException;
import com.db.edu.server.message.HistoryMessage;
import com.db.edu.server.message.Message;
import com.db.edu.server.message.SendMessage;
import com.db.edu.server.message.SetUserNameMessage;
import com.db.edu.server.rooms.RoomContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

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
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

        Message result = new Message();
        try {
            result = messageFacade.processIncomingMessage(itemsTest, userTest);
        } catch (MessageFacadeException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(SendMessage.class, result.getClass());
    }

    @Test
    void shouldSetUserNameMessage() throws ServerException {
        String userTest = "testClient";
        String itemsTest = "/chid This is a test message!";
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

        Message result = new Message();
        try {
            result = messageFacade.processIncomingMessage(itemsTest, userTest);
        } catch (MessageFacadeException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(SetUserNameMessage.class, result.getClass());
    }

    @Test
    void shouldReturnHistoryMessage() throws ServerException {
        String userTest = "testClient";
        String itemsTest = "/hist";
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

        Message result = new Message();
        try {
            result = messageFacade.processIncomingMessage(itemsTest, userTest);
        } catch (MessageFacadeException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(HistoryMessage.class, result.getClass());
    }
}
