import glos.S4008324.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeatingTest {

    SeatDatabase seatDatabase = new SeatDatabase();
    WaitingListDatabase waitingListDatabase = new WaitingListDatabase();

    @Test
    public void testRadixSort(){
        int[] unsortedTestArray = {10, 8, 9, 7, 6, 5, 4, 2, 3, 1};
        int[] sortedTestArray = DepartureDatesRadixSort.returnRadixSort(unsortedTestArray, unsortedTestArray.length);
        int[] dupSortedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        List<Integer> UnsortedArray = Arrays.asList(5, 66, 7, 10, 17, 22);

        ArrayList<Integer> DupSortedArray = new ArrayList<>();
        for (int k : dupSortedArray) DupSortedArray.add(k);
        ArrayList<Integer> SortedTestArray = new ArrayList<>();
        for (int j : sortedTestArray) SortedTestArray.add(j);

        Assertions.assertEquals(DupSortedArray, SortedTestArray);
        Assertions.assertNotEquals(UnsortedArray, SortedTestArray);
    }
    @Test
    public void testRadixMaxValue(){
        int[] testArray = {1, 2, 3, 9};
        int max = 9;
        int falseMax = 2;
        Assertions.assertEquals(DepartureDatesRadixSort.getMaxVal(testArray, testArray.length), max);
        Assertions.assertNotEquals(DepartureDatesRadixSort.getMaxVal(testArray, testArray.length), falseMax);
    }
    @Test
    public void testSeatObjects(){
        Assertions.assertNotNull(seatDatabase.createScheduledPassengers("101"));
    }
    @Test
    public void testWaitListObjects(){
        Assertions.assertNotNull(waitingListDatabase.createWaitingListObject("101"));
    }
}
