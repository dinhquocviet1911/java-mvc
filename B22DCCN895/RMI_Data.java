package TCP;
import TCP.DataService;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
public class RMI_Data {
    public static void main(String[] args) {
        try {
            DataService service = (DataService) Naming.lookup("rmi://203.162.10.109/RMIDataService");
            String studentCode = "B22DCCN895";
            String qCode = "nopRcoVg";
            Object obj = service.requestData(studentCode, qCode);
            if (!(obj instanceof Integer)) {
                System.err.println("Dữ liệu nhận từ server không phải là số nguyên hợp lệ!");
                return;
            }
            int amount = (Integer) obj;
            System.out.println("Số tiền cần đạt được (amount): " + amount);
            int[] coins = {10, 5, 2, 1};
            int remaining = amount;
            List<Integer> usedCoins = new ArrayList<>();
            int count = 0;
            for (int coin : coins) {
                while (remaining >= coin) {
                    remaining -= coin;
                    usedCoins.add(coin);
                    count++;
                }
            }
            if (remaining != 0) {
                count = -1;
                System.out.println("Không thể đạt được số tiền này với các mệnh giá cho trước.");
            }
            StringBuilder result = new StringBuilder();
            if (count == -1) {
                result.append("-1");
            } else {
                result.append(count).append("; ");
                for (int i = 0; i < usedCoins.size(); i++) {
                    result.append(usedCoins.get(i));
                    if (i < usedCoins.size() - 1) result.append(",");
                }
            }
            String resultStr = result.toString();
            System.out.println("Kết quả gửi lên server: " + resultStr);
            service.submitData(studentCode, qCode, resultStr);
            System.out.println("Đã gửi kết quả lên server thành công.");
        } catch (RemoteException re) {
            System.err.println("Lỗi RemoteException: " + re.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
// [Mã câu hỏi (qCode): nopRcoVg].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu.
// Giao diện từ xa:
// public interface DataService extends Remote {
// public Object requestData(String studentCode, String qCode) throws RemoteException;
// public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
// }
// Trong đó:
// •	Interface DataService được viết trong package RMI.
// •	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên là: RMIDataService.
// Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhận được từ RMI Server:
// a. Triệu gọi phương thức requestData để nhận một số nguyên dương amount từ server, đại diện cho số tiền cần đạt được.
// b. Sử dụng thuật toán xếp đồng xu với các mệnh giá cố định [1, 2, 5, 10] để xác định số lượng đồng xu tối thiểu cần thiết để đạt được số tiền amount. Nếu không thể đạt được số tiền đó với các mệnh giá hiện có, trả về -1.
// Ví dụ: Với amount = 18 và mệnh giá đồng xu cố định [1, 2, 5, 10], kết quả là 4 (18 = 10 + 5 + 2 + 1). Chuỗi cần gửi lên server là: 4; 10,5,2,1
// c. Triệu gọi phương thức submitData để gửi chuỗi (kiểu String) chứa kết quả số lượng đồng xu tối thiểu và giá trị các đồng xu tương ứng  trở lại server.
// d. Kết thúc chương trình client.