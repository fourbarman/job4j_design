package ru.job4j.cache;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 13.07.2021.
 */
public class DirFileCacheTest {
    Path tempDir;
    DirFileCache dirFileCache;

    /**
     * Set variables.
     */
    @Before
    public void setVars() {
        try {
            tempDir = Files.createTempDirectory("tempDir");
            dirFileCache = new DirFileCache(tempDir.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Test readFile().
     * When read String from file.
     *
     * @throws IOException IO.
     */
    @Test
    public void readFileTest() throws IOException {
        File file1 = new File(tempDir.toString() + File.separator + "file1.txt");
        String toFile = "1\n2\n3";
        Files.writeString(file1.toPath(), toFile);
        assertThat(dirFileCache.readFile("file1.txt"), is(toFile));
    }

    /**
     * Test get().
     * When cache contains key but GC removed Soft Reference.
     * Than method loads text from file again and returns it.
     *
     * @throws IOException IO.
     */
    @Test
    public void loadTestWhenCacheContainsKeyButGCRemovedSoftReference() throws IOException {
        File file1 = new File(tempDir.toString() + File.separator + "file1.txt");
        String toFile = "1\n2\n3";
        Files.writeString(file1.toPath(), toFile);
        dirFileCache.put(file1.getName(), toFile);
        dirFileCache.cache.get(file1.getName()).clear();
        String result = dirFileCache.get(file1.getName());
        assertThat(result, is(toFile));
    }

    /**
     * Test load().
     * Method should return String from file.
     *
     * @throws IOException IO.
     */
    @Test
    public void loadTestWhenCacheDoesntContainFile() throws IOException {
        File file1 = new File(tempDir.toString() + File.separator + "file1.txt");
        String toFile = "1\n2\n3";
        Files.writeString(file1.toPath(), toFile);
        assertThat(dirFileCache.load(file1.getName()), is(toFile));
    }

    /**
     * Test put().
     * Testing method should make Soft Reference to text from given file.
     *
     * @throws IOException IO.
     */
    @Test
    public void putTestWhenMethodMakesSoftRefToTextFromGivenFile() throws IOException {
        File file1 = new File(tempDir.toString() + File.separator + "file1.txt");
        String toFile = "1\n2\n3";
        Files.writeString(file1.toPath(), toFile);
        dirFileCache.put(file1.getName(), toFile);
        assertTrue(dirFileCache.cache.get(file1.getName()) instanceof SoftReference);
    }
}
