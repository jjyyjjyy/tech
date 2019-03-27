package me.jy.lc;

import me.jy.lc.extension.Problem;

import java.util.Objects;

@Problem(26)
public class RemoveDuplicates {

    public int removeDuplicates0(int[] nums) {

        int length = nums.length;

        if (length < 2) {
            return length;
        }

        int repeatNumber = 0;
        for (int i = 1; i < (length - repeatNumber); i++) {
            int prevElement = nums[i - 1];
            int currentElement = nums[i];
            if (Objects.equals(prevElement, currentElement)) {
                System.arraycopy(nums, i + 1, nums, i, length - i - 1);
                nums[length - 1 - (repeatNumber++)] = 0;
                i--;
            }
        }
        return length - repeatNumber;
    }

    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length < 2) {
            return length;
        }
        int i = 0;
        int prevElement = nums[i];
        for (int j = 1; j < length; j++) {
            int currentElement = nums[j];
            if (prevElement != currentElement) {
                prevElement = nums[++i] = currentElement;
            }
        }

        return i + 1;
    }


}
