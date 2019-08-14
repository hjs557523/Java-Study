/**
 * Bytes数组处理工具
 */
public class ByteUtils {
    /**
     * byte[] 转 int
     * @param b
     * @param start
     * @param len
     * @return
     */
    public static int bytes2Int(byte[] b, int start, int len) {
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int n = ((int) b[i]) & 0xff;//保证补0扩展
            n <<= (--len) * 8;
            sum = n + sum;
        }
        return sum;
    }

    /**
     * int 转 byte[]
     * @param value
     * @param len
     * @return
     */
    public static byte[] int2Bytes(int value, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff); 
        }
        return b;
    }

    /**
     * byte[] 转 String
     * @param b
     * @param start
     * @param len
     * @return
     */
    public static String bytes2String(byte[] b, int start, int len) {
        return new String(b, start, len);
    }

    /**
     * String 转 byte[]
     * @param str
     * @return
     */
    public static byte[] string2Bytes(String str) {
        return str.getBytes();
    }

    public static byte[] bytesReplace(byte[] originalBytes, int offset, int len, byte[] replaceBytes) {
        byte[] newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];
        /**
         *     public static native void arraycopy(Object src, int srcPos, Object dest, int destPos, int length);
         */
        System.arraycopy(originalBytes, 0, newBytes, 0, offset);
        System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);
        System.arraycopy(originalBytes, offset + len, newBytes, offset + replaceBytes.length, originalBytes.length - offset - len);
        return newBytes;
    }
}