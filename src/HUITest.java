/**
 * Authors: Byambasuren Gansukh (Ben), bgansu3
 *          Wieheng Ruan (Alex), waruan2
 *          
 * HUITest.java: Class that tests HUI operations.
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class HUITest {

  private HUI a = new HUI("123");
  
  @Test
  public void testHUIequals() {
    assertTrue(a.HUIequals(new HUI("123")));
    assertTrue(a.HUIequals(123));
    
    assertFalse(a.HUIequals(new HUI("122")));
    assertFalse(a.HUIequals(122));
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
    assertTrue(b.HUIequals(new HUI("246")));
    
    b = a.addition(123);
    assertTrue(b.HUIequals(246));
  }
  
  @Test
  public void testSubtraction() {
    HUI b = a.subtraction(new HUI("23"));
    assertTrue(b.HUIequals(new HUI("100")));
    
    b = a.subtraction(23);
    assertTrue(b.HUIequals(100));
  }
  
  @Test
  public void testMultiplication() {
    HUI b = a.multiplication(new HUI("11"));
    assertTrue(b.HUIequals(new HUI("1353")));
    
    b = a.multiplication(11);
    assertTrue(b.HUIequals(1353));
    
    HUI c = new HUI("3");
    HUI d = new HUI("5");
    
    assertTrue(c.multiplication(d).HUIequals(15));
  }
  
  @Test
  public void testDivision() {
    HUI a = new HUI("256");
    HUI b = a.division(new HUI("8"));
    assertTrue(b.HUIequals(new HUI("32")));
    
    b = a.division(8);
    assertTrue(b.HUIequals(32));
  }
  
  @Test
  public void testMod() {
    HUI a = new HUI("256");
    HUI b = a.Mod(new HUI("8"));
    b.R_display();
    assertTrue(b.HUIequals(new HUI("0")));
    
    b = a.Mod(8);
    assertTrue(b.HUIequals(0));
    
    b = a.Mod(new HUI("6"));
    assertTrue(b.HUIequals(new HUI("4")));
    
    b = a.Mod(6);
    assertTrue(b.HUIequals(4));
  }

}
