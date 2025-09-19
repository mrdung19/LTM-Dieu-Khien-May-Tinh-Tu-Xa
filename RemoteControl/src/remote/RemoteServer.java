package remote;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RemoteServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Robot robot;

    public RemoteServer(int port) throws Exception {
        // Tự tìm port rảnh nếu bị bận
        while (true) {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("✅ Server chạy trên cổng: " + port);
                break;
            } catch (BindException e) {
                System.out.println("⚠️ Port " + port + " đã bận, thử port " + (port + 1));
                port++;
            }
        }

        robot = new Robot();
        System.out.println("Chờ client kết nối...");
        clientSocket = serverSocket.accept();
        System.out.println("Client đã kết nối: " + clientSocket.getInetAddress());

        dis = new DataInputStream(clientSocket.getInputStream());
        dos = new DataOutputStream(clientSocket.getOutputStream());

        listenCommands();
    }

    private void listenCommands() {
        try {
            while (true) {
                String command = dis.readUTF(); // nhận lệnh từ client
                System.out.println("Client: " + command);

                if (command.startsWith("CAPTURE")) {
                    sendScreenshotAndSave();
                } else if (command.startsWith("MOVE")) {
                    String[] parts = command.split(" ");
                    if (parts.length >= 3) {
                        int x = Integer.parseInt(parts[1]);
                        int y = Integer.parseInt(parts[2]);
                        robot.mouseMove(x, y);
                        sendText("Mouse moved to " + x + "," + y);
                    }
                } else if (command.startsWith("CLICK")) {
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    sendText("Mouse clicked");
                } else if (command.startsWith("KEY")) {
                    String[] parts = command.split(" ");
                    if (parts.length >= 2) {
                        int keyCode = Integer.parseInt(parts[1]);
                        robot.keyPress(keyCode);
                        robot.keyRelease(keyCode);
                        sendText("Key pressed: " + (char) keyCode);
                    }
                } else {
                    sendText("Unknown command: " + command);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Client ngắt kết nối hoặc lỗi: " + e.getMessage());
            try { if (clientSocket != null) clientSocket.close(); } catch (IOException ignored) {}
        }
    }

    // Gửi text thông báo tới client
    private void sendText(String msg) throws IOException {
        dos.writeUTF("TEXT");
        dos.writeUTF(msg);
        dos.flush();
    }

    // Chụp màn hình, lưu file và gửi ảnh về client
    private void sendScreenshotAndSave() {
        try {
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screen = robot.createScreenCapture(screenRect);

            // Tạo thư mục screenshots nếu chưa tồn tại
            File dir = new File("screenshots");
            if (!dir.exists()) dir.mkdirs();

            // Tên file: screen_YYYYMMdd_HHmmssSSS.png
            String ts = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
            String fileName = "screenshots/screen_" + ts + ".png";
            File outFile = new File(fileName);

            // Lưu file
            ImageIO.write(screen, "png", outFile);
            System.out.println("Saved screenshot: " + outFile.getAbsolutePath());

            // Gửi ảnh về client
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(screen, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            dos.writeUTF("IMAGE");
            dos.writeInt(imageBytes.length);
            dos.write(imageBytes);
            dos.flush();

            // Gửi text thông báo kèm tên file
            sendText("Screenshot saved: " + fileName + " (" + imageBytes.length + " bytes)");
        } catch (Exception e) {
            try { sendText("Lỗi khi chụp lưu ảnh: " + e.getMessage()); } catch (IOException ignored) {}
            System.out.println("Lỗi gửi/saving screenshot: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            int defaultPort = 5555;
            new RemoteServer(defaultPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
