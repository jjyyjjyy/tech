package me.jy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author jy
 */
public class NioServer {

    private final int port;

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    public NioServer(int port) {
        this.port = port;
        init();
    }

    public static void main(String[] args) throws Exception {
        NioServer server = new NioServer(8800);
        Selector selector = server.getSelector();
        while (true) {
            if (!selector.isOpen()) {
                break;
            }
            if (selector.select(3000) == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectedKey = iterator.next();
                iterator.remove();
                if (selectedKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectedKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (selectedKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectedKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(100);
                    int read = channel.read(buffer);
                    if (read == -1) {
                        System.out.println("[Server] " + channel.getRemoteAddress() + " closed...");
                        channel.close();
                    } else if (read > 0) {
                        String request = new String(buffer.array()).trim();
                        System.out.println("[Server] " + request);
                        if ("SHUTDOWN".equals(request)) {
                            channel.close();
                            server.close();
                            break;
                        }
                        selectedKey.interestOps(SelectionKey.OP_WRITE);
                        selectedKey.attach("Hello From " + channel.getLocalAddress());
                    }
                }
                if (selectedKey.isValid() && selectedKey.isWritable()) {
                    SocketChannel channel = (SocketChannel) selectedKey.channel();
                    channel.write(ByteBuffer.wrap(selectedKey.attachment().toString().getBytes()));
                    selectedKey.interestOps(SelectionKey.OP_READ);
                }
            }
        }
    }

    public void close() {
        try {
            selector.close();
            serverSocketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void init() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 100);
            selector = Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Selector getSelector() {
        return selector;
    }

    public ServerSocketChannel getServerSocketChannel() {
        return serverSocketChannel;
    }

    public int getPort() {
        return port;
    }
}
