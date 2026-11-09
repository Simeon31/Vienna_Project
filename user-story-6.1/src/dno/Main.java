package dno;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    private static OptimizationService service = new OptimizationService();
    private static OptimizationRun run = service.loadRun("RUN-2026-009");

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", exchange -> handle(exchange));
        server.start();
        System.out.println("Server started: http://localhost:8080");
    }

    private static void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();

        if (path.equals("/") && method.equals("GET")) {
            byte[] page = PageBuilder.buildPage(run).getBytes(StandardCharsets.UTF_8);
            send(exchange, 200, "text/html; charset=UTF-8", page);
        } else if (path.equals("/style.css")) {
            sendFile(exchange, "style.css", "text/css; charset=UTF-8");
        } else {
            send(exchange, 404, "text/plain; charset=UTF-8", "Not found".getBytes(StandardCharsets.UTF_8));
        }
    }

    private static void sendFile(HttpExchange exchange, String name, String type) throws IOException {
        Path file = Path.of("web", name);
        if (!Files.exists(file)) {
            send(exchange, 404, "text/plain; charset=UTF-8", "File not found".getBytes(StandardCharsets.UTF_8));
            return;
        }
        send(exchange, 200, type, Files.readAllBytes(file));
    }

    private static void send(HttpExchange exchange, int status, String type, byte[] body) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", type);
        exchange.sendResponseHeaders(status, body.length);
        OutputStream out = exchange.getResponseBody();
        out.write(body);
        out.close();
    }
}
