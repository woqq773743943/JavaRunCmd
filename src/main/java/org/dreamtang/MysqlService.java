package org.dreamtang;



import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Hello world!
 *
 */
public class MysqlService
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        final Process process = Runtime.getRuntime().exec(new String[]{"C:\\Users\\DALAOCHEN\\Desktop\\mysql-5.7.10\\bld\\client\\Debug\\mysql.exe","-uroot","-proot"});
        Thread.sleep(2000);
        String eachErrorLine;
        //BufferedReader errorBufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream(),Charset.forName("GBK")));
        OutputStream outputStream = process.getOutputStream();
        outputStream.write("show databases;\r\n".getBytes());
        outputStream.flush();
        //outputStream.write("quit\r\n".getBytes());
        //outputStream.flush();

        Thread.sleep(1000);
        InputStream errorStream = process.getErrorStream();
        InputStream inputStream = process.getInputStream();
        ArrayList<InputStream> c = new ArrayList<InputStream>();
        c.add(errorStream);
        c.add(inputStream);
        Enumeration<InputStream> e = Collections.enumeration(c);
        SequenceInputStream sis = new SequenceInputStream(e);


        /*byte[] bytes = new byte[process.getErrorStream().available()];
        process.getErrorStream().read(bytes);
        System.out.println(new String(bytes,"GBK"));
        byte[] _bytes = new byte[process.getInputStream().available()];
        process.getInputStream().read(_bytes);
        System.out.println(new String(_bytes,"GBK"));*/
        /*String eachLine;// 读取进程输出值
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sis,Charset.forName("GBK")));
        while((eachLine = bufferedReader.readLine()) != null)
            System.out.println(eachLine);*/
        byte[] bytes= new byte[1024];

        while(sis.read(bytes)!=-1){
            System.out.println(new String(bytes,"GBK"));
        }
    }


    private static void RunMysqlService() throws IOException {
        Process process = Runtime.getRuntime().exec("cmd.exe \\C C:\\Users\\DALAOCHEN\\Desktop\\mysql-5.7.10\\bld\\sql\\Debug\\mysqld.exe");
        OutputStream outputStream = process.getOutputStream();
        outputStream.write("\n".getBytes());
        outputStream.flush();
        String eachLine;// 读取进程输出值
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(),Charset.forName("GBK")));
        while((eachLine = bufferedReader.readLine()) != null)
            System.out.println(eachLine);
    }
}
