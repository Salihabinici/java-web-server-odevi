import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SimpleWebServer {

    public static void main(String[] args) {
        int port = 1989;
        System.out.println("Sunucu " + port + " portunda baslatiliyor...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Sunucu calisiyor! Tarayicidan http://localhost:1989 adresine girin.");

            while (true) {
                try (Socket client = serverSocket.accept()) {
                    InputStream input = client.getInputStream();
                    byte[] buffer = new byte[1024];
                    input.read(buffer);

                    String htmlContent = """
                            <!DOCTYPE html>
                            <html lang="tr">
                            <head>
                                <meta charset="UTF-8">
                                <title>Web Sunucusu Odevi</title>
                                <style>
                                    body {
                                        font-family: Arial, sans-serif;
                                        background-color: #f4f4f9;
                                        color: #333333;
                                        padding: 40px;
                                    }
                                    .card {
                                        background-color: #ffffff;
                                        padding: 30px;
                                        border-radius: 10px;
                                        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
                                        max-width: 600px;
                                        margin: 0 auto;
                                    }
                                    h1 {
                                        color: #2c3e50;
                                        font-size: 32px;
                                        border-bottom: 2px solid #3498db;
                                        padding-bottom: 10px;
                                    }
                                    h2 {
                                        color: #e74c3c;
                                        font-size: 22px;
                                    }
                                    p {
                                        font-size: 16px;
                                        line-height: 1.6;
                                        color: #555555;
                                    }
                                </style>
                            </head>
                            <body>
                                <div class="card">
                                    <h1>Saliha Binici</h1>
                                    <h2>Öğrenci No: 1250505058</h2>
                                    <p>
                                        Merhaba! Ben Yazılım Mühendisliği öğrencisiyim. Bu web sunucusu uygulaması 
                                        Java Sockets mimarisi kullanılarak herhangi bir 3. parti kütüphane 
                                        kullanılmadan geliştirilmiştir.
                                    </p>
                                </div>
                            </body>
                            </html>
                            """;

                    byte[] bodyBytes = htmlContent.getBytes(StandardCharsets.UTF_8);

                    String header = "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html; charset=UTF-8\r\n" +
                            "Content-Length: " + bodyBytes.length + "\r\n" +
                            "Connection: close\r\n\r\n";

                    OutputStream output = client.getOutputStream();
                    output.write(header.getBytes(StandardCharsets.UTF_8));
                    output.write(bodyBytes);
                    output.flush();
                } catch (Exception e) {
                    System.err.println("Baglanti hatasi: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Sunucu hatasi: " + e.getMessage());
        }
    }
}