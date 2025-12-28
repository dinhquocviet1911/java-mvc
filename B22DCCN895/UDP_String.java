package TCP;
import java.net.*;
import java.nio.charset.StandardCharsets;
public class UDP_String {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int serverPort = 2208;
            String studentCode = "B22DCCN895";
            String qCode = "ryzwqFGg";
            String message = ";" + studentCode + ";" + qCode;
            byte[] sendData = message.getBytes(StandardCharsets.UTF_8);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);
            System.out.println("Đã gửi: " + message);
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
            System.out.println("Nhận từ server: " + response);
            String[] parts = response.split(";");
            if (parts.length != 2) {
                System.out.println("Dữ liệu sai định dạng!");
                return;
            }
            String requestId = parts[0];
            String[] binaries = parts[1].split(",");
            String b1 = binaries[0].trim();
            String b2 = binaries[1].trim();
            int num1 = Integer.parseInt(b1, 2);
            int num2 = Integer.parseInt(b2, 2);
            int sum = num1 + num2;
            String result = requestId + ";" + sum;
            byte[] resultData = result.getBytes(StandardCharsets.UTF_8);
            DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length, serverAddress, serverPort);
            socket.send(resultPacket);
            System.out.println("Đã gửi lên server: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed())
                socket.close();
        }
    }
}
//[Mã câu hỏi (qCode): ryzwqFGg].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN000;XbYdNZ3”.
//
//b. Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;b1,b2”, trong đó:
//    requestId là chuỗi ngẫu nhiên duy nhất.
//    b1 là số nhị phân thứ nhất
//    b2 là số nhị phân thứ hai.
//Ví dụ: requestId;0100011111001101,1101000111110101
//c. Thực hiện tính tổng hai số nhị phân nhận được, chuyển về dạng thập phân và gửi lên server theo định dạng “requestId;sum”
//Kết quả: requestId;72130 
//d. Đóng socket và kết thúc chương trình.