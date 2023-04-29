package com.mio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

// https://blog.csdn.net/LyRics1996/article/details/106771669/
// exec(String[] cmd) 最好用

/*
Runtime.getRuntime().exec() 执行系统命令
五个常用的API：
    1.Runtime.getRuntime().exec(String cmd)
    直接输入一行cmd命令，例如：ipconfig /all 、java -version等
    2.Runtime.getRuntime().exec(String [] cmd)
    将命令分开输入，例如new String[] {“java”, “-version”}
    3.getInputStream();
    获得输入流
    4.getErrorStream();
    获得错误流
    5.waitFor(timeout, TimeUnit);
    防止卡死
* */

public class Command {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
//        demo1();
        demo2();
    }

    //测试执行命令，返回标准输出+标准错误

    /*获取换行符
        win: \r\n
        linux: \r
        Mac: \n
     */
    public static String cw = System.getProperty("line.separator");
    private static void demo2() {
        //Map<String, String> map = Command.executeToMap("ls /etc/");
        Map<String, String> map = Command.executeToMap("ls /etc2/");
        System.out.println(map.keySet());
        for(String key: map.keySet()){
            System.out.println("\n"+key);
            System.out.println(map.get(key));
        }
    }

    //测试执行命令，返回标准输出
    public static void demo1() {
        // 执行命令，只需要一行
        List<String> dirs = Command.execute("ls -lth");
        //List<String> dirs = Command.execute("ping baidu.com");

        for(String line : dirs){
            System.out.println(line);
        }
    }




    //执行命令: 返回标准输出，打印标准错误
    // 我做的修改：秒作为默认超时单位
    // 疑问： 这个path参数干啥用的？ //todo
    public static List<String> execute(String cmd, String... path) {
        List<String> out = new ArrayList<>();
        String[] cmds = new String[]{"/bin/bash", "-c", cmd};

        Process pro = null;

        try {
            if (null != path && path.length > 0) {
                pro = Runtime.getRuntime().exec(cmds, (String[]) null, new File(path[0]));
            } else {
                pro = Runtime.getRuntime().exec(cmds);
            }

            //获取标准输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line=null;

            while( (line = reader.readLine()) != null ){
                out.add(line);
            }

            //获取标准错误
            BufferedReader err = new BufferedReader(new InputStreamReader(pro.getErrorStream()));

            while( (line = reader.readLine()) != null ){
                System.out.println(line);
            }

            pro.waitFor();
            //boolean exitVal = pro.waitFor(timeOut, TimeUnit.SECONDS); //防止卡死
            //System.out.println(exitVal == true ? "成功" : "失败");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return out;
    }


    // 第二个函数，执行命令: 返回 标准输出 + 标准错误
    public static Map<String, String> executeToMap(String cmd, String... path) {
        String[] cmds = new String[]{"/bin/bash", "-c", cmd};
        Process pro=null;
        StringBuffer outS=new StringBuffer();
        StringBuffer errS=new StringBuffer();

        try{
            if(null!=path && path.length>0){
                pro=Runtime.getRuntime().exec(cmds, (String[]) null, new File(path[0]));
            }else{
                pro=Runtime.getRuntime().exec(cmds);
            }
            //获取标准输出
            BufferedReader reader=new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line=null;
            while( (line=reader.readLine())!=null){
                outS.append(line).append(System.getProperty("line.separator"));
            }
            //后去标准错误
            BufferedReader err = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
            while((line = err.readLine()) != null) {
                errS.append(line).append(System.getProperty("line.separator"));
            }

            pro.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        //输出结果为 map
        Map<String, String> result=new HashMap<>();
        result.put("output", outS.toString());
        result.put("error", errS.toString());
        return result;
    }
}
