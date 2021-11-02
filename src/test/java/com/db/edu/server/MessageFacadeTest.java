package com.db.edu.server;

import com.db.edu.server.message.HistoryMessage;
import com.db.edu.server.message.Message;
import com.db.edu.server.message.SendMessage;
import com.db.edu.server.message.SetUserNameMessage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MessageFacadeTest {
    @Test
    void shouldSendMessage() throws MessageFacadeError {
        String userTest = "testClient";
        String itemsTest = "/snd This is a test message!";
        MessageFacade messageFacade = new MessageFacade();

        Message result = messageFacade.processIncomingMessage(itemsTest, userTest);

        Assertions.assertEquals(SendMessage.class, result.getClass());
    }

    @Test
    void shouldSetUserNameMessage() throws MessageFacadeError {
        String userTest = "testClient";
        String itemsTest = "/chid This is a test message!";
        MessageFacade messageFacade = new MessageFacade();

        Message result = messageFacade.processIncomingMessage(itemsTest, userTest);

        Assertions.assertEquals(SetUserNameMessage.class, result.getClass());
    }

    @Test
    void shouldReturnHistoryMessage() throws MessageFacadeError {
        String userTest = "testClient";
        String itemsTest = "/hist";
        MessageFacade messageFacade = new MessageFacade();

        Message result = messageFacade.processIncomingMessage(itemsTest, userTest);

        Assertions.assertEquals(HistoryMessage.class, result.getClass());
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenWrongHistoryMessage() {
        String userTest = "testClient";
        String itemsTest = "/hist This is a test message";
        MessageFacade messageFacade = new MessageFacade();

        Exception exception = assertThrows(
                MessageFacadeError.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenSendMessageWithMaxLength() {
        String userTest = "testClient";
        String itemsTest = "/snd Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales tellus est, eu eleifend nisi euismod a. Cras volutpat sollicitudin interdum. Sed!!!";
        MessageFacade messageFacade = new MessageFacade();

        Exception exception = assertThrows(
                MessageFacadeError.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User message length > 150";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldThrowMessageFacadeErrorWhenWrongCommand() {
        String userTest = "testClient";
        String itemsTest = "/send This is a test message";
        MessageFacade messageFacade = new MessageFacade();

        Exception exception = assertThrows(
                MessageFacadeError.class,
                () -> messageFacade.processIncomingMessage(itemsTest, userTest));

        String expectedMessage = "User wrong command";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
