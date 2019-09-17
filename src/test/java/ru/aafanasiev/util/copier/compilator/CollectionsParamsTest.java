/**
 *
 */
package ru.aafanasiev.util.copier.compilator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;

import org.testng.annotations.Test;

import ru.aafanasiev.annotations.CopyCollection.CopyMode;

/**
 * @author <a href="mailto:aafanasiev@umail.ru">Andrey Afanasiev</a>
 */
@Test(groups = {"UTILS"}, ignoreMissingDependencies = true)
public class CollectionsParamsTest {

    /**
     * Test method for {@link ru.aafanasiev.util.copier.compilator.CollectionsParams#getLevels()}.
     */
    @Test(enabled = true, ignoreMissingDependencies = true)
    public void testCollectionParams() throws Exception {
        CollectionsParams params = new CollectionsParams();
        assertNull(params.getToClass());
        assertEquals(params.getLevels(), 0);
        assertSame(params.getMode(), CopyMode.COPY_ELEMENTS);
    }

}
