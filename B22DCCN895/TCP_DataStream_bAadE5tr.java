package TCP_ObjectStream_xicyvjjT;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class TCP_DataStream_bAadE5tr {
    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;

        try {
            socket = new Socket("203.162.10.109", 2207);
            socket.setSoTimeout(5000); // timeout 5s
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            String studentCode = "B22DCCN895"; // đổi nếu cần
            String qCode = "bAadE5tr";
            String sendData = studentCode + ";" + qCode;

            dos.writeUTF(sendData);
            dos.flush();
            int a = dis.readInt();
            int b = dis.readInt();
            System.out.println("Nhận từ server: a = " + a + ", b = " + b);
            int sum = a + b;
            int product = a * b;
            dos.writeInt(sum);
            dos.writeInt(product);
            dos.flush();
            System.out.println("Đã gửi kết quả: tổng = " + sum + ", tích = " + product);
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
//[Mã câu hỏi (qCode): bAadE5tr].  Một chương trình máy chủ cho phép kết nối qua TCP tại cổng 2207 (hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5s), yêu cầu xây dựng chương trình (tạm gọi là client) thực hiện kết nối tới server tại cổng 2207, sử dụng luồng byte dữ liệu (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự: 
//a.	Gửi chuỗi là mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;1D25ED92"
//b.	Nhận lần lượt hai số nguyên a và b từ server
//c.	Thực hiện tính toán tổng, tích và gửi lần lượt từng giá trị theo đúng thứ tự trên lên server
//d.	Đóng kết nối và kết thúc
