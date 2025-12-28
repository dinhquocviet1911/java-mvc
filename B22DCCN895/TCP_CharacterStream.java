package TCP;
import java.io.*;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Map;
public class TCp_CharacterStream {
     public static void main(String[] args) {
        try {
            Socket socket = new Socket("203.162.10.109", 2208);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String code = "B22DCCN895;HXlUCvSJ";
            out.write(code);
            out.newLine();
            out.flush();
            String randomStr = in.readLine();
            System.out.println("Nhận từ server: " + randomStr);
            Map<Character, Integer> freq = new LinkedHashMap<>();
            for (char c : randomStr.toCharArray()) {
                if (Character.isLetterOrDigit(c)) {
                    freq.put(c, freq.getOrDefault(c, 0) + 1);
                }
            }
            StringBuilder result = new StringBuilder();
            for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
                if (entry.getValue() > 1) {
                    result.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
                }
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
//[Mã câu hỏi (qCode): HXlUCvSJ].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng byte (BufferedWriter/BufferedReader) theo kịch bản sau: 
//a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;BAA62945"
//b.	Nhận một chuỗi ngẫu nhiên từ server
//Ví dụ: dgUOo ch2k22ldsOo
//c.	Liệt kê các ký tự (là chữ hoặc số) xuất hiện nhiều hơn một lần trong chuỗi và số lần xuất hiện của chúng và gửi lên server
//Ví dụ: d:2,O:2,o:2,2:3,
//d.	Đóng kết nối và kết thúc chương trình.