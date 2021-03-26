package me.jy.lang.thread.interview;

import java.io.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 读取两个10G的文件, 每个文件存放排好序的id.
 * 找到两个文件里有差异的id, 输出到第三个文件里.
 * 有限制JVM内存.
 * <p>
 * #!/bin/bash
 * for i in {1..50000000}
 * do
 * (( RANDOM%2 == 0 )) && echo $i >> $1
 * done
 *
 * @author jy
 */
public class Diff {

    private static final String DEFAULT_ORIGIN_FILE_NAME = "/origin.txt";
    private static final String DEFAULT_TARGET_FILE_NAME = "/target.txt";
    private static final String DEFAULT_DIFF_FILE_NAME = "diff.txt";

    private static final BlockingDeque<Long> originQueue = new LinkedBlockingDeque<>(1024 * 1024);
    private static final BlockingDeque<Long> targetQueue = new LinkedBlockingDeque<>(1024 * 1024);

    private static volatile boolean originTerminated = false;
    private static volatile boolean targetTerminated = false;

    private static String getOriginFile(String[] args) {
        if (args.length == 0) {
            return DEFAULT_ORIGIN_FILE_NAME;
        }
        return args[0];
    }

    private static String getTargetFile(String[] args) {
        if (args.length < 2) {
            return DEFAULT_TARGET_FILE_NAME;
        }
        return args[1];
    }

    private static String getDiffFile(String[] args) {
        if (args.length < 3) {
            return DEFAULT_DIFF_FILE_NAME;
        }
        return args[2];
    }

    public static void main(String[] args) throws IOException {

        String originFile = getOriginFile(args);
        String targetFile = getTargetFile(args);
        String diffFile = getDiffFile(args);

        new Thread(() -> {
            read(originFile, originQueue);
            originTerminated = true;
        }).start();
        new Thread(() -> {
            read(targetFile, targetQueue);
            targetTerminated = true;
        }).start();

        BufferedWriter writer = new BufferedWriter(new FileWriter(diffFile));
        while (true) {
            if (originQueue.isEmpty() && originTerminated) {
                while (!targetQueue.isEmpty()) {
                    writer.write(String.valueOf(targetQueue.removeFirst()));
                    writer.newLine();
                }
                writer.flush();
                writer.close();
                return;
            }
            if (targetQueue.isEmpty() && targetTerminated) {
                while (!originQueue.isEmpty()) {
                    writer.write(String.valueOf(originQueue.removeFirst()));
                    writer.newLine();
                }
                writer.flush();
                writer.close();
                return;
            }
            Long origin = originQueue.peekFirst();
            Long target = targetQueue.peekFirst();
            if (origin == null || target == null) {
                continue;
            }
            if (origin < target) {
                writer.write(String.valueOf(originQueue.removeFirst()));
                writer.newLine();
            } else if (origin > target) {
                writer.write(String.valueOf(targetQueue.removeFirst()));
                writer.newLine();
            } else {
                originQueue.removeFirst();
                targetQueue.removeFirst();
            }
        }
    }

    private static void read(String file, BlockingQueue<Long> queue) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Diff.class.getResourceAsStream(file)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                queue.put(Long.valueOf(line));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
