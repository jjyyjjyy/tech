package me.jy._14_template_method;

import java.util.Arrays;

/**
 * @author jy
 */
public abstract class Sort {

    public void sortAndPrint(int[] array) {
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    protected abstract void sort(int[] array);
}
