package com.db.edu.server;

import com.db.edu.server.message.HistoryMessage;
import com.db.edu.server.message.Message;
import com.db.edu.server.message.SendMessage;
import com.db.edu.server.message.SetUserNameMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class MessageFacadeTest {
    SendMessage sendMessageStub;
    String userTest;
    MessageFacade messageFacade;
    String itemsTest;

    @BeforeEach
    void setUp() {
        userTest = "testClient";
        sendMessageStub = mock(SendMessage.class);
        messageFacade = new MessageFacade(new UserThreadsController());
    }

    @Test
    void shouldSendMessage() {
        itemsTest = "/snd This is a test message!";
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
        itemsTest = "/chid This is a test message!";
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
        itemsTest = "/hist";
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
        itemsTest = "/hist This is a test message";
        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenSendMessageWithOverMaxLength() {
        itemsTest = "/snd Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Sed sodales tellus est, eu eleifend nisi euismod a. " +
                "Cras volutpat sollicitudin interdum. Sed!!!";
        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User message length > 150";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void shouldThrowMessageFacadeErrorWhenWrongCommand() {
        itemsTest = "/send This is a test message";
        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenMessageSentWithoutCommand() {
        itemsTest = "/Hello";
        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenMessageIsInappropriate() {
        itemsTest = "/ snd Hello";
        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldsendMessageWhenSendMessageWithEqualMaxLength() throws MessageFacadeException {
        itemsTest = "/snd FLpQ9eJVzGLUnobIejVxQ4egg0Bqc0ogJVh1TAppKfUbze" +
                "WVCElDI7EpWb7jH79ISU7liZnXdERDhjKrX2yGt0EgygCbvG1JyTVqaa" +
                "Mj9viDPuaUSbYYx1O2yUYvhP90rG7EBTNKcNZufKKa6aClLj";
        String expectedMessage = "FLpQ9eJVzGLUnobIejVxQ4egg0Bqc0ogJVh1TAppKfUbze" +
                "WVCElDI7EpWb7jH79ISU7liZnXdERDhjKrX2yGt0EgygCbvG1JyTVqaa" +
                "Mj9viDPuaUSbYYx1O2yUYvhP90rG7EBTNKcNZufKKa6aClLj";

        assertThat(String.valueOf(messageFacade.processIncomingMessage(itemsTest, userTest))).contains(expectedMessage);
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenSendMessageWithZeroLength(){
        itemsTest = "/snd";
        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenZeroLength() {
        itemsTest = "";
        Exception exception = assertThrows(
                MessageFacadeException.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}