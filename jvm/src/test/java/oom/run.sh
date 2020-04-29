#java -XX:+PrintCommandLineFlags -version

cd /app/
javac OOMDemo.java
java -Xmx10m OOMDemo
