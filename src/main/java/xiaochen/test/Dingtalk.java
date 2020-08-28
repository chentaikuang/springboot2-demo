package xiaochen.test;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Dingtalk {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String reqUrl = "https://oapi.dingtalk.com/robot/send?access_token=f0f0559c79b4ff1ae932b3ac69067a7e74740526a33f029176ede37c3d5f065f";
        String secret = "SECb971a62520cbbe891ea5d307ce892bfe9a407ea161d7ed81a664e14ad1bb293c";

        Long timestamp = System.currentTimeMillis();
        String sign = getSign(timestamp, secret);

//        https://oapi.dingtalk.com/robot/send?access_token=XXXXXX&timestamp=XXX&sign=XXX
        StringBuffer stringBuffer = new StringBuffer(reqUrl).append("&timestamp=").append(timestamp).append("&sign=").append(sign);
        System.out.println(stringBuffer.toString());
    }

    private static String getSign(Long timestamp, String secret) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
        System.out.println(sign);
        return sign;
    }
}
