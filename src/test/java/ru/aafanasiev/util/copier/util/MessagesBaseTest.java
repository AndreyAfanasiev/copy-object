/**
 *
 */
package ru.aafanasiev.util.copier.util;

import static org.testng.Assert.assertEquals;

import java.util.MissingResourceException;

import org.testng.annotations.Test;

/**
 * @author aafanasyev
 */
@Test(groups = {"MessagesBaseTest", "UTIL"}, ignoreMissingDependencies = true)
public class MessagesBaseTest {
    private static final String RESOURCE_NAME = "ru.aafanasiev.util.test-resource";
    private static final String UTF_RESOURCE_NAME = "ru.aafanasiev.util.utf8-resource";
    private static final String BAD_RESOURCE_NAME = "ru.aafanasiev.util.bad-resource";
    private static final String ENGLISH_NAME = "english";
    private static final String RUSSIAN_NAME = "russian";
    private static final String ENGLISH_MSG = "Test message";
    private static final String RUSSIAN_MSG = "Тестовое сообщение";

    /**
     * Test method for {@link ru.aafanasiev.util.MessagesBase#MessagesBase(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testMessagesBase01() throws Exception {
        @SuppressWarnings("unused")
        MessagesBase messages = new MessagesBase(RESOURCE_NAME);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.MessagesBase#MessagesBase(java.lang.String)}.
     */
    @SuppressWarnings("unused")
    @Test(enabled = true, ignoreMissingDependencies = true, expectedExceptions = {MissingResourceException.class},
            expectedExceptionsMessageRegExp = ".*ru\\.aafanasiev\\.util\\.bad-resource.*")
    public void testMessagesBase02() throws Exception {
        MessagesBase messages = new MessagesBase(BAD_RESOURCE_NAME);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.MessagesBase#getString(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetString() throws Exception {
        MessagesBase messages = new MessagesBase(RESOURCE_NAME);
        assertEquals(messages.getString(ENGLISH_NAME), ENGLISH_MSG);
        assertEquals(messages.getString(RUSSIAN_NAME), RUSSIAN_MSG);
    }

    /**
     * Test method for {@link ru.aafanasiev.util.MessagesBase#getString(java.lang.String)}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testGetStringUtf8() throws Exception {
        MessagesBase messages = new MessagesBase(UTF_RESOURCE_NAME);
        assertEquals(messages.getStringUtf8(ENGLISH_NAME), ENGLISH_MSG);
        assertEquals(messages.getStringUtf8(RUSSIAN_NAME), RUSSIAN_MSG);
    }
}
