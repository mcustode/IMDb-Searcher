package imdb.searcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImdbParserTitles {

	public List<String> getTitles(String text) {
		List <String> lstTitles = new ArrayList <String>();

		try {
			Document document = Jsoup.connect ("https://www.imdb.com/find?q=" + text + "&s=tt&ref_=fn_al_tt_mr&ttype=ft").get(); //&exact=true
			Elements elements = document.getElementsByClass("result_text");

			for (Element element : elements) {
				lstTitles.add(element.text());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return lstTitles;

	}
}
