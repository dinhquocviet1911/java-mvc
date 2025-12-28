package TCP;
import java.net.*;
import java.io.*;
public class UDP_DataType {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int serverPort = 2207;
            String studentCode = "B22DCCN895";
            String qCode = "yXo9pv9v";
            String message = ";" + studentCode + ";" + qCode;
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);
            System.out.println("Đã gửi: " + message);
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Nhận từ server: " + response);
            String[] parts = response.split(";");
            if (parts.length != 2) {
                System.out.println("Phản hồi không hợp lệ!");
                return;
            }
            String requestId = parts[0];
            String numStr = parts[1];
            int sum = 0;
            for (char c : numStr.toCharArray()) {
                if (Character.isDigit(c)) {
                    sum += Character.getNumericValue(c);
                }
            }
            String result = requestId + ";" + sum;
            byte[] resultData = result.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length, serverAddress, serverPort);
            socket.send(resultPacket);
            System.out.println("Đã gửi lại: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
//[Mã câu hỏi (qCode): yXo9pv9v].  Một chương trình server cho phép giao tiếp qua giao thức UDP tại cổng 2207. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản:
//
//a. Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng ";studentCode;qCode". Ví dụ: ";B15DCCN011;A1F3D5B7".
//
//b. Nhận thông điệp là một chuỗi từ server theo định dạng "requestId;num", với:
//   - requestId là chuỗi ngẫu nhiên duy nhất.
//   - num là một số nguyên lớn.
//
//c. Tính tổng các chữ số trong num và gửi lại tổng này về server theo định dạng "requestId;sumDigits".
//
//d. Đóng socket và kết thúc chương trình.