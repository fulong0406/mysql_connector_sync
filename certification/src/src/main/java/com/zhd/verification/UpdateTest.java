package com.zhd.verification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest  extends   Thread{
    public static void main(String[] args) {
        UpdateTest test = new UpdateTest();
        //创建10个插入线程
        threadUpdateTest threadtest01 = new threadUpdateTest();
        threadtest01.setStartID(1);
        threadtest01.setEndID(1023);
        threadtest01.start();

        threadUpdateTest threadtest02 = new threadUpdateTest();
        threadtest02.setStartID(1024);
        threadtest02.setEndID(2047);
        threadtest02.start();

        threadUpdateTest threadtest03 = new threadUpdateTest();
        threadtest03.setStartID(2048);
        threadtest03.setEndID(3071);
        threadtest03.start();

        threadUpdateTest threadtest04 = new threadUpdateTest();
        threadtest04.setStartID(3072);
        threadtest04.setEndID(4095);
        threadtest04.start();

        threadUpdateTest threadtest05 = new threadUpdateTest();
        threadtest05.setStartID(4096);
        threadtest05.setEndID(5119);
        threadtest05.start();

        threadUpdateTest threadtest06 = new threadUpdateTest();
        threadtest06.setStartID(5120);
        threadtest06.setEndID(6143);
        threadtest06.start();

        threadUpdateTest threadtest07 = new threadUpdateTest();
        threadtest07.setStartID(6144);
        threadtest07.setEndID(7167);
        threadtest07.start();

        threadUpdateTest threadtest08 = new threadUpdateTest();
        threadtest08.setStartID(7168);
        threadtest08.setEndID(8191);
        threadtest08.start();

        threadUpdateTest threadtest09 = new threadUpdateTest();
        threadtest09.setStartID(8192);
        threadtest09.setEndID(9215);
        threadtest09.start();

        threadUpdateTest threadtest10 = new threadUpdateTest();
        threadtest10.setStartID(9216);
        threadtest10.setEndID(10239);
        threadtest10.start();

//        threadUpdateTest threadtest11 = new threadUpdateTest();
//        threadtest11.start();

    }

    public void updateData(){
        long startUpdate,endUpdate;
        MYSQLConnection mysqlConnection = new MYSQLConnection();
        Connection conn = mysqlConnection.getConnection();
        if(conn != null){
            System.out.println("链接数据库成功");
        }
        try {
            String sql = "update gridtest set diff_buf = ? where grid_id = ? ";
            PreparedStatement stat = conn.prepareStatement(sql);
//            System.out.println("*************start:");

            //构造一个1KB的字节数组
            byte[] by = new byte[1024*1024];
            for(int k = 0;k<1024*1024;k++){
                by[k]=5;
            }
            String diff_buf = new String(by);
            startUpdate = System.currentTimeMillis();
                stat.setInt(2,1);
                stat.setString(1, diff_buf);
                stat.executeUpdate();
            endUpdate = System.currentTimeMillis();
            System.out.println(" **********更新 Run Time:" + (endUpdate - startUpdate) + "(ms)");
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

    public void readDatd(){
        long startUpdate,endUpdate;
        MYSQLConnection mysqlConnection = new MYSQLConnection();
        Connection conn = mysqlConnection.getConnection();
        if(conn != null){
            System.out.println("链接数据库成功");
        }
        try {
            String sql = "select  diff_buf  from  gridtest ";
            PreparedStatement stat = conn.prepareStatement(sql);

            startUpdate = System.currentTimeMillis();

            stat.executeQuery();
            endUpdate = System.currentTimeMillis();
            System.out.println(" **********读取 Run Time:" + (endUpdate - startUpdate) + "(ms)");
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

            byte[] by1 = new byte[1024*1024*3];
            for(int k = 0;k<1024*1024*3;k++){
                by1[k]=4;
            }

            String diff_buf = new String(by1);
            start = System.currentTimeMillis();

            stat.setInt(1,1);
            stat.setString(2, diff_buf);
            stat.executeUpdate();

            end = System.currentTimeMillis();
            System.out.println(" **********Run Time:" + (end - start) + "(ms)");


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

//测试单线程的更新和读取
class threadUpdateTest extends Thread{
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
        updateData();
//        insertData();
    }

    public void updateData(){
        long startUpdate,endUpdate;
        MYSQLConnection mysqlConnection = new MYSQLConnection();
        Connection conn = mysqlConnection.getConnection();
        if(conn != null){
            System.out.println("链接数据库成功");
        }
        try {
            String sql = "update gridtest set diff_buf = ? where grid_id > ?  and grid_id < ? ";
            PreparedStatement stat = conn.prepareStatement(sql);

            //构造一个1KB的字节数组
            byte[] by = new byte[1024];
            for(int k = 0;k<1024;k++){
                by[k]=5;
            }
            String diff_buf = new String(by);
            startUpdate = System.currentTimeMillis();

//            stat.setString(1, diff_buf);
//            stat.setInt(2,startID);
//            stat.setInt(3,endID-1);
            stat.setString(1,diff_buf);
            stat.setInt(2,startID);
            stat.setInt(3,endID);
            stat.executeUpdate();

            endUpdate = System.currentTimeMillis();
            System.out.println(" **********更新 Run Time:" + (endUpdate - startUpdate) + "(ms)");
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
            for(int l=0;l<1024*10;l++){
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



//class threadInsertTest  extends   Thread{
//
////    long idSTART ;
////    long idend ;
//    long useTime;
//    public long getUseTime(){
//        return useTime;
//    }
////    public void setIdSTART(long idSTART){
////        this.idSTART = idSTART;
////    }
////    public void setIdend(long idend){
////        this.idend = idend;
////    }
//
//    public void run() {
//        System.out.println(Thread.currentThread().getName());
//        long start,end ;
//        MYSQLConnection mysqlConnection = new MYSQLConnection();
//        Connection conn = mysqlConnection.getConnection();
//        if(conn != null){
//            System.out.println("链接数据库成功");
//        }
//        try {
//            String sql = "insert into gridtest(diff_buf) values(?)";
////            PreparedStatement stat = conn.prepareStatement(sql);
//
//            //构造一个1KB的字节数组
//            byte[] by1 = new byte[1024];
//            for(int k = 0;k<1024;k++){
//                by1[k]=68;
//            }
//
//            String diff_buf = new String(by1);
//            start = System.currentTimeMillis();
//
//            //循环插入1024次   922 次
////            for(long m = 1; m<=922; m++){
//////                stat.setLong(1,m);
////                stat.setString(1, diff_buf);
////                stat.executeUpdate();
////            }
//
//            //使用批处理方式处理
//            String sql1 = "update gridtest set diff_buf = ? where grid_id = ? ";
////            String sql2 ="update gridtest set diff_buf = ? where grid_id = ? ";
////            String sql3 = "update gridtest set diff_buf = ? where grid_id = ? ";
////            String sql4 ="update gridtest set diff_buf = ? where grid_id = ? ";
////            String sql5 = "update gridtest set diff_buf = ? where grid_id = ? ";
////            String sql6 = "update gridtest set diff_buf = ? where grid_id = ? ";
////            String sql7 = "update gridtest set diff_buf = ? where grid_id = ? ";
////            String sql8 = "update gridtest set diff_buf = ? where grid_id = ? ";
////            String sql9 = "update gridtest set diff_buf = ? where grid_id = ? ";
////            String sql10 = "update gridtest set diff_buf = ? where grid_id = ? ";
////            String sql11 ="update gridtest set diff_buf = ? where grid_id = ? ";
////            String sql12 = "update gridtest set diff_buf = ? where grid_id = ? ";
//            PreparedStatement stmt01 = conn.prepareStatement(sql1);
//            PreparedStatement stmt02 = conn.prepareStatement(sql1);
//            PreparedStatement stmt03 = conn.prepareStatement(sql1);
//            PreparedStatement stmt04 = conn.prepareStatement(sql1);
//            PreparedStatement stmt05 = conn.prepareStatement(sql1);
//            PreparedStatement stmt06 = conn.prepareStatement(sql1);
//            PreparedStatement stmt07 = conn.prepareStatement(sql1);
//            PreparedStatement stmt08 = conn.prepareStatement(sql1);
//            PreparedStatement stmt09 = conn.prepareStatement(sql1);
//            PreparedStatement stmt10 = conn.prepareStatement(sql1);
//            PreparedStatement stmt11 = conn.prepareStatement(sql1);
//            PreparedStatement stmt12 = conn.prepareStatement(sql1);
//            stmt01.setString(1,diff_buf);
//            stmt01.setInt(2,1);
//            stmt01.addBatch(sql1);
//            stmt01.executeBatch();
//            stmt01.clearBatch();
//
//            stmt02.setString(1,diff_buf);
//            stmt02.setInt(2,2);
//            stmt02.addBatch(sql1);
//            stmt02.executeBatch();
//            stmt02.clearBatch();
//
//            stmt03.setString(1,diff_buf);
//            stmt03.setInt(2,3);
//            stmt03.addBatch(sql1);
//            stmt03.executeBatch();
//            stmt03.clearBatch();
//
//            stmt04.setString(1,diff_buf);
//            stmt04.setInt(2,4);
//            stmt04.addBatch(sql1);
//            stmt04.executeBatch();
//            stmt04.clearBatch();
//
//            stmt05.setString(1,diff_buf);
//            stmt05.setInt(2,5);
//            stmt05.addBatch(sql1);
//            stmt05.executeBatch();
//            stmt05.clearBatch();
//
//            stmt06.setString(1,diff_buf);
//            stmt06.setInt(2,6);
//            stmt06.addBatch(sql1);
//            stmt06.executeBatch();
//            stmt06.clearBatch();
//
//            stmt07.setString(1,diff_buf);
//            stmt07.setInt(2,7);
//            stmt07.addBatch(sql1);
//            stmt07.executeBatch();
//            stmt07.clearBatch();
//
//            stmt08.setString(1,diff_buf);
//            stmt08.setInt(2,8);
//            stmt08.addBatch(sql1);
//            stmt08.executeBatch();
//            stmt08.clearBatch();
//
//            stmt09.setString(1,diff_buf);
//            stmt09.setInt(2,9);
//            stmt09.addBatch(sql1);
//            stmt09.executeBatch();
//            stmt09.clearBatch();
//
//            stmt10.setString(1,diff_buf);
//            stmt10.setInt(2,10);
//            stmt10.addBatch(sql1);
//            stmt10.executeBatch();
//            stmt10.clearBatch();
//
//            stmt11.setString(1,diff_buf);
//            stmt11.setInt(2,11);
//            stmt11.addBatch(sql1);
//            stmt11.executeBatch();
//            stmt11.clearBatch();
//
//            stmt12.setString(1,diff_buf);
//            stmt12.setInt(2,12);
//            stmt12.addBatch(sql1);
//            stmt12.executeBatch();
//            stmt12.clearBatch();
//
//            end = System.currentTimeMillis();
//            useTime = end - start;
//            System.out.println(" **********Run Time:" + (end - start) + "(ms)");
//            stmt01.close();
//            stmt02.close();
//            stmt03.close();
//            stmt04.close();
//            stmt05.close();
//            stmt06.close();
//            stmt07.close();
//            stmt08.close();
//            stmt09.close();
//            stmt10.close();
//            stmt11.close();
//            stmt12.close();
//
////            stat.close();
////            stmt.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
