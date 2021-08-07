package p04_BubbleSortTest;

import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleTest {

    @Test
    public void testSortedElementsInArray(){
        int[] nums = new int[]{ 5 , -2 , 13 , 7 , 1};
        Bubble.sort(nums);
        int[] sortNums = new int[]{-2 , 1 , 5 , 7 , 13};

        assertArrayEquals(sortNums , nums);
    }
}
