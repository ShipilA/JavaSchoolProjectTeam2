package com.db.edu.server;

import com.db.edu.server.message.HistoryMessage;
import com.db.edu.server.message.Message;
import com.db.edu.server.message.SendMessage;
import com.db.edu.server.message.SetUserNameMessage;
import com.db.edu.server.rooms.RoomContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.fest.assertions.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class MessageFacadeTest {
    SendMessage sendMessageStub;
    String userTest;

    @BeforeEach
    void setUp() {
        userTest = "testClient";
        sendMessageStub = mock(SendMessage.class);
    }

    @Test
    void shouldSendMessage() {
        String itemsTest = "/snd This is a test message!";
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

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
        String itemsTest = "/chid This is a test message!";
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

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
        String itemsTest = "/hist";
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

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
        String itemsTest = "/hist This is a test message";
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenSendMessageWithOverMaxLength() {
        String itemsTest = "/snd Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Sed sodales tellus est, eu eleifend nisi euismod a. " +
                "Cras volutpat sollicitudin interdum. Sed!!!";
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User message length > 150";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void shouldThrowMessageFacadeErrorWhenWrongCommand() {
        String itemsTest = "/send This is a test message";
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenMessageSentWithoutCommand() {
        String itemsTest = "/Hello";
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenMessageIsInappropriate() {
        String itemsTest = "/ snd Hello";
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldsendMessageWhenSendMessageWithEqualMaxLength() throws MessageFacadeException {
        String itemsTest = "/snd FLpQ9eJVzGLUnobIejVxQ4egg0Bqc0ogJVh1TAppKfUbze" +
                "WVCElDI7EpWb7jH79ISU7liZnXdERDhjKrX2yGt0EgygCbvG1JyTVqaa" +
                "Mj9viDPuaUSbYYx1O2yUYvhP90rG7EBTNKcNZufKKa6aClLj";
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

        String expectedMessage = "FLpQ9eJVzGLUnobIejVxQ4egg0Bqc0ogJVh1TAppKfUbze" +
                "WVCElDI7EpWb7jH79ISU7liZnXdERDhjKrX2yGt0EgygCbvG1JyTVqaa" +
                "Mj9viDPuaUSbYYx1O2yUYvhP90rG7EBTNKcNZufKKa6aClLj";

        assertThat(String.valueOf(messageFacade.processIncomingMessage(itemsTest, userTest))).contains(expectedMessage);
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenSendMessageWithZeroLength() throws MessageFacadeException {
        String itemsTest = "/snd";
        MessageFacade messageFacade = new MessageFacade(new RoomContainer());

        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}