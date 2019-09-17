package ru.aafanasiev.util.copier.i18;

import ru.aafanasiev.util.copier.util.MessagesBase;

public class Messages {
    private static final String BUNDLE_NAME = "ru.aafanasiev.util.copier.i18.messages"; //$NON-NLS-1$

    private Messages() {
    }

    public static String getString(String key) {
        return PlaceHolder.MESSAGES.getString(key);
    }

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
