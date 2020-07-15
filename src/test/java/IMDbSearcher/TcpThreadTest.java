package IMDbSearcher;

import org.junit.Test;
import static org.junit.Assert.*;

public class TcpThreadTest {

    @Test public void testprocessQuery() {
        TcpThread classUnderTest = new TcpThread(null);
        assertEquals(classUnderTest.processQuery("Ab"), "ERRO:Escreva pelo menos 3 caracteres");
    }
}
