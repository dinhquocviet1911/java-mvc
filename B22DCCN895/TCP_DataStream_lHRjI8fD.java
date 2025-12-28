package TCP_ObjectStream_xicyvjjT;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class TCP_DataStream_lHRjI8fD {
    private static String decryptCaesar(String cipher, int s) {
        StringBuilder result = new StringBuilder();
        for (char c : cipher.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char ch = (char) ((c - 'A' - s % 26 + 26) % 26 + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(c)) {
                char ch = (char) ((c - 'a' - s % 26 + 26) % 26 + 'a');
                result.append(ch);
            } else {

                result.append(c);
            }
        }
        return result.toString();
    }
    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            socket = new Socket("203.162.10.109", 2207);
            socket.setSoTimeout(5000);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            String studentCode = "B22DCCN895";
            String qCode = "lHRjI8fD";
            String sendData = studentCode + ";" + qCode;
            dos.writeUTF(sendData);
            dos.flush();
            String encrypted = dis.readUTF();
            int s = dis.readInt();
            System.out.println("Chuỗi mã hóa: " + encrypted);
            System.out.println("Giá trị dịch chuyển s = " + s);
            String decrypted = decryptCaesar(encrypted, s);
            System.out.println("Chuỗi sau giải mã: " + decrypted);
            dos.writeUTF(decrypted);
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
//[Mã câu hỏi (qCode): lHRjI8fD].  Mật mã caesar, còn gọi là mật mã dịch chuyển, để giải mã thì mỗi ký tự nhận được sẽ được thay thế bằng một ký tự cách nó một đoạn s. Ví dụ: với s = 3 thì ký tự “A” sẽ được thay thế bằng ký tự “D”.
//Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2207 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng chương trình client tương tác với server trên, sử dụng các luồng byte (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
//a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;D68C93F7"
//b.	Nhận lần lượt chuỗi đã bị mã hóa caesar và giá trị dịch chuyển s nguyên
//c.	Thực hiện giải mã ra thông điệp ban đầu và gửi lên Server
//d.	Đóng kết nối và kết thúc chương trình.
