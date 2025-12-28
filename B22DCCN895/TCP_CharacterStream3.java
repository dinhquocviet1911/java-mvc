package TCP_ObjectStream_xicyvjjT;
import java.io.*;
import java.net.Socket;
public class TCP_CharacterStream3 {
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
            String qCode = "3QsxvHXw";
            String sendStr = studentCode + ";" + qCode;
            out.write(sendStr);
            out.newLine();
            out.flush();
            String domainList = in.readLine();
            System.out.println("Nhận từ server: " + domainList);
            String[] domains = domainList.split(",");
            StringBuilder result = new StringBuilder();
            for (String domain : domains) {
                domain = domain.trim();
                if (domain.endsWith(".edu")) {
                    result.append(domain).append(", ");
                }
            }
            String finalResult = result.length() > 0
                    ? result.substring(0, result.length() - 2)
                    : "";

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
//[Mã câu hỏi (qCode): 3QsxvHXw].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2208 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu là xây dựng một chương trình client tương tác với server sử dụng các luồng byte (BufferedWriter/BufferedReader) theo kịch bản sau: 
//a.	Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng "studentCode;qCode". Ví dụ: "B15DCCN999;EC4F899B"
//b.	Nhận một chuỗi ngẫu nhiên là danh sách các một số tên miền từ server
//Ví dụ: giHgWHwkLf0Rd0.io, I7jpjuRw13D.io, wXf6GP3KP.vn, MdpIzhxDVtTFTF.edu, TUHuMfn25chmw.vn, HHjE9.com, 4hJld2m2yiweto.vn, y2L4SQwH.vn, s2aUrZGdzS.com, 4hXfJe9giAA.edu
//c.	Tìm kiếm các tên miền .edu và gửi lên server
//Ví dụ: MdpIzhxDVtTFTF.edu, 4hXfJe9giAA.edu
//d.	Đóng kết nối và kết thúc chương trình.