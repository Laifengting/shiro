package com.lft.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;

public class TestSimpleHash {
    
    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        Object credentials = "123456";
        Object salt = null;
        int hashInterations = 2;
        SimpleHash result = new SimpleHash(hashAlgorithmName, credentials, salt, hashInterations);
        System.out.println(result);
    }
}
