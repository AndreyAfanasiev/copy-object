package ru.aafanasiev.util.copier.util;

import java.nio.charset.Charset;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Base class for i18 Massge bundle
 *
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
public class MessagesBase {
    private final ResourceBundle resourceBundle;

    /**
     * Create message bundle
     *
     * @param bundelName Bundle name with a fully qualified way without {@code "properties"} suffix
     */
    public MessagesBase(String bundelName) {
        resourceBundle = ResourceBundle.getBundle(bundelName);
    }

    /**
     * Get string from IEE-8859-1 codding file with metacharacters
     *
     * @param key Key name
     * @return Key value
     */
    public String getString(String key) {
        try {
            return resourceBundle.getString(key);
        } catch(MissingResourceException ex) { // NOSONAR
            return '!' + key + '!' + ex.getMessage();
        }
    }

    /**
     * Get string from UTF-8 codding file without metacharacters
     *
     * @param key Key name
     * @return Key value
     */
    public String getStringUtf8(String key) {
        try {
            String str = resourceBundle.getString(key);
            byte[] bytes;
            bytes = str.getBytes(Charset.forName("ISO-8859-1"));
            return new String(bytes, Charset.forName("UTF-8"));
        } catch(MissingResourceException ex) { // NOSONAR
            return '!' + key + '!' + ex.getMessage();
        }
    }
}
