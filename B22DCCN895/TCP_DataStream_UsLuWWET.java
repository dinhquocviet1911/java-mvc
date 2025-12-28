package TCP_ObjectStream_xicyvjjT;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class TCP_DataStream_UsLuWWET {
    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            socket = new Socket("203.162.10.109", 2207);
            socket.setSoTimeout(5000); // timeout 5 giây
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            String studentCode = "B22DCCN008";   // đổi nếu cần
            String qCode = "UsLuWWET";
            String sendData = studentCode + ";" + qCode;
            dos.writeUTF(sendData);
            dos.flush();
            int number = dis.readInt();
            System.out.println("Số nhận từ server: " + number);
            String binary = Integer.toBinaryString(number);
            String hex = Integer.toHexString(number).toUpperCase();
            String result = binary + ";" + hex;
            System.out.println("Chuỗi gửi lại server: " + result);
            dos.writeUTF(result);
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
//[Mã câu hỏi (qCode): UsLuWWET].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2207 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu sinh viên xây dựng chương trình client để tương tác với server, sử dụng các luồng data (DataInputStream và DataOutputStream) để trao đổi thông tin theo thứ tự sau:
//a. Gửi mã sinh viên và mã câu hỏi: Chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;D68C93F7".
//b. Nhận một số nguyên hệ thập phân từ server. Ví dụ:: 15226.
//c. Chuyển đổi số nguyên nhận được sang hệ nhị phân và thập lục phân, ghép thành chuỗi và gửi lên server. Ví dụ: 15226 sẽ thành "11101101111010;3B7A"
//d. Đóng kết nối: Kết thúc chương trình sau khi gửi kết quả chuyển đổi.