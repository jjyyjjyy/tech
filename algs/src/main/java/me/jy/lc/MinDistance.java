package me.jy.lc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 寻找最近点对距离
 * 将所有点按照x坐标分为两半, 分别求左右区间的最小距离d, 再和中间区间 [mid-d,mid+d]的最短距离比较得到所有点的最小距离.
 *
 * @author jy
 */
public class MinDistance {

    private MinDistance() {
    }

    public static double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2d) + Math.pow(p1.y - p2.y, 2d));
    }

    public static double getMinDistance(List<Point> points) {
        points.sort(Point.X_COMPARATOR);
        return getMinDistance(points, 0, points.size() - 1);
    }

    private static double getMinDistance(List<Point> points, int start, int end) {

        if (start >= end) {
            return Double.MAX_VALUE;
        }
        if (end - start == 1) {
            return getDistance(points.get(start), points.get(end));
        }

        int mid = start + (end - start) / 2;
        // 中点
        double midX = points.get(mid).x;

        // 左右区间最小距离
        double minLeft = getMinDistance(points, start, mid);
        double minRight = getMinDistance(points, mid + 1, end);
        double minDistance = Math.min(minLeft, minRight);


        double minDistanceCopy = minDistance;
        // 中间区域的点
        List<Point> midPoints = points.stream().filter(p -> Math.abs(p.x - midX) < minDistanceCopy)
            .sorted(Point.Y_COMPARATOR)
            .collect(Collectors.toList());

        for (int i = 0; i < midPoints.size(); i++) {
            for (int j = i + 1; j < midPoints.size(); j++) {
                Point p1 = midPoints.get(i);
                Point p2 = midPoints.get(j);
                if (p2.y - p1.y > minDistance) {
                    break;
                }
                double distance = getDistance(p1, p2);
                minDistance = Math.min(minDistance, distance);
            }
        }

        return minDistance;
    }

    @Data
    @AllArgsConstructor
    public static class Point {

        private static final PointXComparator X_COMPARATOR = new PointXComparator();
        private static final PointYComparator Y_COMPARATOR = new PointYComparator();

        private double x;
        private double y;

        public static class PointXComparator implements Comparator<Point> {
            @Override
            public int compare(Point o1, Point o2) {
                return (int) (o1.x - o2.x);
            }
        }

        public static class PointYComparator implements Comparator<Point> {
            @Override
            public int compare(Point o1, Point o2) {
                return (int) (o1.y - o2.y);
            }
        }

    }
}
