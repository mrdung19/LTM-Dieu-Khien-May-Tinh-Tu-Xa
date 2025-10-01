<h2 align="center">
  <a href="https://dainam.edu.vn/vi/khoa-cong-nghe-thong-tin">
  🎓 Faculty of Information Technology (DaiNam University)
  </a>
</h2>

<h2 align="center">
  ỨNG DỤNG ĐIỀU KHIỂN MÁY TÍNH TỪ XA
</h2>

<div align="center">
  <p align="center">
    <img src="doc/aiotlab_logo.png" alt="AIoTLab Logo" width="170"/>
    <img src="doc/fitdnu_logo.png" alt="FIT DNU Logo" width="180"/>
    <img src="doc/dnu_logo.png" alt="DaiNam University Logo" width="200"/>
  </p>

  [![AIoTLab](https://img.shields.io/badge/AIoTLab-green?style=for-the-badge)](https://www.facebook.com/DNUAIoTLab)
  [![Faculty of Information Technology](https://img.shields.io/badge/Faculty%20of%20Information%20Technology-blue?style=for-the-badge)](https://dainam.edu.vn/vi/khoa-cong-nghe-thong-tin)
  [![DaiNam University](https://img.shields.io/badge/DaiNam%20University-orange?style=for-the-badge)](https://dainam.edu.vn)
</div>



## 📖 1. Giới thiệu

Ứng dụng **Điều khiển máy tính từ xa (Remote Control)** được xây dựng nhằm giúp người dùng có thể truy cập và điều khiển máy tính thông qua mạng LAN hoặc Internet.  

Hệ thống gồm có 2 thành phần:

- 🖥️ **Remote Server**: Máy tính đóng vai trò nhận và thực thi lệnh điều khiển từ xa (chuột, bàn phím, chụp màn hình).  
- 💻 **Remote Client**: Máy tính gửi lệnh điều khiển, hiển thị màn hình từ xa và quản lý phiên kết nối.  

👨‍💻 Người dùng có thể:
- Xem màn hình của máy chủ (Server) theo thời gian thực.
- Điều khiển chuột và bàn phím của Server từ Client.
- Chụp màn hình và lưu lại trên Client.  

✅ Ứng dụng được xây dựng dựa trên **mô hình Client-Server** thông qua **TCP Socket**.  



## 🔧 2. Ngôn ngữ lập trình sử dụng[![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)

### 🔹 Java TCP Socket
- Triển khai mô hình **Client – Server**.
- **Server**: sử dụng `ServerSocket` để lắng nghe kết nối.  
- **Client**: sử dụng `Socket` để kết nối tới server thông qua **IP + Port**.  
- Giao tiếp qua:
  - `DataInputStream`: nhận dữ liệu.
  - `DataOutputStream`: gửi dữ liệu.
- Dữ liệu truyền:
  - **Client → Server**: lệnh điều khiển (`MOVE x y`, `CLICK`, `KEY ...`).
  - **Server → Client**: thông tin cấu hình (`RESOLUTION w h`) và hình ảnh màn hình (JPG nén).

### 🔹 Java Swing (GUI)
- Dùng để xây dựng giao diện người dùng cho Client.
- Thành phần chính:
  - `JFrame`, `JPanel`, `JLabel`, `JButton`, `JTextField`, `JOptionPane`.
- Chức năng:
  - Nhập IP/Port để kết nối.
  - Hiển thị màn hình của Server theo thời gian thực.
  - Cho phép chụp màn hình và lưu file PNG.

### 🔹 Robot Class (AWT)
- Được sử dụng tại **Server** để thao tác trực tiếp với hệ thống:
  - Di chuyển và click chuột (`mouseMove`, `mousePress`, `mouseRelease`).
  - Nhấn/thả phím (`keyPress`, `keyRelease`).
  - Chụp ảnh màn hình (`createScreenCapture`).


## 🚀 3. Các project đã thực hiện

 ### Giao diện chính
![Giao diện](./giaodienchinh.png)

### Kết Nối IP
![Kết nối](./ip.png)


## 📝 4. Hướng dẫn và cài đặt 

### 🔧 Yêu cầu hệ thống

Java Development Kit (JDK): Phiên bản 8 trở lên.

Hệ điều hành: Windows, macOS, hoặc Linux.

Môi trường phát triển: Eclipse / IntelliJ IDEA / VS Code hoặc terminal.

Dung lượng: khoảng 20MB cho mã nguồn và file thực thi.


### 📦 Cài đặt và triển khai
#### Bước 1: Kiểm tra Java
```bash
java -version
javac -version
```

### Bước 2: Biên dịch mã nguồn

Mở terminal, điều hướng đến thư mục src:
```bash
`cd project/src`
```
Chạy biên dịch:
``` bash
`javac remote/RemoteServer.java remote/RemoteClient.java`
```

### Bước 3: Chạy ứng dụng

Khởi động Server:
``` bash
java remote.RemoteServer
```

Chạy Client
```bash
java remote.RemoteClient
```
Nhập **IP** của **Server** + **Port** → nhấn Connect.

Sau khi kết nối thành công, có thể chọn lệnh (kết nối, chụp màn hình, click chuột, gõ phím)
.

### 🚀 Sử dụng ứng dụng
### 🔗 Kết nối
- Nhập **IP** và **Port** của Server.
- Nhấn **Connect** để bắt đầu kết nối.

### 🎮 Điều khiển
- Di chuyển chuột và click trực tiếp trên màn hình Server.
- Gõ phím từ Client để nhập văn bản trên Server.
- Chụp ảnh màn hình Server và lưu về Client.

## 5. Liên Hệ
Họ tên: Nguyễn Tiến Dũng

Lớp: CNTT16-01

Email: dungnguyen190224@gmail.com

Zalo: 0987047451



