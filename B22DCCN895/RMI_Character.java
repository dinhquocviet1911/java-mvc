package RMI;
import java.rmi.Naming;
import java.util.LinkedHashMap;
import java.util.Map;
public class RMI_Character {
    public static void main(String[] args) {
        try {
            CharacterService service = (CharacterService) Naming.lookup(
                    "rmi://203.162.10.109/RMICharacterService"
            );
            String studentCode = "B22DCCN895";
            String qCode = "5IpTHTqh";
            String input = service.requestCharacter(studentCode, qCode);
            System.out.println("Chuỗi nhận được: " + input);
            Map<Character, Integer> freq = new LinkedHashMap<>();
            for (char c : input.toCharArray()) {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }
            StringBuilder result = new StringBuilder();
            result.append("{");
            int index = 0;
            for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
                result.append("\"").append(entry.getKey()).append("\":")
                      .append(entry.getValue());
                if (index < freq.size() - 1) result.append(",");
                index++;
            }
            result.append("}");
            String resultStr = result.toString();
            System.out.println("Kết quả gửi: " + resultStr);
            service.submitCharacter(studentCode, qCode, resultStr);
            System.out.println("Gửi kết quả thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//[Mã câu hỏi (qCode): 5IpTHTqh].  Một chương trình (tạm gọi là RMI Server) cung cấp giao diện cho phép triệu gọi từ xa để xử lý chuỗi.
//Giao diện từ xa:
//public interface CharacterService extends Remote {
//public String requestCharacter(String studentCode, String qCode) throws RemoteException;
//public void submitCharacter(String studentCode, String qCode, String strSubmit) throws RemoteException;
//}
//Trong đó:
//• Interface CharacterService được viết trong package RMI.
//• Đối tượng cài đặt giao diện từ xa CharacterService được đăng ký với RegistryServer với tên là: RMICharacterService.
//Yêu cầu: Viết chương trình tại máy trạm (RMI client) để thực hiện các công việc sau với chuỗi được nhận từ RMI Server:
//a. Triệu gọi phương thức requestCharacter để nhận chuỗi ngẫu nhiên từ server với định dạng: "Chuỗi văn bản đầu vào".
//b. Thực hiện thao tác đếm tần số xuất hiện của từng ký tự trong chuỗi đầu vào. Kết quả trả về là danh sách các ký tự kèm theo số lần xuất hiện của mỗi ký tự.
//Ví dụ: Chuỗi ban đầu "Hello world" -> Kết quả đếm tần số ký tự: {"H": 1, "e": 1, "l": 3, "o": 2, " ": 1, "w": 1, "r": 1, "d": 1}.
//c. Triệu gọi phương thức submitCharacter để gửi kết quả đếm tần số ký tự trở lại server dưới dạng chuỗi kết quả đã được định dạng.
//d. Kết thúc chương trình client.