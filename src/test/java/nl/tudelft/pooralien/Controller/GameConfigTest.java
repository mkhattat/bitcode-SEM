package nl.tudelft.pooralien.Controller;

import nl.tu.delft.defpro.api.IDefProAPI;
import nl.tu.delft.defpro.exception.NotExistingVariableException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameConfigTest {
    private IDefProAPI testCfg = mock(IDefProAPI.class);


    @Before
    public void setUp() throws Exception {
        GameConfig.setConfig(testCfg);
    }

    @After
    public void breakDown() throws Exception {
        GameConfig.resetConfig();
    }

    @Test
    public void getBoolean() throws Exception {
        when(testCfg.getBooleanValueOf("bool1")).thenReturn(true);
        assertEquals(true, GameConfig.getBoolean("bool1", true));
    }

    @Test
    public void getBooleanNEVException() throws Exception {
        when(testCfg.getBooleanValueOf("bool2")).thenThrow(new NotExistingVariableException(""));
        assertNull(GameConfig.getBoolean("bool2", null));
    }

    @Test
    public void getBooleanOtherException() throws Exception {
        when(testCfg.getBooleanValueOf("bool3")).thenThrow(new NullPointerException());
        assertNull(GameConfig.getBoolean("bool3", null));
    }

    @Test
    public void getBList() throws Exception {
        List<Boolean> bList = new ArrayList<>();
        List<Boolean> defaultList = new ArrayList<>();
        bList.add(true); defaultList.add(true);
        bList.add(true); defaultList.add(true);
        bList.add(false); defaultList.add(false);

        when(testCfg.getListBoolValueOf("bList1")).thenReturn(bList);
        assertEquals(bList, GameConfig.getBooleanList("bList1", 3, 3, defaultList));
    }

    @Test
    public void getBListNEVException() throws Exception {
        when(testCfg.getListBoolValueOf("bList2")).thenThrow(new NotExistingVariableException(""));
        assertNull(GameConfig.getBooleanList("bList2", 0,0, null));
    }

    @Test
    public void getBListOtherException() throws Exception {
        when(testCfg.getListBoolValueOf("bList3")).thenThrow(new NullPointerException());
        assertNull(GameConfig.getBooleanList("bList3", 0,0, null));
    }

    @Test
    public void getInteger() throws Exception {
        when(testCfg.getIntegerValueOf("int1")).thenReturn(13);
        assertEquals((Integer) 13, GameConfig.getInteger("int1", 13,13,13));
    }

    @Test
    public void getIntegerNEVException() throws Exception {
        when(testCfg.getIntegerValueOf("int2")).thenThrow(new NotExistingVariableException(""));
        assertNull(GameConfig.getInteger("int2", 0, 0, null));
    }

    @Test
    public void getIntegerOtherException() throws Exception {
        when(testCfg.getIntegerValueOf("int3")).thenThrow(new NullPointerException());
        assertNull(GameConfig.getInteger("int3", 0, 0, null));
    }

    @Test
    public void getIList() throws Exception {
        List<Integer> iList = new ArrayList<>();
        iList.add(2);
        iList.add(-51);
        iList.add(17);
        List<Integer> defaultList = Arrays.asList(2,-51,17);
        when(testCfg.getListIntValueOf("iList1")).thenReturn(iList);
        assertEquals(iList, GameConfig.getIntegerList("iList1", 3, 3,
                defaultList, defaultList, defaultList));
    }

    @Test
    public void getIListNEVException() throws Exception {
        when(testCfg.getListIntValueOf("iList2")).thenThrow(new NotExistingVariableException(""));
        List<Integer> zeroList = Arrays.asList(0);
        assertNull(GameConfig.getIntegerList("iList2", 0, 0,
                zeroList, zeroList, null));
    }

    @Test
    public void getIListOtherException() throws Exception {
        when(testCfg.getListIntValueOf("iList3")).thenThrow(new NullPointerException());
        List<Integer> zeroList = Arrays.asList(0);
        assertNull(GameConfig.getIntegerList("iList3", 0, 0, zeroList, zeroList, null));
    }

    @Test
    public void getReal() throws Exception {
        when(testCfg.getRealValueOf("real1")).thenReturn(8.75);
        assertEquals((Double) 8.75, GameConfig.getReal("real1", 8.75, 8.75, 8.75));
    }

    @Test
    public void getRealNEVException() throws Exception {
        when(testCfg.getRealValueOf("real2")).thenThrow(new NotExistingVariableException(""));
        assertNull(GameConfig.getReal("real2", 0.0, 0.0,null));
    }

    @Test
    public void getRealOtherException() throws Exception {
        when(testCfg.getRealValueOf("real3")).thenThrow(new NullPointerException());
        assertNull(GameConfig.getReal("real3", 0.0, 0.0, null));
    }

    @Test
    public void getRList() throws Exception {
        List<Double> dList = new ArrayList<>();
        dList.add(-9.21);
        dList.add(0.64);
        dList.add(7.6);
        List<Double> defaultList = Arrays.asList(-9.21,0.64,7.6);
        when(testCfg.getListRealValueOf("rList1")).thenReturn(dList);
        assertEquals(dList, GameConfig.getRealList("rList1", 3, 3, defaultList, defaultList, defaultList));
    }

    @Test
    public void getRListNEVException() throws Exception {
        when(testCfg.getListRealValueOf("rList2")).thenThrow(new NotExistingVariableException(""));
        List<Double> zeroList = Arrays.asList(0.0,0.0,0.0);
        assertNull(GameConfig.getRealList("rList2", 0,0,zeroList, zeroList, null));
    }

    @Test
    public void getRListOtherException() throws Exception {
        List<Double> zeroList = Arrays.asList(0.0,0.0,0.0);
        when(testCfg.getListRealValueOf("rList3")).thenThrow(new NullPointerException());
        assertNull(GameConfig.getRealList("rList3",0,0,zeroList,zeroList,null));
    }

    @Test
    public void getString() throws Exception {
        when(testCfg.getStringValueOf("string1")).thenReturn("Hello World!");
        assertEquals("Hello World!", GameConfig.getString("string1",12,12,"Hello World!"));
    }

    @Test
    public void getStringNEVException() throws Exception {
        when(testCfg.getStringValueOf("string2")).thenThrow(new NotExistingVariableException(""));
        assertNull(GameConfig.getString("string2", 0,0, null));
    }

    @Test
    public void getStringOtherException() throws Exception {
        when(testCfg.getStringValueOf("string3")).thenThrow(new NullPointerException());
        assertNull(GameConfig.getString("string3", 0,0,null));
    }

    @Test
    public void getSList() throws Exception {
        List<String> sList = new ArrayList<>();
        sList.add("Hello");
        sList.add("World");
        sList.add("!");
        List<String> defaultList = Arrays.asList("Hello", "World", "!");
        List<Integer> defaultLegths = Arrays.asList(5,5,1);
        when(testCfg.getListStringValueOf("sList1")).thenReturn(sList);
        assertEquals(sList, GameConfig.getStringList("sList1", 3, 3,
                defaultLegths, defaultLegths, defaultList));
    }

    @Test
    public void getSListNEVException() throws Exception {
        List<Integer> defaultLegths = Arrays.asList(0,0,0);
        when(testCfg.getListStringValueOf("sList2")).thenThrow(new NotExistingVariableException(""));
        assertNull(GameConfig.getStringList("sList2", 0,0,
                defaultLegths, defaultLegths, null));
    }

    @Test
    public void getSListOtherException() throws Exception {
        List<Integer> defaultLegths = Arrays.asList(0,0,0);
        when(testCfg.getListStringValueOf("sList3")).thenThrow(new NullPointerException());
        assertNull(GameConfig.getStringList("sList3", 0, 0,
                defaultLegths, defaultLegths,null));
    }


}