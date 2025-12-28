package TCP_ObjectStream_xicyvjjT;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class TCP_DataStream_q4dqqspG {
 public static void main(String[] args) {
        Socket socket = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            socket = new Socket("203.162.10.109", 2207);
            socket.setSoTimeout(5000); // thời gian chờ tối đa 5 giây
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            String studentCode = "B22DCCN895"; // đổi nếu cần
            String qCode = "q4dqqspG";
            String sendData = studentCode + ";" + qCode;
            dos.writeUTF(sendData);
            dos.flush();
            int number = dis.readInt();
            System.out.println("Nhận từ server: " + number);
            String binary = Integer.toBinaryString(number);
            System.out.println("Chuỗi nhị phân: " + binary);
            dos.writeUTF(binary);
            dos.flush();
            System.out.println("Đã gửi kết quả, kết thúc.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) dis.close();
                if (dos != null) dos.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
//[Mã câu hỏi (qCode): q4dqqspG].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2207 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu sinh viên xây dựng chương trình client để tương tác với server, sử dụng các luồng data (DataInputStream và DataOutputStream) để trao đổi thông tin theo thứ tự sau:
//a. Gửi mã sinh viên và mã câu hỏi: Chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;D68C93F7".
//b. Nhận một số nguyên hệ thập phân từ server. Ví dụ: 45
//c. Chuyển đổi số nguyên nhận được sang hệ nhị phân và gửi chuỗi kết quả này lại cho server. Ví dụ: 45 sẽ thành chuỗi "101101"
//d. Đóng kết nối và kết thúc chương trình.