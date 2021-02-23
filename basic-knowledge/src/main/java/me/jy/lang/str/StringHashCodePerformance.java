package me.jy.lang.str;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author jy
 */
@Slf4j
public class StringHashCodePerformance extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    static char getChar(byte[] val, int index) {
        index <<= 1;
        return (char) (((val[index++] & 0xff) << 8) | ((val[index] & 0xff)));
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("hashCode()乘积系数碰撞率图");

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        xAxis.setLabel("哈希系数");
        yAxis.setLabel("碰撞率(%)");

        XYChart.Series<String, Number> xSeries = new XYChart.Series<>();

        String[] words = HttpClient.newHttpClient()
            .send(HttpRequest.newBuilder().GET().uri(URI.create("https://raw.githubusercontent.com/skywind3000/ECDICT/master/ecdict.csv")).build(), HttpResponse.BodyHandlers.ofString())
            .body()
            .split(System.lineSeparator());

        long total = words.length;
        log.info("Loaded {} words", total);

        Stream.of(2, 3, 5, 7, 17, 31, 32, 33, 39, 41, 199)
            .forEach(multiplier -> {
                long collision = total - Arrays.stream(words).parallel().map(w -> computeHashCode(multiplier, w)).distinct().count();
                log.info("multiplier {} collision: {}", multiplier, collision);
                xSeries.getData().add(new XYChart.Data<>(String.valueOf(multiplier), collision));
            });

        Scene scene = new Scene(bc, 800, 600);
        bc.getData().add(xSeries);
        stage.setScene(scene);
        stage.show();
    }

    private int computeHashCode(int multiplier, String chars) {
        byte[] value = chars.getBytes();
        int h = 0;
        int length = value.length / 2;
        for (int i = 0; i < length; i++) {
            // h * 31 == (h << 5) - h
            h = multiplier * h + getChar(value, i);
        }
        return h;
    }
}
