package xyz.dxstoolnavigation.utils;

public class HumpConversion {
    /**
     * 驼峰转下划线
     * @param humpStr 驼峰字符串
     * @return 下划线字符串
     */
    public static String humpToUnderline(String humpStr) {
        if (humpStr == null || humpStr.isEmpty()) {
            return humpStr;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < humpStr.length(); i++) {
            char c = humpStr.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_').append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
