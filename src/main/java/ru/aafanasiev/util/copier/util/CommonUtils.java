/**
 *
 */
package ru.aafanasiev.util.copier.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
public class CommonUtils {
    /** Logger */
    private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);

    /**
     *
     */
    private CommonUtils() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Копия заданного объекта посредством сериализации-десереализации (в частности, глубокое копирование иерархических
     * структур)
     *
     * @param object копируемый объект
     * @param <T> тип копируемого объекта
     * @return копия
     */
    @SuppressWarnings("unchecked")
    public static <T> T deepCopy(T object) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return (T) objInputStream.readObject();
        } catch(IOException | ClassNotFoundException e) {
            log.error("Error while deepCopy object: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
