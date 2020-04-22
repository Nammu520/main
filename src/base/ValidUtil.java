package base;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * ValidUtil
 *
 * @author dengyu
 * @date 2020/1/10
 * @since 1.0.0
 */
public class ValidUtil {

    public static void main(String[] args) {
        String a = null;
        if(null == a || a.equals("aa")){

        }
        String vin = "LSUN141Z6E2035917";
        System.out.println(validVin(vin));
    }

    private static final String VIN_REGEX = "^[0-9A-HJ-NPR-Z]{17}$";

    private static final String PHONE_REGEX = "^1[0-9]{10}$";

    public static boolean validPhone(String phone){
        if(StringUtils.isBlank(phone)){
            return false;
        }
        return Pattern.matches(PHONE_REGEX, phone);
    }

    public static boolean validVin(String vin){
        if(StringUtils.isBlank(vin)){
            return false;
        }
        int v[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 0, 7, 0, 9, 2, 3, 4, 5, 6, 7, 8, 9}; // values
        int w[] = {8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2}; //weight
        String code = "0123456789X";
        if(!Pattern.matches(VIN_REGEX, vin)){
            return false;
        }
        int checkSum = 0;
        for(int i = 0; i < vin.length(); i++){
            char c = vin.charAt(i);
            int n;
            if(c >= 65 && c <= 90){ //A-Z,no O,I,Q
                n = c - 65 + 10;
            }else if(c >= 48 && c <= 57) { // 0 - 9
                n = c - 48;
            }else {
                return false;
            }
            checkSum += (w[i] * v[n]);
        }
        checkSum %= 11;
        return vin.charAt(8) == code.charAt(checkSum);
    }
}
