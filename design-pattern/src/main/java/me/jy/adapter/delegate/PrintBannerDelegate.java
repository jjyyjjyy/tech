package me.jy.adapter.delegate;

import me.jy.adapter.Banner;
import me.jy.adapter.Print;

/**
 * @author jy
 * @date 2018/01/06
 */
public class PrintBannerDelegate implements Print {

    private final Banner banner;

    public PrintBannerDelegate(Banner banner) {
        this.banner = banner;
    }

    @Override
    public void printWeak() {
        this.banner.showWithParen();
    }

    @Override
    public void printStrong() {
        this.banner.showWithAster();
    }
}
