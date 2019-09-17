package ru.aafanasiev.util.copier.util;

/**
 * Message class for message localizathion.
 *
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
public class MessagesUtils {
    private static final String BUNDLE_NAME = "ru.aafanasiev.util.messages"; //$NON-NLS-1$

    /**
     * Private constructor for singleton
     */
    private MessagesUtils() {
    }

    /**
     * Get string from IEE-8859-1 codding file with metacharacters
     *
     * @param key Key name
     * @return Key value
     */
    public static String getString(String key) {
        return PlaceHolder.MESSAGES.getString(key);
    }

    /**
     * Get string from UTF-8 codding file without metacharacters
     *
     * @param key Key name
     * @return Key value
     */
    public static String getStringUtf8(String key) {
        return PlaceHolder.MESSAGES.getStringUtf8(key);
    }

    /**
     * Place holder for singleton object.
     *
     * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
     */
    private static class PlaceHolder {
        /** Message resource */
        private static final MessagesBase MESSAGES = new MessagesBase(BUNDLE_NAME);

        /**
         * Private constructor for util class.
         */
        private PlaceHolder() {
        }
    }
}
