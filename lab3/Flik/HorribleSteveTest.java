import static org.junit.Assert.*;

import org.junit.Test;

public class HorribleSteveTest {
    @Test
    public void testFilk(){
        boolean a=Flik.isSameNumber(1,1);
        assertTrue(a);
        //boolean b=Flik.isSameNumber(500,500);
        assertTrue(Flik.isSameNumber(500,500));
    }
}
