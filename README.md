
# Tối Ưu Hóa Dòng Tiền Giữa Các Cá Nhân Sử Dụng Ví Điện Tử

## Giới Thiệu

Trong kỷ nguyên số hóa hiện nay, việc quản lý và tối ưu hóa dòng tiền giữa các cá nhân thông qua các ví điện tử đã trở thành một nhu cầu cấp thiết. Đề tài này nghiên cứu việc tối ưu hóa các giao dịch tài chính giữa các cá nhân sử dụng các ví điện tử như Momo, Zalo Pay và VNPay. Mục tiêu là giảm thiểu số lượng giao dịch cần thiết để cân bằng các khoản nợ giữa các cá nhân, từ đó tối ưu hóa dòng tiền và giảm thiểu chi phí giao dịch.

## 1. Bài Toán Cụ Thể

### 1.1 Các Cá Nhân và Ví Điện Tử:

- Mỗi cá nhân có thể sử dụng một hoặc nhiều ví điện tử như Momo, Zalo Pay và VNPay để thực hiện giao dịch.
- Mỗi cá nhân có thể có các khoản nợ hoặc được nợ từ các cá nhân khác.
- Phải có một cá nhân sử dụng được tất cả loại ví để làm trung gian.

### 1.2 Giao Dịch và Nợ:

Giữa các cá nhân có thể tồn tại các khoản nợ lẫn nhau, được biểu diễn qua các giao dịch. Mỗi giao dịch gồm: người gửi (người nợ), người nhận (người cho vay) và số tiền giao dịch.

### 1.3 Mục Tiêu:

Giảm thiểu số lượng giao dịch cần thiết để cân bằng tất cả các khoản nợ giữa các cá nhân.

## 2. Các Bước Thực Hiện

### 2.1 Xây Dựng Lớp Người (Person):

Lớp này đại diện cho mỗi cá nhân tham gia vào các giao dịch, với các thuộc tính như tên, số tiền nợ ròng và tập hợp các phương thức thanh toán mà họ hỗ trợ.

```java
class Person {
    public String name;
    public int netAmount;
    public Set<String> types;
}
```

### 2.2 Tính Toán Số Dư Ròng:

Tính toán số dư nợ ròng cho mỗi cá nhân, bằng cách lấy tổng số tiền được nhận trừ đi tổng số tiền phải trả.



### 2.3 Tìm Kiếm Tối Ưu Hóa Giao Dịch:

- **Thuật Toán Tham Lam:** Tìm các giao dịch lớn nhất trước để giảm số nợ lớn nhất.
- **Thuật Toán Dijkstra:** Xây dựng đồ thị nợ và tìm đường đi ngắn nhất để tối ưu hóa giao dịch.
- **Thuật Toán Bellman-Ford:** Áp dụng để xử lý các trường hợp có thể có cạnh âm, đảm bảo tính chính xác và hiệu quả.


### 2.4 Thực Hiện và In Kết Quả:

Triển khai phương pháp tối ưu hóa dòng tiền, tính toán các giao dịch cần thiết và in ra các giao dịch này.


Với cách tiếp cận này, đề tài đã sử dụng các thuật toán tối ưu hóa để giải quyết bài toán giảm thiểu số lượng giao dịch giữa các cá nhân sử dụng các ví điện tử khác nhau. Kết quả đạt được giúp tối ưu hóa dòng tiền và giảm thiểu chi phí giao dịch, góp phần nâng cao hiệu quả quản lý tài chính cá nhân trong thời đại số hóa.



## Cài đặt và Sử dụng

### Yêu cầu

- Java Development Kit (JDK) 8 hoặc cao hơn
- Git

### Các bước chạy chương trình

1. **Nhân bản kho lưu trữ**

   Mở terminal của bạn và chạy lệnh sau để nhân bản kho lưu trữ:

   ```sh
   git clone https://github.com/ltt0112/DSA_Project_Cash-Flow-Minimizer.git
   ```

2. **Chuyển đến thư mục dự án**

   ```sh
   cd DSA_Project_Cash-Flow-Minimizer
   ```

3. **Biên dịch chương trình Java**

   ```sh
   javac src/main/java/CashFlowMinimizer.java
   ```

4. **Chạy chương trình**

   ```sh
   java src/main/java/CashFlowMinimizer
   ```

5. **Làm theo các hướng dẫn**

   - Nhập số lượng người tham gia giao dịch.
   - Nhập chi tiết của từng người, bao gồm tên, số lượng phương thức thanh toán và các phương thức thanh toán.
   - Nhập số lượng giao dịch.
   - Nhập chi tiết từng giao dịch, bao gồm người gửi, người nhận và số tiền.

### Ví dụ

Dưới đây là ví dụ về chuỗi nhập liệu để bạn dễ hiểu hơn:

```
6
a 3 m v z
b 2 z v
c 2 v m
d 1 z
e 2 m v
f 1 v
9
f a 300
f c 300
f b 200
f e 100
a c 300
c b 200
b e 500
d a 500
d b 400
```

### Kết quả

Chương trình sẽ hiển thị các giao dịch tối ưu để thanh toán tất cả các khoản nợ với số lượng giao dịch ít nhất.

## Phiên bản Web

Bạn cũng có thể sử dụng phiên bản web của Hệ thống Tối ưu Hóa Dòng Tiền thông qua liên kết sau:

[Ứng dụng Web Tối ưu Hóa Dòng Tiền](https://nguyenluan12.github.io/Cash_Flow_Minimizer/)

## Đóng góp

Chúng tôi hoan nghênh mọi sự đóng góp! Vui lòng fork kho lưu trữ và tạo pull request với các thay đổi của bạn.

