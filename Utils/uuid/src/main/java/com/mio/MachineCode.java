package com.mio;

import java.util.*;

import java.security.SecureRandom;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MachineCode {
    public static int cq = 9;
    public static String cr = "MD5";

    public static void main(String[] args) {
        String machineCode = MachineCode.getMachineCode();
        System.out.println(machineCode);
    }

    public static String getMachineCode() {
        Set<String> result = new LinkedHashSet();
        result.add(getCpuID());
        result.add(getBoardID());
        result.add(getDiskID());
        String regex = "(.{8})";
        String code = encrypt(result.toString(), "").replaceAll(regex, "$1-").toUpperCase();

        //输出
        System.out.println("CpuId, BoardId, DiskId:  " + result.toString());
        System.out.println(code);

        return code.substring(0, code.length() - 1);
    }

    public static String encrypt(String pass, String salt) {
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object obj = new SimpleHash(cr, pass, credentialsSalt, cq);
        return obj.toString();
    }


    public static String getCpuID() {
        try {
            String cmd = "dmidecode -t processor | grep 'ID' | awk -F ':' '{print $2}' | head -n 1";
            Optional<List<String>> op = Optional.ofNullable(Command.execute(cmd,  new String[0]));
            if (op.isPresent() && ((List)op.get()).size() > 0) {
                return (String)((List)op.get()).get(0);
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return "00 00 00 00 00 00 00 00";
    }

    public static String getBoardID() {
        try {
            String cmd = "dmidecode -t baseboard | grep 'Serial Number' | awk -F ': ' '{print $2}' | head -n 1";
            Optional<List<String>> opt = Optional.ofNullable(Command.execute(cmd,  new String[0]));
            if (opt.isPresent() && ((List)opt.get()).size() > 0) {
                return (String)((List)opt.get()).get(0);
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return "000000000000000";
    }

    public static String getDiskID() {
        try {
            String cmd = "fdisk -l | grep 'Disk identifier' | awk -F ': ' '{print $2}' | head -n 1";
            Optional<List<String>> opt = Optional.ofNullable(Command.execute(cmd, new String[0]));
            if (opt.isPresent() && ((List)opt.get()).size() > 0) {
                return (String)((List)opt.get()).get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return S();
    }

    private static String S() {
        return a(8) + "-" + a(4) + "-" + a(4) + "-" + a(4) + "-" + a(12);
    }

    private static String a(int len) {
        StringBuffer buffer = new StringBuffer();

        for(int i = 0; i < len; ++i) {
            buffer.append(randNext());
        }

        return buffer.toString();
    }

    //产生随机数字
    private static String randNext() {
        SecureRandom r = new SecureRandom();
        return String.valueOf(r.nextInt(10));
    }

}
