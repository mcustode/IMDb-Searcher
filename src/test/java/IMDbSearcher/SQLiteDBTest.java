package IMDbSearcher;

import org.junit.Test;
import static org.junit.Assert.*;

public class SQLiteDBTest {

    @Test public void testSearchTitles() {
        SQLiteDB classUnderTest = new SQLiteDB();
        assertTrue(classUnderTest.searchTitles("Tis") != null);
    }
}
