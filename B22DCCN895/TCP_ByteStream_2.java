package TCP_ObjectStream_xicyvjjT;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.util.*;
public class TCP_ByteStream_2 {
     public static void main(String[] args) {
        try (Socket socket = new Socket("203.162.10.109", 2206);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {
            String req1 = "B22DCCN700;NXg5H7MM";
            outputStream.write(req1.getBytes());
            outputStream.flush();
            byte[] buf = new byte[1024];
            int bytesRead = inputStream.read(buf);
            String res = new String(buf, 0, bytesRead);
            System.out.println("Received: " + res);
            List<Integer> lis = new ArrayList<>();
            lis = findLIS(res);
            String lisStr = "";
            for (int i = 0; i < lis.size(); i++) {
                lisStr += lis.get(i);
                if (i < lis.size() - 1) lisStr += ",";
            }
            String result = lisStr + ";" + lis.size();
            System.out.println("Sending: " + result);
            outputStream.write(result.getBytes());
            outputStream.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static List<Integer> findLIS(String str) {
        String[] arrays = str.split(",");
        int[] arr = new int[arrays.length];
        for(int i = 0; i<arrays.length; i++) {
            arr[i] = Integer.parseInt(arrays[i].trim());
        }
        int n = arr.length;
        int[] dp = new int[n];
        int[] prev = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);
        int maxLength = 1;
        int maxIndex = 0;
        for(int i = 1; i<n; i++) {
            for(int j = 0; j<i; j++) {
                if(arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if(dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }
        List<Integer> lis = new ArrayList<>();
        int index = maxIndex;
        while(index != -1) {
            lis.add(arr[index]);
            index = prev[index];
        }
        Collections.reverse(lis);
        return lis;
    }
}
//[Mã câu hỏi (qCode): Wd2fZle6].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
//    a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;76B68B3B".
//    b. Nhận dữ liệu từ server là một chuỗi các giá trị số nguyên được phân tách bởi ký tự ",". Ví dụ: 5,10,20,25,50,40,30,35.
//    c. Tìm chuỗi con tăng dần dài nhất và gửi độ dài của chuỗi đó lên server theo format "chuỗi tăng dài nhất; độ dài". Ví dụ: 5,10,20,25,50;5
//    d. Đóng kết nối và kết thúc chương trình.