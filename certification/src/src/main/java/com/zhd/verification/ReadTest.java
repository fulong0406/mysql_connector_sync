package com.zhd.verification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReadTest {
public static void main(String[] args){

//    UpdateTest test = new UpdateTest();

    //创建10个插入线程
    ThreadReadTest threadtest01 = new ThreadReadTest();
    threadtest01.setStartID(1);
    threadtest01.setEndID(1024-1);
    threadtest01.start();

    ThreadReadTest threadtest02 = new ThreadReadTest();
    threadtest02.setStartID(1024);
    threadtest02.setEndID(1024*2-1);
    threadtest02.start();

    ThreadReadTest threadtest03 = new ThreadReadTest();
    threadtest03.setStartID(1024*2);
    threadtest03.setEndID(1024*3-1);
    threadtest03.start();

    ThreadReadTest threadtest04 = new ThreadReadTest();
    threadtest04.setStartID(1024*3);
    threadtest04.setEndID(1024*4-1);
    threadtest04.start();

    ThreadReadTest threadtest05 = new ThreadReadTest();
    threadtest05.setStartID(1024*4);
    threadtest05.setEndID(1024*5-1);
    threadtest05.start();

    ThreadReadTest threadtest06 = new ThreadReadTest();
    threadtest06.setStartID(1024*5);
    threadtest06.setEndID(1024*6-1);
    threadtest06.start();

    ThreadReadTest threadtest07 = new ThreadReadTest();
    threadtest07.setStartID(1024*6);
    threadtest07.setEndID(1024*7-1);
    threadtest07.start();

    ThreadReadTest threadtest08 = new ThreadReadTest();
    threadtest08.setStartID(1024*7);
    threadtest08.setEndID(1024*8-1);
    threadtest08.start();

    ThreadReadTest threadtest09 = new ThreadReadTest();
    threadtest09.setStartID(1024*8);
    threadtest09.setEndID(1024*9-1);
    threadtest09.start();

    ThreadReadTest threadtest10 = new ThreadReadTest();
    threadtest10.setStartID(1024*9);
    threadtest10.setEndID(1024*10-1);
    threadtest10.start();
}
}
class ThreadReadTest extends Thread{
    int startID ;
    int endID ;

    public int getStartID() {
        return startID;
    }

    public void setStartID(int startID) {
        this.startID = startID;
    }

    public int getEndID() {
        return endID;
    }

    public void setEndID(int endID) {
        this.endID = endID;
    }

    public void run() {
        //循环执行30次，算出30次查询用时   和   每次查询用时
        int flag = 30;
        long startSelectTime,endSelectTime ,useTotolSelectTime;
        startSelectTime = System.currentTimeMillis();
        while(flag-- > 0)
            selectData();
        }
        endSelectTime = System.currentTimeMillis();
        useTotolSelectTime = endUpdate - startUpdate;
        System.out.println(" **********循环读取30次总共花费的时间:" + useTotolSelectTime + "(ms)");

//        insertData();
    }


    public void selectData(){
        long startUpdate,endUpdate,useTime;
        MYSQLConnection mysqlConnection = new MYSQLConnection();
        Connection conn = mysqlConnection.getConnection();
        if(conn != null){
            System.out.println("链接数据库成功");
        }
        try {
            String sql = "select  diff_buf  from  gridtest  where grid_id > ?  and grid_id < ? ";
            PreparedStatement stat = conn.prepareStatement(sql);

            startUpdate = System.currentTimeMillis();
            stat.setInt(1,startID);
            stat.setInt(2,endID);
            stat.executeQuery();
            endUpdate = System.currentTimeMillis();

            useTime = endUpdate - startUpdate;
            System.out.println(" **********单次读取所用的时间:" + useTime + "(ms)");

            //如果执行一次查询所用的时间小于1秒，则sleep剩下的时间，如果查询大于1秒则不处理
            if(1000 - useTime ) {
//                Thread.sleep((int)(1000 - useTime));
                System.out.println(" **********睡眠所用的时间:" + (int)(1000 - useTime) + "(ms)");
            }
            stat.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(){
        long start,end;
        MYSQLConnection mysqlConnection = new MYSQLConnection();
        Connection conn = mysqlConnection.getConnection();
        if(conn != null){
            System.out.println("链接数据库成功");
        }
        try {
            String sql = "insert into gridtest(grid_id,diff_buf) values(?,?)";
            PreparedStatement stat = conn.prepareStatement(sql);

            //构造一个1M的字节数组
            byte[] by1 = new byte[1024];
            for(int k = 0;k<1024;k++){
                by1[k]=4;
            }

            String diff_buf = new String(by1);
            start = System.currentTimeMillis();
            //循环执行1024次
            for(int l=0;l<1024*30;l++){
                stat.setInt(1,l);
                stat.setString(2, diff_buf);
                stat.executeUpdate();
            }

            end = System.currentTimeMillis();
            System.out.println(" **********插入 Run Time:" + (end - start) + "(ms)");


            stat.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
