package TCP_ObjectStream_xicyvjjT;
import java.io.*;
import java.net.Socket;
import java.util.LinkedHashSet;
import java.util.Set;
public class TCP_CharacterStream {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("203.162.10.109", 2208);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );
            String studentCode = "B22DCCN187";
            String qCode = "HQ0k3NoL";
            String sendStr = studentCode + ";" + qCode;
            out.write(sendStr);
            out.newLine();
            out.flush();
            String randomStr = in.readLine();
            System.out.println("Nhận từ server: " + randomStr);
            Set<Character> resultSet = new LinkedHashSet<>();

            for (char c : randomStr.toCharArray()) {
                if (Character.isLetter(c)) {  
                    resultSet.add(c);        
                }
            }
            StringBuilder result = new StringBuilder();
            for (char c : resultSet) {
                result.append(c);
            }
            String finalResult = result.toString();
            System.out.println("Gửi lên server: " + finalResult);
            out.write(finalResult);
            out.newLine();
            out.flush();
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//[Mã câu hỏi (qCode): HQ0k3NoL].  [Loại bỏ ký tự đặc biệt, trùng và giữ nguyên thứ tự xuất hiện] Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác tới server sử dụng các luồng ký tự (BufferedReader/BufferedWriter) theo kịch bản dưới đây:
//a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;7D6265E3"
//b.	Nhận một chuỗi ngẫu nhiên từ server
//c.	Loại bỏ ký tự đặc biệt, số, ký tự trùng và giữ nguyên thứ tự xuất hiện của ký tự. Gửi chuỗi đã được xử lý lên server.
//d.	Đóng kết nối và kết thúc chương trình
