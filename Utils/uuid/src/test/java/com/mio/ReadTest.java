package com.mio;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class ReadTest {
    public static String read(final InputStream in) throws IOException{
        ByteArrayOutputStream out=null;
        try{
            out=new ByteArrayOutputStream();
            final byte[] buf=new byte[1024];
            int len=-1;
            while((len=in.read(buf)) != -1 ){
                out.write(buf, 0, len);
            }
            out.flush();
            final byte[] data=out.toByteArray();
            return new String(data);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(in);
            close(out);
        }
        return null;
    }

    public static void close(final Closeable c){
        if (c != null) {
            try{
                c.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
