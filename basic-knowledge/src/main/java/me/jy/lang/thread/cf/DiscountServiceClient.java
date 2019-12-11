package me.jy.lang.thread.cf;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. get price
 * 2. cal discounted price
 * 3. print
 *
 * @author jy
 */
public class DiscountServiceClient {

    private static final List<DiscountShop> DISCOUNT_SHOPS = Stream.of("OPPO", "XIAO_MI", "IPHONE", "NOKIA", "HUA_WEI", "CHUI_ZI", "MEI_ZU", "ASUS", "INTEL")
        .map(DiscountShop::new).collect(Collectors.toList());

    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(Math.min(DISCOUNT_SHOPS.size(), 20), r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    private static void getPricesSync() {
        DISCOUNT_SHOPS
            .stream()
            .map(DiscountShop::getPrice)
            .map(Quote::parse)
            .map(DiscountService::getDiscountPrice)
            .forEach(System.out::println);
    }

    private static void getPricesCF() {
        List<CompletableFuture<Double>> futureList =
            DISCOUNT_SHOPS
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(shop::getPrice, THREAD_POOL))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> DiscountService.getDiscountPrice(quote), THREAD_POOL)))
                .collect(Collectors.toList());
        futureList
            .stream()
            .map(CompletableFuture::join)
            .forEach(System.out::println);
    }

    public static void main(String[] args) {
        long start = System.nanoTime();

        // 9 tasks: 18017 2017
//        getPricesSync();
        getPricesCF();
        long end = System.nanoTime();
        System.out.println("cost time : " + (end - start) / 1_000_000);
    }

}
