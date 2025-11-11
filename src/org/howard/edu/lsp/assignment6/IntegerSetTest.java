package org.howard.edu.lsp.assignment6;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntegerSetTest {
    private IntegerSet setA;
    private IntegerSet setB;
    private IntegerSet emptySet;

    /**
     * Sets up common IntegerSet objects before each test.
     * setA = [1, 2, 3]
     * setB = [3, 4, 5]
     * emptySet = []
     */
    @BeforeEach
    void setUp() {
        setA = new IntegerSet();
        setA.add(1);
        setA.add(2);
        setA.add(3);

        setB = new IntegerSet();
        setB.add(3);
        setB.add(4);
        setB.add(5);

        emptySet = new IntegerSet();
    }

    @Test
    @DisplayName("Test for clear()")
    public void testClear() {
        assertFalse(setA.isEmpty());
        setA.clear();
        assertTrue(setA.isEmpty());
        assertEquals(0, setA.length());
    }

    @Test
    @DisplayName("Test for length()")
    public void testLength() {
        assertEquals(3, setA.length());
        assertEquals(0, emptySet.length());
        setA.add(99);
        assertEquals(4, setA.length());
    }

    @Test
    @DisplayName("Test for equals(Object o) - Equal Sets")
    public void testEquals_EqualSets() {
        IntegerSet setA_copy = new IntegerSet();
        setA_copy.add(1);
        setA_copy.add(2);
        setA_copy.add(3);
        
        assertTrue(setA.equals(setA_copy));
        assertTrue(setA_copy.equals(setA));
        assertTrue(emptySet.equals(new IntegerSet()));
    }
    
    @Test
    @DisplayName("Test for equals(Object o) - Equal Sets Different Order")
    public void testEquals_EqualSetsDifferentOrder() {
        IntegerSet setA_shuffled = new IntegerSet();
        setA_shuffled.add(3);
        setA_shuffled.add(1);
        setA_shuffled.add(2);
        
        assertTrue(setA.equals(setA_shuffled));
        assertTrue(setA_shuffled.equals(setA));
    }

    @Test
    @DisplayName("Test for equals(Object o) - Unequal Sets")
    public void testEquals_UnequalSets() {
        assertFalse(setA.equals(setB));
        assertFalse(setB.equals(setA));
        assertFalse(setA.equals(emptySet));
    }
    
    @Test
    @DisplayName("Test for equals(Object o) - Different Types and Null")
    public void testEquals_DifferentTypesAndNull() {
        assertFalse(setA.equals(null));
        assertFalse(setA.equals("String Object"));
        assertFalse(setA.equals(new java.util.ArrayList<Integer>()));
    }

    @Test
    @DisplayName("Test for contains(int value)")
    public void testContains() {
        assertTrue(setA.contains(1));
        assertTrue(setA.contains(3));
        assertFalse(setA.contains(99));
        assertFalse(emptySet.contains(1));
    }

    @Test
    @DisplayName("Test for largest() - Normal Case")
    public void testLargest() {
        assertEquals(3, setA.largest());
        assertEquals(5, setB.largest());
    }

    @Test
    @DisplayName("Test for largest() - Exception on Empty Set")
    public void testLargest_ThrowsExceptionOnEmpty() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            emptySet.largest();
        });
        assertEquals("Cannot find largest element in an empty set.", exception.getMessage());
    }

    @Test
    @DisplayName("Test for smallest() - Normal Case")
    public void testSmallest() {
        assertEquals(1, setA.smallest());
        assertEquals(3, setB.smallest());
    }

    @Test
    @DisplayName("Test for smallest() - Exception on Empty Set")
    public void testSmallest_ThrowsExceptionOnEmpty() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            emptySet.smallest();
        });
        assertEquals("Cannot find smallest element in an empty set.", exception.getMessage());
    }

    @Test
    @DisplayName("Test for add(int item) - Adding New Item")
    public void testAdd_NewItem() {
        setA.add(4);
        assertTrue(setA.contains(4));
        assertEquals(4, setA.length());
    }

    @Test
    @DisplayName("Test for add(int item) - Adding Duplicate Item")
    public void testAdd_DuplicateItem() {
        setA.add(2); // Already contains 2
        assertEquals(3, setA.length());
        assertEquals("[1, 2, 3]", setA.toString()); // Assumes ArrayList insertion order
    }

    @Test
    @DisplayName("Test for remove(int item) - Removing Existing Item")
    public void testRemove_ExistingItem() {
        setA.remove(2);
        assertFalse(setA.contains(2));
        assertEquals(2, setA.length());
    }

    @Test
    @DisplayName("Test for remove(int item) - Removing Non-Existent Item")
    public void testRemove_NonExistentItem() {
        setA.remove(99);
        assertEquals(3, setA.length());
    }

    @Test
    @DisplayName("Test for union(IntegerSet other) - Standard Case")
    public void testUnion() {
        setA.union(setB); // setA = [1, 2, 3] U [3, 4, 5]
        
        IntegerSet expected = new IntegerSet();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        
        assertEquals(expected, setA);
        assertEquals(5, setA.length());
    }

    @Test
    @DisplayName("Test for union(IntegerSet other) - Union with Empty")
    public void testUnion_WithEmpty() {
        IntegerSet setA_copy = new IntegerSet();
        setA_copy.add(1); setA_copy.add(2); setA_copy.add(3);

        setA.union(emptySet); // setA U []
        assertEquals(setA_copy, setA); // Should be unchanged
        
        emptySet.union(setA); // [] U setA
        assertEquals(setA_copy, emptySet); // Should become a copy of setA
    }

    @Test
    @DisplayName("Test for intersect(IntegerSet other) - Standard Case")
    public void testIntersect() {
        setA.intersect(setB); // setA = [1, 2, 3] ∩ [3, 4, 5]
        
        IntegerSet expected = new IntegerSet();
        expected.add(3);
        
        assertEquals(expected, setA);
        assertEquals(1, setA.length());
    }

    @Test
    @DisplayName("Test for intersect(IntegerSet other) - No Intersection")
    public void testIntersect_NoIntersection() {
        IntegerSet setC = new IntegerSet();
        setC.add(10);
        setC.add(11);
        
        setA.intersect(setC); // [1, 2, 3] ∩ [10, 11]
        
        assertTrue(setA.isEmpty());
        assertEquals(emptySet, setA);
    }
    
    @Test
    @DisplayName("Test for intersect(IntegerSet other) - Intersect with Self")
    public void testIntersect_WithSelf() {
        IntegerSet setA_copy = new IntegerSet();
        setA_copy.add(1); setA_copy.add(2); setA_copy.add(3);

        setA.intersect(setA); // setA ∩ setA
        assertEquals(setA_copy, setA); // Should be unchanged
    }

    @Test
    @DisplayName("Test for diff(IntegerSet other) - Standard Case (this \\ other)")
    public void testDiff() {
        setA.diff(setB); // setA = [1, 2, 3] \ [3, 4, 5]
        
        IntegerSet expected = new IntegerSet();
        expected.add(1);
        expected.add(2);
        
        assertEquals(expected, setA);
        assertEquals(2, setA.length());
    }

    @Test
    @DisplayName("Test for diff(IntegerSet other) - Diff with Self")
    public void testDiff_WithSelf() {
        setA.diff(setA); // setA \ setA
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("Test for complement(IntegerSet other) - Standard Case (other \\ this)")
    public void testComplement() {
        // Note: complement modifies 'this' (setA) to become (other \ this)
        setA.complement(setB); // setA becomes [3, 4, 5] \ [1, 2, 3]
        
        IntegerSet expected = new IntegerSet();
        expected.add(4);
        expected.add(5);
        
        assertEquals(expected, setA);
        assertEquals(2, setA.length());
    }

    @Test
    @DisplayName("Test for complement(IntegerSet other) - Complement with Self")
    public void testComplement_WithSelf() {
        // setA becomes setA \ setA
        setA.complement(setA);
        assertTrue(setA.isEmpty());
    }
    
    @Test
    @DisplayName("Test for complement(IntegerSet other) - Empty 'this' (other \\ empty)")
    public void testComplement_EmptyThis() {
        // emptySet becomes setA \ emptySet
        emptySet.complement(setA);
        assertEquals(setA, emptySet); // Should become a copy of setA
    }
    
    @Test
    @DisplayName("Test for complement(IntegerSet other) - Empty 'other' (empty \\ this)")
    public void testComplement_EmptyOther() {
        // setA becomes emptySet \ setA
        setA.complement(emptySet);
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("Test for isEmpty()")
    public void testIsEmpty() {
        assertTrue(emptySet.isEmpty());
        assertFalse(setA.isEmpty());
        setA.clear();
        assertTrue(setA.isEmpty());
    }

    @Test
    @DisplayName("Test for toString()")
    public void testToString() {
        // Note: This test relies on the insertion order from setUp()
        // and the fact that ArrayList.toString() is order-preserving.
        assertEquals("[1, 2, 3]", setA.toString());
        assertEquals("[3, 4, 5]", setB.toString());
        assertEquals("[]", emptySet.toString());
        
        IntegerSet single = new IntegerSet();
        single.add(99);
        assertEquals("[99]", single.toString());
    }
    
}
