package Inheritance;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class P {
    public int d1 = 10;
    public int d = 100;

    public void fun1(){
        System.out.println("P's fun1");
    }

    public void fun(){
        System.out.println("P's fun");
    }

    static public void sfun(){
        System.out.println("P's sfun");
    }

    public static class BigFactorial extends Thread {
        private final int number;
        private BigInteger factorial;

        public BigFactorial(int number) {
            this.number = number;
            factorial = BigInteger.ONE;
        }

        @Override
        public void run() {
            for(long i = 1; i<number; i++){
                BigInteger x = BigInteger.valueOf(i);
                factorial = factorial.multiply(x) ;
            }
        }

        public BigInteger getFactorial() {
            return factorial;
        }



        public static void main(String[] args) throws InterruptedException {
            BigFactorial calculator = new BigFactorial(100);
            calculator.start();
            calculator.join(); // Wait for the thread to finish

            System.out.println("Factorial of 100: " + calculator.getFactorial());
        }
    }

    public static class RedisSizeEstimator {
        public static void main(String[] args) throws Exception {
            ObjectMapper mapper = new ObjectMapper();
            String json =
    //                 """
    //                 { "bizOrderId": "202506090152500001138854454912557056",
    //                           		"settlementTime": "2025-06-10T15:21:00+05:30",
    //                				"orderCreatedTime": "2025-06-10T15:21:00+05:30",
    //                				"txnSettleType" : "INSTANT"
    //                                 }
    //                """;
                    "latest_transaction_mid_216820000266977048750";
    //                """
    //        {
    //          "bizOrderId": "202506090152500001138853923037061121",
    //          "orderCreatedTime": "2025-06-09T15:18:54+05:30",
    //          "bizType": "ACQUIRING",
    //          "orderStatus": "SUCCESS",
    //          "payMoneyAmount": {
    //            "value": 1608062189,
    //            "currency": "INR"
    //          },
    //          "payMethod": "UPI",
    //          "promoteExtendInfo": {"upiSubType": "UPI_CC"},
    //          "posId": "POS124xyz",
    //          "referenceBizOrderId": "202506090152500001138854454912557056",
    //          "requestType": "VOIDashbdlwjdiuehbfu",
    //          "oppositeNickname": "Customer2qwertyuiopasdfghjkkllmnbvcxzbefeuryfvr"
    //        }
    //        """;
            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
            for(byte x : bytes){
                System.out.println(x);
            }
        }
    }
}
