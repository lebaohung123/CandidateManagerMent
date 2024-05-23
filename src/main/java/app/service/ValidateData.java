package app.service;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateData {
    private Matcher matcher;
    private final String
            REGEX_DATE = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)"
            + "?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|"
            + "[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

    private final String REGEX_EMAIL = "^[a-zA-Z][a-z0-9_\\.]{4,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";

    public boolean validateDate(String regex){
        matcher = Pattern.compile(REGEX_DATE).matcher(regex.toString());
        return matcher.matches();
    }

    public boolean validateEmail(String regex){
        matcher = Pattern.compile(REGEX_EMAIL).matcher(regex);
        return matcher.matches();
    }
}

/**
 * public static void main(String[] args) {
 *         String text = "This is a string with numbers 123 and 4567 and 89";
 *         String regex = "\\d+"; // Biểu thức chính quy để tìm các số nguyên
 *
 *         // Tạo một Pattern từ biểu thức chính quy
 *         Pattern pattern = Pattern.compile(regex);
 *         // Tạo một Matcher từ chuỗi và Pattern
 *         Matcher matcher = pattern.matcher(text);
 *
 *         // Duyệt qua các khớp con và hiển thị chúng
 *         while (matcher.find()) {
 *             System.out.println("Found: " + matcher.group());
 *         }
 *     }
 */
