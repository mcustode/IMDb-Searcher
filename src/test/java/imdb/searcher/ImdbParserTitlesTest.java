package imdb.searcher;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.List;

public class ImdbParserTitlesTest {

	@Test 
	public void testgetTitles() {

		ImdbParserTitles parser = new ImdbParserTitles();
		List<String> titles = parser.getTitles(String.valueOf(""));
		assertTrue(titles.isEmpty());

		titles = parser.getTitles(String.valueOf("titanic"));
		assertTrue(!titles.isEmpty());	
	}	
	

}
