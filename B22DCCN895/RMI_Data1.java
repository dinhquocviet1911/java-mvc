package TCP;
import java.rmi.Naming;
import java.util.Arrays;
public class RMI_Data1 {
    public static void main(String[] args) {
        try {
            DataService service = (DataService) Naming.lookup(
                    "rmi://203.162.10.109/RMIDataService"
            );
            String studentCode = "B22DCCN895";
            String qCode = "ls9th0l7";
            Object obj = service.requestData(studentCode, qCode);
            if (!(obj instanceof String)) {
                System.err.println("Dữ liệu không hợp lệ");
                return;
            }
            String data = (String) obj;
            System.out.println("Chuỗi nhận được: " + data);
            String[] parts = data.split(",");
            int[] arr = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                arr[i] = Integer.parseInt(parts[i].trim());
            }
            nextPermutation(arr);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                result.append(arr[i]);
                if (i < arr.length - 1) result.append(",");
            }
            String resultStr = result.toString();
            System.out.println("Kết quả gửi: " + resultStr);
            service.submitData(studentCode, qCode, resultStr);
            System.out.println("Gửi kết quả thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void nextPermutation(int[] a) {
        int n = a.length;
        int i = n - 2;
        while (i >= 0 && a[i] >= a[i + 1]) {
            i--;
        }
        if (i < 0) {
            Arrays.sort(a);
            return;
        }
        int j = n - 1;
        while (a[j] <= a[i]) {
            j--;
        }
        swap(a, i, j);
        reverse(a, i + 1, n - 1);
    }
    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    private static void reverse(int[] a, int l, int r) {
        while (l < r) {
            swap(a, l++, r--);
        }
    }
//     [Mã câu hỏi (qCode): ls9th0l7].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu.
// Giao diện từ xa:
// public interface DataService extends Remote {
// public Object requestData(String studentCode, String qCode) throws RemoteException;
// public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
// }
// Trong đó:
// •	Interface DataService được viết trong package RMI.
// •	Đối tượng cài đặt giao diện từ xa DataService được đăng ký với RegistryServer với tên là: RMIDataService.
// Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhận được từ RMI Server:
// a. Triệu gọi phương thức requestData để nhận một chuỗi các số nguyên.
// b. Sử dụng thuật toán sinh tổ hợp kế tiếp để tìm tổ hợp kế tiếp của chuỗi số này theo thứ tự từ điển. Nếu chuỗi đã là tổ hợp lớn nhất, trả về tổ hợp nhỏ nhất (sắp xếp lại từ đầu theo thứ tự từ điển).
// Ví dụ: Với chuỗi 1, 2, 3 tổ hợp kế tiếp là 1, 3, 2. Nếu chuỗi là 3, 2, 1 (tổ hợp lớn nhất), kết quả sẽ là 1, 2, 3 (tổ hợp nhỏ nhất).
// c. Triệu gọi phương thức submitData để gửi chuỗi (String) chứa tổ hợp kế tiếp đã tìm được trở lại server.
// d. Kết thúc chương trình client.