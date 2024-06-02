
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
