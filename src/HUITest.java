import static org.junit.Assert.*;

import org.junit.Test;

public class HUITest {

  private HUI a = new HUI("123");
  
  @Test
  public void testEQUALS() {
    assertTrue(a.Equals(new HUI("123")));
    assertTrue(a.equals(123));
    
    assertFalse(a.Equals(new HUI("122")));
    assertFalse(a.Equals(122));
  }
  
  @Test
  public void testGREATEREQ() {
    assertTrue(a.greaterThanOrEqual(new HUI("123")));
    assertTrue(a.greaterThanOrEqual(123));
    assertTrue(a.greaterThanOrEqual(new HUI("122")));
    assertTrue(a.greaterThanOrEqual(122));
    
    assertFalse(a.greaterThanOrEqual(new HUI("124")));
    assertFalse(a.greaterThanOrEqual(124));
  }
  
  @Test
  public void testLESSEQ() {
    assertTrue(a.lessThanOrEqual(new HUI("123")));
    assertTrue(a.lessThanOrEqual(new HUI("124")));
    
    assertFalse(a.lessThanOrEqual(new HUI("122")));
  }
  
  @Test
  public void testGREATER() {
    assertTrue(a.greaterThan(new HUI("122")));
    assertTrue(a.greaterThan(122));
    
    assertFalse(a.greaterThan(new HUI("124")));
    assertFalse(a.greaterThan(124));
  }
  
  @Test
  public void testLESS() {
    assertTrue(a.lessThan(new HUI("124")));
    assertTrue(a.lessThan(124));
    
    assertFalse(a.lessThan(new HUI("122")));
    assertFalse(a.lessThan(122));
  }
  
  @Test
  public void testAddition() {
    HUI b = a.addition(new HUI("123"));
    assertTrue(b.equals(new HUI("246")));
    
    b = a.addition(123);
    assertTrue(b.equals(246));
  }
  
  @Test
  public void testSubtraction() {
    HUI b = a.subtraction(new HUI("23"));
    assertTrue(b.equals(new HUI("100")));
    
    b = a.subtraction(23);
    assertTrue(b.equals(100));
  }
  
  @Test
  public void testMultiplication() {
    HUI b = a.multiplication(new HUI("11"));
    assertTrue(b.equals(new HUI("1353")));
    
    b = a.multiplication(11);
    assertTrue(b.equals(1353));
  }
  
  @Test
  public void testDivision() {
    HUI a = new HUI("256");
    HUI b = a.division(new HUI("8"));
    assertTrue(b.equals(new HUI("32")));
    
    b = a.division(8);
    assertTrue(b.equals(32));
  }
  
  @Test
  public void testMod() {
    HUI a = new HUI("256");
    HUI b = a.Mod(new HUI("8"));
    assertTrue(b.equals(new HUI("0")));
    
    b = a.Mod(8);
    assertTrue(b.equals(0));
    
    b = a.Mod(new HUI("6"));
    assertTrue(b.equals(new HUI("4")));
    
    b = a.Mod(6);
    assertTrue(b.equals(4));
  }

}