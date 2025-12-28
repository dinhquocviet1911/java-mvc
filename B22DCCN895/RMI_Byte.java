package RMI;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
public class RMI_Byte {
    public static void main(String[] args) {
        try {
            ByteService service = (ByteService) Naming.lookup(
                    "rmi://203.162.10.109/RMIByteService"
            );
            String studentCode = "B22DCCN895";
            String qCode = "3ZawD5kX";
            byte[] data = service.requestData(studentCode, qCode);
            if (data == null) {
                System.err.println("Dữ liệu nhận được null");
                return;
            }
            List<Byte> evenList = new ArrayList<>();
            List<Byte> oddList = new ArrayList<>();
            for (byte b : data) {
                if (b % 2 == 0) {
                    evenList.add(b);
                } else {
                    oddList.add(b);
                }
            }
            byte[] result = new byte[data.length];
            int index = 0;
            for (byte b : evenList) {
                result[index++] = b;
            }
            for (byte b : oddList) {
                result[index++] = b;
            }
            service.submitData(studentCode, qCode, result);
            System.out.println("Gửi dữ liệu thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//[Mã câu hỏi (qCode): 3ZawD5kX].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý dữ liệu nhị phân.
//Giao diện từ xa:
//public interface ByteService extends Remote {
//public byte[] requestData(String studentCode, String qCode) throws RemoteException;
//public void submitData(String studentCode, String qCode, byte[] data) throws RemoteException;
//}
//Trong đó:
//•	Interface ByteService được viết trong package RMI.
//Đối tượng cài đặt giao diện từ xa ByteService được đăng ký với RegistryServer với tên là: RMIByteService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với dữ liệu nhị phân nhận được từ RMI Server:
//a. Triệu gọi phương thức requestData để nhận một mảng dữ liệu nhị phân (byte[]) từ server.
//b. Thực hiện phân chia mảng byte[] nhận được thành hai phần: phần đầu chứa các byte có giá trị chẵn và phần sau chứa các byte có giá trị lẻ, duy trì thứ tự xuất hiện của các phần tử trong từng nhóm.
//Ví dụ: Nếu mảng dữ liệu nhận được là [1, 2, 3, 4, 5], sau khi phân chia chẵn-lẻ, mảng kết quả sẽ là [2, 4, 1, 3, 5] (tất cả phần tử chẵn được đặt trước, theo sau là tất cả phần tử lẻ).
//c. Triệu gọi phương thức submitData để gửi mảng byte[] đã được phân chia chẵn-lẻ trở lại server.
//d. Kết thúc chương trình client.