package me.jy.adapter.ext;

import me.jy.adapter.Banner;
import me.jy.adapter.Print;

/**
 * @author jy
 * @date 2018/01/06
 */
public class PrintBanner extends Banner implements Print {

    public PrintBanner(String ad) {
        super(ad);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printStrong() {
        showWithAster();
    }
}
