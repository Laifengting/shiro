package com.lft.shiro;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name:      TestInt
 * Package Name:    com.lft.shiro
 * <p>
 * Function: 		A {@code TestInt} object With Some FUNCTION.
 * Date:            2021-06-11 18:50
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class TestInt {
    
    static Map<String, Integer> map = new HashMap<>();
    
    public static void main(String[] args) {
        // Integer 32 位
        // 00000000 00000000 00000000 00000000
        Integer storeValue = 0;
        Integer value1 = 55;
        Integer value2 = 60;
        Integer value3 = 255;
        Integer value4 = 33;
        
        storeValue = storeValue | value1;
        storeValue = storeValue | (value2 << 8);
        storeValue = storeValue | (value3 << 16);
        storeValue = storeValue | (value4 << 24);
        
        map.put("key", storeValue);
        
        Integer resultValue = map.get("key");
        System.out.println(resultValue);
        
        Integer resultValue1 = storeValue & 0xFF;
        Integer resultValue2 = (storeValue & 0xFF00) >> 8;
        Integer resultValue3 = (storeValue & 0xFF0000) >> 16;
        Integer resultValue4 = (storeValue & 0xFF000000) >> 24;
        System.out.println(resultValue1);
        System.out.println(resultValue2);
        System.out.println(resultValue3);
        System.out.println(resultValue4);
    }
    
    
    
}
