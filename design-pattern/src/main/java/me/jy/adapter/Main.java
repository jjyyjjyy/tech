package me.jy.adapter;

import me.jy.adapter.delegate.PrintBannerDelegate;
import me.jy.adapter.ext.PrintBanner;

/**
 * @author jy
 * @date 2018/01/06
 */
public class Main {

    public static void main(String[] args) {

        Print print = new PrintBanner("Extend style");
        print.printStrong();
        print.printWeak();


        Print printDe = new PrintBannerDelegate(new Banner("Delegate Style"));
        printDe.printStrong();
        printDe.printWeak();
    }
}
