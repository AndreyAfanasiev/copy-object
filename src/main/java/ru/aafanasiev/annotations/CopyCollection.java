/**
 *
 */
package ru.aafanasiev.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * Collection copy parameters
 * </p>
 * This annotation set following parameters:
 * <ul>
 * <li>Target collection class</li>
 * <li>Copy mode (copy element or copy collection)</li>
 * <li>Clone collection</li>
 * </ul>
 *
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface CopyCollection {
    /**
     * <p>
     * Copy mode
     * </p>
     * See {@link CopyMode}
     *
     * @return mode
     */
    public CopyMode mode() default CopyMode.COPY_ELEMENTS;

    /**
     * <p>
     * Set target class.
     * </p>
     * Objects with this class will be created during copy elements
     *
     * @return Target class
     */
    public Class<?> targetClass();

    /**
     * <p>
     * Set collections levels for object copy
     * </p>
     * Default value is {@code 0} - don't copy collections for converted coped objects.
     *
     * @return Levels number for copy objects
     */
    public int levels() default 0;

    /**
     * <p>
     * Copy mode
     * </p>
     * This enimeration describe copy modes for collections
     *
     * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
     */
    public enum CopyMode {
        /**
         * <p>
         * Copy collection by elements without convertion
         * </p>
         * Getter copy to getter. Elements copy from source collection to target collection
         */
        COPY_ELEMENTS,

        /**
         * <p>
         * Copy collection by elements with clone elements
         * </p>
         * Getter copy to getter. Each elemnts will be cloned. Collection element must have Clonable interface and
         * public method clone().
         */
        CLONE_ELEMENTS,

        /**
         * <p>
         * Copy collection by elements with convertion
         * </p>
         * Getter copy to getter. Each elemnts will have be cloned. Collection elements must implement Clonable
         * interface and public method clone().
         */
        CONVERT_ELEMENTS,

        /**
         * <p>
         * Copy collection as one part
         * </p>
         * Getter copy to setter. After this operation collection will be have referenses from tow fields.
         */
        COPY_COLLECTION,

        /**
         * <p>
         * Copy collection as one part with collection clone
         * </p>
         * Getter copy to setter. This operation clones copied collection.
         */
        CLONE_COLLECTION;
    }

}
