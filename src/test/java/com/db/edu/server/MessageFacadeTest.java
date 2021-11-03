package com.db.edu.server;

import com.db.edu.server.message.HistoryMessage;
import com.db.edu.server.message.Message;
import com.db.edu.server.message.SendMessage;
import com.db.edu.server.message.SetUserNameMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class MessageFacadeTest {
    SendMessage sendMessageStub;

    @BeforeEach
    void setUp() {
        sendMessageStub = mock(SendMessage.class);
    }

    @Test
    void shouldSendMessage() {
        String userTest = "testClient";
        String itemsTest = "/snd This is a test message!";
        MessageFacade messageFacade = new MessageFacade();

        Message result = new Message();
        try {
            result = messageFacade.processIncomingMessage(itemsTest, userTest);
        } catch (MessageFacadeException e) {
            e.printStackTrace();
        }

        assertEquals(SendMessage.class, result.getClass());
    }

    @Test
    void shouldSetUserNameMessage() {
        String userTest = "testClient";
        String itemsTest = "/chid This is a test message!";
        MessageFacade messageFacade = new MessageFacade();

        Message result = new Message();
        try {
            result = messageFacade.processIncomingMessage(itemsTest, userTest);
        } catch (MessageFacadeException e) {
            e.printStackTrace();
        }

        assertEquals(SetUserNameMessage.class, result.getClass());
    }

    @Test
    void shouldReturnHistoryMessage() {
        String userTest = "testClient";
        String itemsTest = "/hist";
        MessageFacade messageFacade = new MessageFacade();

        Message result = new Message();
        try {
            result = messageFacade.processIncomingMessage(itemsTest, userTest);
        } catch (MessageFacadeException e) {
            e.printStackTrace();
        }

        assertEquals(HistoryMessage.class, result.getClass());
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenWrongHistoryMessage() {
        String userTest = "testClient";
        String itemsTest = "/hist This is a test message";
        MessageFacade messageFacade = new MessageFacade();

        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenSendMessageWithMaxLength() {
        String userTest = "testClient";
        String itemsTest = "/snd Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus est, eu eleifend nisi euismod a. Cras volutpat sollicitudin interdum. Sed!!!";
        MessageFacade messageFacade = new MessageFacade();

        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User message length > 150";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenWrongCommand() {
        String userTest = "testClient";
        String itemsTest = "/send This is a test message";
        MessageFacade messageFacade = new MessageFacade();

        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenMessageSentWithoutCommand() {
        String userTest = "testClient";
        String itemsTest = "/Hello";
        MessageFacade messageFacade = new MessageFacade();

        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenMessageIsInappropriate() {
        String userTest = "testClient";
        String itemsTest = "/ snd Hello";
        MessageFacade messageFacade = new MessageFacade();

        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
