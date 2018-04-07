package me.jy.lang.thread.cf;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 1. get price
 * 2. print
 * <p>
 * 2C4T
 * N(thread number) = N(threads) * U(cpu usage) * (Wait_time/Compute_time)
 *
 * @author jy
 */
public class ApiClient {

    private static final List<Shop> SHOPS = Stream.of("OPPO", "XIAO_MI", "IPHONE", "NOKIA", "HUA_WEI", "CHUI_ZI", "MEI_ZU", "ASUS", "INTEL")
            .map(Shop::new).collect(Collectors.toList());

    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(Math.min(SHOPS.size(), 20), r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    private static void getPricesSync() {
        SHOPS
                .forEach(ApiClient::printPrice);
    }

    private static void getPricesParallel() {
        SHOPS
                .parallelStream()
                .forEach(ApiClient::printPrice);
    }

    private static void getPriceCF() {
        List<CompletableFuture<String>> futures = SHOPS
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s shop price is %.2f", shop.getName(), shop.getPrice()), THREAD_POOL))
                .collect(Collectors.toList());
        futures
                .stream()
                .map(CompletableFuture::join)
                .forEach(System.out::println);
    }

    private static void printPrice(Shop shop) {
        System.out.println(String.format("%s shop price is %.2f", shop.getName(), shop.getPrice()));
    }


    public static void main(String[] args) {
        long start = System.nanoTime();

        // 4 tasks: 4019 1027 1018
        // 5 tasks: 5015 2019 1022
        // 9 tasks: 9019 3019 1018
//        getPricesSync();
//        getPricesParallel();
        getPriceCF();
        long end = System.nanoTime();
        System.out.println("cost time : " + (end - start) / 1_000_000);
    }
}
