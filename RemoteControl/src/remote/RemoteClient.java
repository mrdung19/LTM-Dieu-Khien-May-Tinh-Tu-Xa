package remote;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;

public class RemoteClient extends JFrame {
    private JTextField txtHost, txtPort, txtKey;
    private JButton btnConnect, btnCapture, btnClick, btnSendKey;
    private JTextArea txtLog;
    private JLabel lblScreen;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    // Throttling gửi MOVE (ms)
    private long lastMoveSent = 0;
    private final long MOVE_INTERVAL_MS = 40; // ~25 lần/s

    public RemoteClient() {
        setTitle("Remote Client");
        setSize(1000, 760);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtHost = new JTextField("127.0.0.1", 12);
        txtPort = new JTextField("5555", 6);
        txtKey = new JTextField(6);
        btnConnect = new JButton("Kết nối");
        btnCapture = new JButton("Chụp màn hình");
        btnClick = new JButton("Click chuột");
        btnSendKey = new JButton("Gõ phím");

        topPanel.add(new JLabel("IP:"));
        topPanel.add(txtHost);
        topPanel.add(new JLabel("Port:"));
        topPanel.add(txtPort);
        topPanel.add(btnConnect);
        topPanel.add(btnCapture);
        topPanel.add(btnClick);
        topPanel.add(new JLabel("Phím:"));
        topPanel.add(txtKey);
        topPanel.add(btnSendKey);

        add(topPanel, BorderLayout.NORTH);

        lblScreen = new JLabel("Chưa có ảnh", SwingConstants.CENTER);
        lblScreen.setPreferredSize(new Dimension(900, 560));
        JScrollPane imgScroll = new JScrollPane(lblScreen);
        add(imgScroll, BorderLayout.CENTER);

        txtLog = new JTextArea(6, 80);
        txtLog.setEditable(false);
        JScrollPane logScroll = new JScrollPane(txtLog);
        add(logScroll, BorderLayout.SOUTH);

        // Sự kiện nút
        btnConnect.addActionListener(e -> connect());
        btnCapture.addActionListener(e -> sendCommand("CAPTURE"));
        btnClick.addActionListener(e -> sendCommand("CLICK"));
        btnSendKey.addActionListener(e -> {
            String key = txtKey.getText().trim();
            if (!key.isEmpty()) {
                int code = key.toUpperCase().charAt(0);
                sendCommand("KEY " + code);
            }
        });

        // Click hoặc kéo chuột trên lblScreen -> gửi MOVE
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMoveFromMouseEvent(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                handleMoveFromMouseEvent(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                handleMoveFromMouseEvent(e);
            }
        };
        lblScreen.addMouseListener(ma);
        lblScreen.addMouseMotionListener(ma);
    }

    private void handleMoveFromMouseEvent(MouseEvent e) {
        Icon icon = lblScreen.getIcon();
        if (icon != null && icon instanceof ImageIcon) {
            Image img = ((ImageIcon) icon).getImage();

            int lblW = lblScreen.getWidth();
            int lblH = lblScreen.getHeight();

            // Lấy kích thước ảnh thực (để tỉ lệ nếu label scaled)
            int imgW = img.getWidth(null);
            int imgH = img.getHeight(null);

            // Nếu ảnh lớn hơn label hoặc khác tỉ lệ, ta scale theo kích thước label hiển thị
            // Map tọa độ click trên label về toạ độ trên màn hình server:
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenW = screenSize.width;
            int screenH = screenSize.height;

            // Tính tỉ lệ dựa trên label kích thước (hiển thị)
            // (Sử dụng label width/height để tỉ lệ, vì ImageIcon được đặt vào JLabel sẽ co giãn)
            int xOnLabel = e.getX();
            int yOnLabel = e.getY();

            // Map theo tỉ lệ: x = xOnLabel * screenW / lblW
            int x = (int) ((long) xOnLabel * screenW / Math.max(1, lblW));
            int y = (int) ((long) yOnLabel * screenH / Math.max(1, lblH));

            long now = System.currentTimeMillis();
            if (now - lastMoveSent >= MOVE_INTERVAL_MS) {
                sendCommand("MOVE " + x + " " + y);
                lastMoveSent = now;
            }
        }
    }

    private void connect() {
        try {
            String host = txtHost.getText().trim();
            int port = Integer.parseInt(txtPort.getText().trim());

            txtLog.append("Đang kết nối tới " + host + ":" + port + "...\n");
            socket = new Socket(host, port);

            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            txtLog.append("✅ Kết nối thành công!\n");

            // Thread lắng nghe server
            new Thread(this::listenServer).start();
        } catch (Exception ex) {
            txtLog.append("❌ Kết nối thất bại! " + ex.getMessage() + "\n");
        }
    }

    private void listenServer() {
        try {
            while (true) {
                String type = dis.readUTF();
                if ("TEXT".equals(type)) {
                    String msg = dis.readUTF();
                    SwingUtilities.invokeLater(() -> txtLog.append("Server: " + msg + "\n"));
                } else if ("IMAGE".equals(type)) {
                    int length = dis.readInt();
                    byte[] imageBytes = new byte[length];
                    dis.readFully(imageBytes);

                    ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
                    BufferedImage img = ImageIO.read(bais);

                    if (img != null) {
                        ImageIcon icon = new ImageIcon(img);
                        SwingUtilities.invokeLater(() -> {
                            lblScreen.setIcon(icon);
                            lblScreen.setText("");
                        });
                    }
                }
            }
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> txtLog.append("❌ Mất kết nối hoặc lỗi nhận dữ liệu: " + e.getMessage() + "\n"));
            try { if (socket != null) socket.close(); } catch (IOException ignored) {}
        }
    }

    private void sendCommand(String cmd) {
        try {
            if (dos != null) {
                dos.writeUTF(cmd);
                dos.flush();
                SwingUtilities.invokeLater(() -> txtLog.append("Client: " + cmd + "\n"));
            } else {
                SwingUtilities.invokeLater(() -> txtLog.append("⚠️ Chưa kết nối tới server!\n"));
            }
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> txtLog.append("❌ Lỗi gửi lệnh: " + e.getMessage() + "\n"));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RemoteClient c = new RemoteClient();
            c.setVisible(true);
        });
    }
}
