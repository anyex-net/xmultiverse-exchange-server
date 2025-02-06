/*
 * @(#)EncryptUtils.java 2015-4-16 下午2:27:42
 * Copyright 2015 Playguy, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.utils;

import com.anyex.apps.consts.CharsetConst;
import com.anyex.apps.bean.Digests;
import com.anyex.apps.bean.Encodes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * <p>File：EncryptUtils.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-16 下午2:27:42</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class EncryptUtils
{
    public static final int     HASH_INTERATIONS = 1024;
    
    public static final int     SALT_SIZE        = 8;
    
    private static final String PARSE_KEY        = "apps";
    
    private static final String ENCRYPT_NAME     = "DES";
    
    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword)
    {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }
    
    /**
     * 验证密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password)
    {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }
    
    /**
     * 返回经过加密的字符串
     * @param password 要加密码的明文字符串
     * @param algorithm 加密运算法则(可以是MD5、MD2、SHA-256、SHA-1等等)
     * @return String 加密后的字符串
     */
    public static String encrypt(String password, String algorithm)
    {
        String result = null;
        byte[] unencodedPassword = password.getBytes(Charset.forName(CharsetConst.CHARSET_UT));
        MessageDigest md = null;
        try
        {
            md = MessageDigest.getInstance(algorithm);
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        if (null != md)
        {
            md.update(unencodedPassword);
            byte[] encodedPassword = md.digest();
            StringBuffer buf = new StringBuffer();
            int iLen = encodedPassword.length;
            for (int i = 0; i < iLen; i++)
            {
                if ((encodedPassword[i] & 0xff) < 0x10)
                {
                    buf.append("0");
                }
                buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
            }
            result = buf.toString();
        }
        return result;
    }
    
    /**
     * 验证密码
     * @param password 明文密码
     * @param ciphertext 加密密码
     * @param algorithm 加密方式
     * @return
     */
    public static Boolean unEncrypt(String password, String ciphertext, String algorithm)
    {
        return StringUtils.equals(ciphertext, encrypt(password, algorithm));
    }
    
    /**
     * DES加密
     * @param strMing
     * @return
     */
    public static String desEncrypt(String strMing)
    {
        String strMi = "";
        try
        {
            byte[] byteMing = strMing.getBytes(CharsetConst.CHARSET_UT);
            byte[] byteMi = encryptByte(byteMing);
            strMi = Base64.encodeBase64String(byteMi);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        return strMi;
    }
    
    /**
     * DES解密
     * @param strMi
     * @return
     */
    public static String desDecrypt(String strMi)
    {
        String strMing = "";
        try
        {
            byte[] byteMi = Base64.decodeBase64(strMi);
            byte[] byteMing = decryptByte(byteMi);
            strMing = new String(byteMing, CharsetConst.CHARSET_UT);
        }
        catch (UnsupportedEncodingException e)
        {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return strMing;
    }
    
    static byte[] encryptByte(byte[] byteS)
    {
        byte[] byteFina = null;
        try
        {
            Cipher cipher = Cipher.getInstance(ENCRYPT_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, generatorKey(PARSE_KEY));
            byteFina = cipher.doFinal(byteS);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        return byteFina;
    }
    
    static byte[] decryptByte(byte[] byteD)
    {
        byte[] byteFina = null;
        try
        {
            Cipher cipher = Cipher.getInstance(ENCRYPT_NAME);
            cipher.init(Cipher.DECRYPT_MODE, generatorKey(PARSE_KEY));
            byteFina = cipher.doFinal(byteD);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
        return byteFina;
    }
    
    static Key generatorKey(String parseKey)
    {
        Key key = null;
        KeyGenerator generator = null;
        try
        {
            generator = KeyGenerator.getInstance(ENCRYPT_NAME);
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        if (null != generator)
        {
            SecureRandom secureRandom = null;
            try
            {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            }
            catch (NoSuchAlgorithmException e)
            {
                log.error(ExceptionUtils.getStackTrace(e));
            }
            assert secureRandom != null;
            secureRandom.setSeed(parseKey.getBytes(Charset.forName(CharsetConst.CHARSET_UT)));
            generator.init(secureRandom);
            key = generator.generateKey();
        }
        return key;
    }
    
    public static void main(String[] args)
    {
        System.out.println(EncryptUtils.desEncrypt("123456"));
        System.out.println(EncryptUtils.desDecrypt("gBbam2tSQKiWCwoghw4Mtg=="));
        System.out.println(EncryptUtils.entryptPassword("yinzhouFire123456"));
        System.out.println(EncryptUtils.desEncrypt("asd123456"));
        try {
            String s =  java.util.Base64.getEncoder().encodeToString("mtopjsonp15({ \"ret\": [\"RGV587_ERROR::SM\" ],\"data\": { \"url\": \"https://login.m.taobao.com/login.htm?from=sm&ttid=h5@iframe&redirectURL=https%3a%2f%2fh5api.m.taobao.com:443/h5/mtop.taobao.idlemtopsearch.main.item.search/5.0/_____tmd_____/punish%3fx5secdata=5e0c8e1365474455070961b803bd560607b52cabf5960afff39b64ce58073f781774affbf103d56a5a7d9ffaab1bd491f02ca36a3c8452ff78183a42ed5329ebdab38dbf06b033a6f34c03b0d5dd2a98b7c78a84d627a84298214a175f3378a4c2a486f2f65fd2f518f4bdb91bf68fb97cabdb8166929b4d0da21f0fd92133f165b0309398014db5684e8a0ec481572e461ee819ca12264cfd380e1ff9a31817100e89753c780197eb66d3c19826cdc34988fd3fe1e94142dae3c516b5d9b484a5bf1ac1aa07d95408e4f9d612b8217779a52a883b8d79c21b1904b01749f64f66d428e113fb539d5f81805431f96a3357f7918a4f2572499cc398910575bb4a39bcaf21bb438351498c3d21f2a8dae0232d72119ccb85c4f5e9ead37afa2a0bc8a5376d5eeb10e4b18bc286a166d1630ba2afb0aa610bf7ac271d0799152c5014944361717ffc7d52587af8124beb74508423590907a8bc0f8102d4102c444b1c0e27289e3bbd296ac6d9bfe0277f66ea90fd43527c7cb806e3cd275d982fccc24f32d9a0571614a0d0e19ddf0b33c9f9df8eab4c64de550c1d9dbb7b18f07f44e309ede9dd2f75a7d4157140698666f9374708cee1a3cea05baca897b0ff09875ed9e59bfb46849f694d6c7d286b68773791c4b2acad843762a6475732eff97aed45417884d425e8ab58db0d14a0651255b0545a012ae2583641b19589676d02fb52b9cf258491d5f9861e82313ed7b1d84c6fb8334148df4a0ddedff15d7e27f0b2cb542b2269c8e7f4fd35f1be44ef7333764e7fb36cccfd267709662089afad2494aa7bd65769319e0bc2293b6370a870007fd45bd898ef5dd6a6e0298ea3edeb1b5c1dc8319112176951d118d10487ac434e9b65914c5db5c6c8dd60e129573c0b9ec76a9a5480379df91549d41580291b1e7636242a5be8f98e8a6af3f6046117e2d23feb4c4b075b540e6e542cf884784a3f7a8cde30a7577d8782b9875d3d608aa9dd09622cfefca61afe0e871fba37fd672c64543c0f73bb2f0cf5aa4656a6f3eb8a633b345e993f9f78059027f37aae4bf8cfb53a8409988ce7c54fcf7b66885db9e7bd2392ca4cf2827403a728d24c1ce98ac7351b4e56489fed6095766a706a3ab95a2986ef67f672e7d21d67b1d4461cb180244c0258d045c47ca161ff803ef50d71ebc54e498b29b7729ce083a926f519d3f67a3fd3d14dcc0e2600e0a818e1404806cd58c2557e7e698ca9975474fc7bc576d73483696ce5bd1a7dcccf8f137986ef6604dd3d864e0fae71620cd4044492a53d0cbd2f85ea0efca638fef5802938ca0a573f395afe4450e664e0cc1b00170eb1c2140a05997e6425fdfc6cfcfd424ac17a8fc4767597e1b361dbac88a4c0ae5e884e27f76582c2cdc771dd5995198552097f9901a66b9eadd75044240b7b7094f668ff1e7a2d656a59922ac6a2ab1ab31ad7fd02548c51d0bda6610a2fd82ed5a0a30e6076af4ff811a8a37dcf65b4329056d74a9d42df2d5b4d291fcbfc90a0a015c15b27871fba37fd672c64543c0f73bb2f0cf5192415b685e99654b8c77cb77ac3927aaacb1fa52e240f4cd81c1136328fda8abf182fbfcf39332db4c42836a88b7576dbe01fa1a5a10a74f3e8bcff75c5b79a549c436f7954edc556f4f5fb7b0646455859539732a3b9805615b7f7853c8c4fdd4782a4695cbe70c09ee041a4c83c4037381032f5f6c008ed9eff4d6891264924831bb44fb43f5a4bd74180b9f0df9106c29d1e7e15e4c1e1af8db33df2eed38bd8d8075f76a1e16a873efac8502008eced4291dda72395ad55c473dc9b11ab30726181f73a3f5ebdcfe2c43ca08dc299dbd07605afa07d2859dc12d0f7d1f5a2fcb5af51fd6cd9403cbc29dd3c91326095766a706a3ab95a2986ef67f672e7dc0489ee2bdfdda492779174bc5fb1ee96f2cc105085dff547b28e18dcd0ee380a670273ede5033253095c424a6edc043cdeb39a1cd7f279f536c02811acdbe785db3bd4e7ba3dcae2f635ee4a13c935656406f3cb4fc576453dd4ddbd73304cc470f5bbf700db3446055d4ba1fcba192e6ca23d761a8ab4ea3445d7df1fd79e1778cb77a146a68df5ccca40c5d8c841ec6f02aad05bd103aeb360b6e2e93316990221562da1e5a48797f5d1bdabc7ec87ae44e200f5a44ed657a40ed30ec6e521b8d71b5018d3167fe6b955b8a3cc69bc22dd7784e1b580c976b742ad27dc7e7e35e29e895dd5559881ed86fa34fa7b%26x5step=100\" }})".getBytes("utf-8"));
            System.out.println(s);
            System.out.println(new String(java.util.Base64.getDecoder().decode(s), "utf-8"));
        } catch (Exception e)
        {

        }
    }
}
