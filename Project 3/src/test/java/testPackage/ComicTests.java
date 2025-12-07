package testPackage;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.comic_collection_app.model.Comic;

class ComicTest {

    @Test
    void testValidComicCreation() {
        Comic comic = new Comic("Spider-Man", 1);
        assertEquals("Spider-Man", comic.getTitle());
        assertEquals(1, comic.getIssueNumber());
    }

    @Test
    void testComicCreationWithEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Comic("", 1);
        });
    }

    @Test
    void testComicCreationWithNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Comic(null, 1);
        });
    }

    @Test
    void testComicCreationWithInvalidIssueNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Comic("Batman", 0);
        });
    }

    @Test
    void testToString() {
        Comic comic = new Comic("Hulk", 3);
        assertEquals("Hulk #3", comic.toString());
    }

    @Test
    void testEqualsSameValues() {
        Comic c1 = new Comic("Batman", 5);
        Comic c2 = new Comic("Batman", 5);
        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testEqualsDifferentTitle() {
        Comic c1 = new Comic("Batman", 5);
        Comic c2 = new Comic("Superman", 5);
        assertNotEquals(c1, c2);
    }

    @Test
    void testEqualsDifferentIssueNumber() {
        Comic c1 = new Comic("Batman", 1);
        Comic c2 = new Comic("Batman", 2);
        assertNotEquals(c1, c2);
    }
}