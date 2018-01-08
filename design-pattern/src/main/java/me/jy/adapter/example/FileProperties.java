package me.jy.adapter.example;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.Properties;

/**
 * @author jy
 * @date 2018/01/08
 */
public class FileProperties implements FileIO {

    private BufferedReader reader;

    private Properties properties = new Properties();

    public FileProperties() {
    }

    @Override
    public void readFromFile(String fileName) throws IOException {
        this.reader = Files.newBufferedReader(Paths.get(fileName));
        setProperties();
    }

    @Override
    public void writeToFile(String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        StringBuilder body = new StringBuilder("#written by")
                .append(getClass().getSimpleName())
                .append("\r\n#")
                .append(ZonedDateTime.now().toString());
        this.properties.forEach((k, v) -> body.append("\r\n").append(k).append("=").append(v));
        fileOutputStream.write(body.toString().getBytes(Charset.defaultCharset()));
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @Override
    public void setValue(String key, Object value) {
        this.properties.put(key, value);
    }

    @Override
    public Object getValue(String key) {
        return this.properties.get(key);
    }

    private void setProperties() throws IOException {
        if (this.reader == null) {
            throw new IllegalStateException("null file");
        }
        String kv;
        while ((kv = reader.readLine()) != null) {
            String[] split = kv.split("=");
            this.properties.put(split[0], split[1]);
        }
    }
}
