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

Ứng dụng **Điều khiển máy tính từ xa (Remote Control)** được xây dựng nhằm giúp người dùng có thể **truy cập và điều khiển** máy tính thông qua mạng LAN hoặc Internet.  

Hệ thống gồm có 2 thành phần:

- 🖥️ **Remote Server**: Máy tính đóng vai trò nhận và thực thi lệnh điều khiển từ xa (chuột, bàn phím, chụp màn hình).  
- 💻 **Remote Client**: Máy tính gửi lệnh điều khiển, hiển thị màn hình từ xa và quản lý phiên kết nối.  

👨‍💻 Người dùng có thể:
- Gõ văn bản từ xa.  
- Chụp và lưu ảnh màn hình của máy từ xa.  
- Giao tiếp client ↔ server qua giao thức Socket (TCP).  

✅ Ứng dụng hữu ích trong **Remote Work**, giảng dạy từ xa, hoặc quản trị hệ thống.  



## 🔧 2. Ngôn ngữ lập trình sử dụng

[![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)

### 🔹 Java TCP Socket
- Triển khai mô hình **Client-Server**.  
- Giao tiếp thông qua `DataInputStream` và `DataOutputStream`.  
- Các lệnh được hỗ trợ:  
  `CAPTURE`, `MOVE`, `CLICK`, `TEXT`.  

### 🔹 Java Swing
- Xây dựng giao diện người dùng thân thiện (GUI).  
- Các thành phần chính: `JFrame`, `JPanel`, `JButton`, `JTextField`, `JTextArea`.  
- Cho phép nhập **IP, Port** và hiển thị log hoạt động.  

### 🔹 Robot Class (AWT)
- Hỗ trợ thao tác trực tiếp trên hệ thống:  
  - Nhấp chuột.  
  - Gõ phím văn bản.  
  - Chụp màn hình và lưu file.  


## 🚀 3. Các project đã thực hiện

 ### Giao diện chính
![Giao diện](./giaodien.png)

### Kết nối thành công
![Kết nối](./ketnoi.png)


## 📝 4. Hướng dẫn và cài đặt 

### 🔧 Yêu cầu hệ thống

Java Development Kit (JDK): Phiên bản 8 trở lên.

Hệ điều hành: Windows, macOS, hoặc Linux.

Môi trường phát triển: Eclipse / IntelliJ IDEA / VS Code hoặc terminal.

Dung lượng: khoảng 20MB cho mã nguồn và file thực thi.


### 📦 Cài đặt và triển khai
### Bước 1: Cài Java

java -version
javac -version

### Bước 2: Biên dịch mã nguồn

Mở terminal, điều hướng đến thư mục src:

`cd project/src`

Chạy biên dịch:

`javac remote/RemoteServer.java remote/RemoteClient.java`


### Bước 3: Chạy ứng dụng

Khởi động Server:

Mở java remote.RemoteServer và chạy.

Server sẽ khởi động và lắng nghe tại IP:Port cấu hình sẵn.

Khởi động Client:

Mở java remote.RemoteClien và chạy.

Nhập IP của Server + Port → nhấn Connect.

Sau khi kết nối thành công, có thể chọn lệnh (kết nối, chụp màn hình, click chuột, gõ phím)
.

### 🚀 Sử dụng ứng dụng
Kết nối

Nhập IP và Port của Server.

Nhấn Connect để kết nối.

Điều khiển máy tính từ xa
## 5. Liên Hệ
Họ tên: Nguyễn Tiến Dũng

Lớp: CNTT16-01

Email: dungnguyen190224@gmail.com

Zalo: 0987047451



