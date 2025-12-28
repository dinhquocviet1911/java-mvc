package TCP;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
public class TCP_ByteStream {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("203.162.10.109", 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        String code = "B22DCCN895;HOu4kAVe";
        out.write(code.getBytes());
        out.flush();
        byte[] buffer = new byte[1024];
        int bytesRead = in.read(buffer);
        String s = new String(buffer, 0, bytesRead).trim();
        System.out.println("Nhận từ server: " + s);
        int n = Integer.parseInt(s);
        ArrayList<Integer> seq = new ArrayList<>();
        while (n != 1) {
            seq.add(n);
            if (n % 2 == 0)
                n = n / 2;
            else
                n = 3 * n + 1;
        }
        seq.add(1);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < seq.size(); i++) {
            res.append(seq.get(i));
            if (i < seq.size() - 1) res.append(" ");
        }
        res.append("; ").append(seq.size());
        System.out.println("Gửi lên server: " + res.toString());
        out.write(res.toString().getBytes());
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
//[Mã câu hỏi (qCode): HOu4kAVe].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2206 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s).
//Yêu cầu là xây dựng một chương trình client tương tác tới server ở trên sử dụng các luồng byte (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: 
//a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;2B3A6510"
//b.	Nhận dữ liệu từ server là một số nguyên n nhỏ hơn 400. Ví dụ: 7
//c.	Thực hiện các bước sau đây để sinh ra chuỗi từ số nguyên n ban đầu và gửi lên server.
//        Bắt đầu với số nguyên nn:
//            Nếu n là số chẵn, chia nn cho 2 để tạo ra số tiếp theo trong dãy.
//            Nếu n là số lẻ và khác 1, thực hiện phép toán n=3*n+1 để tạo ra số tiếp theo.
//        Lặp lại quá trình trên cho đến khi n=1, tại đó dừng thuật toán.
//Kết quả là một dãy số liên tiếp, bắt đầu từ n ban đầu, kết thúc tại 1 và độ dài của chuỗi theo format "chuỗi kết quả; độ dài"  Ví dụ: kết quả với n = 7 thì dãy: 7 22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1; 17;  
//d.	Đóng kết nối và kết thúc chương trình.