package app.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateData {
    private Matcher matcher;
    private final String REGEX_EMAIL = "^[a-zA-Z][a-z0-9_\\.]{4,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";

    public boolean validateDate(String regex){
        boolean check = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate localDate = LocalDate.parse(regex, formatter);
            if (localDate.getYear() >1900 && localDate.isBefore(LocalDate.now())){
                check = true;
            }
        }catch (Exception e){
            System.out.println("Ngay khong dung dinh dang nam > 1900 va nam < ngay hien tai.");
            check = false;
        }
        return check;
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
