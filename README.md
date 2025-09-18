
<h2 align="center">
    <a href="https://dainam.edu.vn/vi/khoa-cong-nghe-thong-tin">
    🎓 Faculty of Information Technology (DaiNam University)
    </a>
</h2>
<h2 align="center">
   Ứng Dụng Điều Khiển Máy Tính Từ Xa
</h2>
<div align="center">
    <p align="center">
         <img src="https://github.com/Wipper0000/Student_behavior_recognition/raw/main/anhimage/LogoAIoTLab.png" alt="Ảnh của tôi" width="300"/>
       <img src="https://raw.githubusercontent.com/Wipper0000/Student_behavior_recognition/refs/heads/main/anhimage/logodnu.webp" alt="DaiNam University Logo" width="300"/>
  <img src="https://github.com/Wipper0000/Student_behavior_recognition/raw/main/anhimage/LogoAIoTLab.png" alt="Ảnh của tôi" width="300"/>
        <img src="docs/khoa" alt="DaiNam University Logo" width="200"/>
    </p>

[![AIoTLab](https://img.shields.io/badge/AIoTLab-green?style=for-the-badge)](https://www.facebook.com/DNUAIoTLab)
[![Faculty of Information Technology](https://img.shields.io/badge/Faculty%20of%20Information%20Technology-blue?style=for-the-badge)](https://dainam.edu.vn/vi/khoa-cong-nghe-thong-tin)
[![DaiNam University](https://img.shields.io/badge/DaiNam%20University-orange?style=for-the-badge)](https://dainam.edu.vn)

</div>

## 📖 1. Giới thiệu
Ứng dụng điều khiển máy tính từ xa (Remote Control) được xây dựng nhằm giúp người dùng có thể **truy cập và điều khiển** máy tính thông qua mạng LAN hoặc Internet.  
Hệ thống gồm có 2 thành phần:

- 🖥️ **Remote Server**: Máy tính đóng vai trò nhận và thực thi lệnh điều khiển từ xa (chuột, bàn phím, chụp màn hình).
- 💻 **Remote Client**: Máy tính gửi lệnh điều khiển, hiển thị màn hình từ xa và quản lý phiên kết nối.
- 👨‍💻 Người dùng có thể:
  - Gõ văn bản từ xa.
  - Chụp và lưu ảnh màn hình của máy từ xa.
  - Giao tiếp client ↔ server qua giao thức Socket (TCP).
- ✅ Ứng dụng hữu ích trong **Remote Work**, giảng dạy từ xa, hoặc quản trị hệ thống.

## 🔧 2. Ngôn ngữ lập trình sử dụng: [![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
### 🔹 Java TCP Socket
- Triển khai mô hình **Client-Server**.
- Giao tiếp thông qua `DataInputStream` và `DataOutputStream`.
- Các lệnh được hỗ trợ:  
  `CAPTURE`, `MOVE`, `CLICK`, `TEXT`.

### 🔹 Java Swing
- Xây dựng giao diện người dùng thân thiện (GUI).  
- Các thành phần chính: `JFrame`, `JPanel`, `JButton`, `JTextField`, `JTextArea`.  
- Cho phép nhập IP, Port và hiển thị log hoạt động.

### 🔹 Robot Class (AWT)
- Hỗ trợ thao tác trực tiếp trên hệ thống:  
  - Di chuyển/nhấp chuột.  
  - Gõ phím văn bản.  
  - Chụp màn hình và lưu file.
## 🚀 3. Các project đã thực hiện

### [Khoá 16](./docs/projects/K16/README.md)

## 📝 4. License

© 2025 AIoTLab, Faculty of Information Technology, DaiNam University. All rights reserved.

---
