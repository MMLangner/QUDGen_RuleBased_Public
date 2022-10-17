package qudgen_system;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class SimpleNLGInterfaceTest
{
    @Test
    public void testSimpleSVO()
    {
        SimpleNLGInterface nlg = new SimpleNLGInterface();
        String result = nlg.simpleSVO("der hund", "helfen", "mensch");
        assertEquals(result, "Der Hund hilft Menschen.");
    }
}
