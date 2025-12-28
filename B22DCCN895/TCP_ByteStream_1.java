package TCP_ObjectStream_xicyvjjT;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
public class TCP_ByteStream_1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("203.162.10.109", 2206);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            String request = "B22DCCN895;nQgZ0ih2";
            out.write(request.getBytes());
            out.flush();
            byte[] buffer = new byte[1024];
            int len = in.read(buffer);
            String data = new String(buffer, 0, len).trim();
            System.out.println("Nhận từ server: " + data);
            String[] nums = data.split("\\|");
            int sum = 0;
            for (String num : nums) {
                sum += Integer.parseInt(num.trim());
            }
            String result = String.valueOf(sum);
            System.out.println("Gửi lên server: " + result);
            out.write(result.getBytes());
            out.flush();
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//[Mã câu hỏi (qCode): nQgZ0ih2].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). 
//Yêu cầu xây dựng chương trình client thực hiện kết nối tới server trên sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: 
//a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;C64967DD"
//b.	Nhận dữ liệu từ server là một chuỗi gồm các giá trị nguyên được phân tách với nhau bằng  "|"
//Ex: 2|5|9|11
//c.	Thực hiện tìm giá trị tổng của các số nguyên trong chuỗi và gửi lên server
//Ex: 27
//d.	Đóng kết nối và kết thúc