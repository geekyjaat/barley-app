package com.barley.socket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@WebSocket
public class AppWebSocket {

    private int count = 0;

    // Store sessions if you want to, for example, broadcast a message to all users
    private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

    @OnWebSocketConnect
    public void connected(Session session) {
        System.out.println("Adding session = [" + session + "]");
        sessions.add(session);
    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        sessions.remove(session);
    }

    @OnWebSocketMessage
    public void message(Session session, String message) throws IOException {
        System.out.println("Got: " + message);   // Print message
        System.out.println(session.getRemoteAddress().getHostName());
        System.out.println(session.getRemoteAddress().getAddress().getHostAddress());
        session.getRemote().sendString(message + count); // and send it back
        count++;
    }

    /**
     * Send message to all sessions
     *
     * @param message
     */
    public static void sendToAll(String message) {
        System.out.println("message = [" + message + "]");
        sessions.stream().forEach(s -> {
            try {
                s.getRemote().sendString(message);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });
    }

    /**
     * Send message to specified ip if present in stored sessions
     *
     * @param ip
     * @param message
     */
    public static void sendToIp(String ip, String message) {
        System.out.println("ip = [" + ip + "], message = [" + message + "]");
        sessions.stream()
                .filter(s -> s.getRemoteAddress().getAddress().getHostAddress().equalsIgnoreCase(ip))
                .findFirst()
                .ifPresent(s -> {
                    try {
                        s.getRemote().sendString(message);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                });
    }
}
