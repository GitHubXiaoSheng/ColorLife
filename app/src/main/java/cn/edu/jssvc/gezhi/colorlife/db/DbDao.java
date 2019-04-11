package cn.edu.jssvc.gezhi.colorlife.db;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;



import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import cn.edu.jssvc.gezhi.colorlife.bean.MemberInfo;
import cn.edu.jssvc.gezhi.colorlife.home.Arts_info;

/**
 * Created by Administrator on 2018/12/9.
 * 所有对数据库表的操作方法都写在该类中
 */

public class DbDao {
    private Connection conn;
    private PreparedStatement ps;
    private DbConnection dbConnection;
    private ResultSet resultSet;

    MyHandler myHandler = new MyHandler();
    final int DBCONNECTED = 1;

    ScheduledExecutorService scheduledExecutorService;

    public static List<MemberInfo> mMemberInfoList=new ArrayList<>(  );

    public static List<MemberInfo> getmMemberInfoList() {
        return mMemberInfoList;
    }

    public static void setmMemberInfoList(List<MemberInfo> mMemberInfoList) {
        DbDao.mMemberInfoList = mMemberInfoList;
    }

    public DbDao() {

        //定义存放4个线程的线程池
        scheduledExecutorService = Executors.newScheduledThreadPool( 4 );
        new Thread( new Runnable() {
            @Override
            public void run() {
                dbConnection = new DbConnection();
                conn = dbConnection.getConnection();
                Message message = new Message();
                if (conn != null) {
                    message.what = DBCONNECTED;
                    myHandler.sendMessage( message );
                }
            }
        } ).start();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DBCONNECTED:
                    new Thread( new Runnable() {
                        @Override
                        public void run() {
                            mMemberInfoList=queryMemberInfo();
                        }
                    } ).start();
                    break;
            }
        }
    }

    public Connection getConn() {
        return conn;
    }

    /**
     * 查询member_info表，将所有数据存到列表里面
     * @return
     */
    public List<MemberInfo> queryMemberInfo() {//查询方法，返回List
        List<MemberInfo> memberInfoList = new ArrayList<>();

        String sql = "select * from member_info";
        MemberInfo memberInfo;
        try {
            ps = (PreparedStatement) conn.prepareStatement( sql );
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                memberInfo = new MemberInfo();
                memberInfo.setId( resultSet.getInt( "member_id" ) );
                memberInfo.setNickName( resultSet.getString( "nick_name" ) );
//                memberInfo.setRealName( resultSet.getString( 3 ) );
                memberInfo.setPhotoUrl( resultSet.getString( "head_photo" ) );
                memberInfo.setPassword( resultSet.getString( "password" ) );
                memberInfo.setRegisterDate( resultSet.getString( "register_date" ) );
                memberInfo.setPhone(resultSet.getString( "telephone"));
                memberInfo.setHobbies(resultSet.getString( "hobbies"));
                memberInfo.setSex(resultSet.getString( "sex"));
                memberInfo.setBirthday(resultSet.getString( "birthday"));
                memberInfo.setPostalAddress(resultSet.getString( "postal_address"));
                memberInfo.setPoints( resultSet.getInt( "points" ) );
                memberInfo.setMatto( resultSet.getString( "matto" ) );
                memberInfo.setLevel( resultSet.getInt( "level" ) );

//                memberInfo.setQQ(resultSet.getString( 5 ));


                memberInfoList.add( memberInfo );
                Log.d( "tag-querymemberinfo", memberInfo.toString() );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            dbConnection.closeConn();
        }
        return memberInfoList;
    }


    /**
     * 查询arts_info表，将所有数据存到列表里面
     * @return
     */
    public List<Arts_info> queryArtsinfo() {//查询方法，返回List
        List<Arts_info> arts_infoList = new ArrayList<>();
        String sql = "select * from arts_info";
        Arts_info arts_info;
        try {
            ps = (PreparedStatement) conn.prepareStatement( sql );
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                arts_info = new Arts_info();
                arts_info.setArt_id(resultSet.getInt("art_id")+"");
                arts_info.setUrl(resultSet.getString("url"));
                arts_info.setCreate_date(resultSet.getString("create_date"));
                arts_info.setRelease_date(resultSet.getString("release_date"));
                arts_info.setPrice(resultSet.getFloat("price")+"");
                arts_info.setTags(resultSet.getString("tags")+"");
                arts_info.setAuthor_id(resultSet.getInt("author_id")+"");
                arts_info.setClassify_id(resultSet.getInt("classify_id")+"");
                arts_info.setTheme_id(resultSet.getInt("theme_id")+"");
                arts_info.setContent(resultSet.getString("content"));
                arts_info.setMaptilte(resultSet.getString("maptitle"));

                arts_infoList.add(arts_info);
                Log.d( "tag-query-----arts_info", arts_info.toString() );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            dbConnection.closeConn();
        }
        return arts_infoList;
    }

    /**
     * 查询登录
     * @param account 账号
     * @param password 密码
     * @return
     */
    public MemberInfo queryMemberInfo(String account ,String password){
        MemberInfo memberInfo = null;
        try {
            String sql = "select * from member_info where nick_name = \'"+account+"\' and password = \'"+password+"\'";
            ps = (PreparedStatement) conn.prepareStatement( sql );
            resultSet = ps.executeQuery();
            if(resultSet.next()){
                memberInfo = new MemberInfo();
                memberInfo.setId( resultSet.getInt( "member_id" ) );
                memberInfo.setNickName( resultSet.getString( "nick_name" ) );
//                memberInfo.setRealName( resultSet.getString( 3 ) );
                memberInfo.setPhotoUrl( resultSet.getString( "head_photo" ) );
                memberInfo.setPassword( resultSet.getString( "password" ) );
                memberInfo.setRegisterDate( resultSet.getString( "register_date" ) );
                memberInfo.setPhone(resultSet.getString( "telephone"));
                memberInfo.setHobbies(resultSet.getString( "hobbies"));
                memberInfo.setSex(resultSet.getString( "sex"));
                memberInfo.setBirthday(resultSet.getString( "birthday"));
                memberInfo.setPostalAddress(resultSet.getString( "postal_address"));
                memberInfo.setPoints( resultSet.getInt( "points" ) );
                memberInfo.setMatto( resultSet.getString( "matto" ) );
                memberInfo.setLevel( resultSet.getInt( "level" ) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Log.d("testE:", "queryMemberInfo: "+memberInfo.toString());
        return memberInfo;
    }

    /**
     * 插入记录
     * @param memberInfo 要插入的对象
     * @return 是否插入成功
     */
    public boolean insertMemberInfoData(MemberInfo memberInfo){
        String sql = "insert into member_info(nick_name,real_name,head_photo,password,register_date,qq,telephone,hobbies,sex,birthday,postal_address,points,matto,level)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
//            ps = (PreparedStatement) conn.prepareStatement(sql);
            if (conn!=null){
                ps=  (PreparedStatement)conn.prepareStatement(sql);
//                ps.setInt(1,memberInfo.getMemberId());
                ps.setString(1,memberInfo.getNickName());
                ps.setString(2,memberInfo.getRealName());
                ps.setString(3,memberInfo.getPhotoUrl());
                ps.setString(4,memberInfo.getPassword());
                ps.setString(5,memberInfo.getRegisterDate());
                ps.setString(6,memberInfo.getQQ());
                ps.setString(7,memberInfo.getPhone());
                ps.setString(8,memberInfo.getHobbies());
                ps.setString(9,memberInfo.getSex());
                ps.setString(10,memberInfo.getBirthday());
                ps.setString(11,memberInfo.getPostalAddress());
                ps.setInt(12,memberInfo.getPoints());
                ps.setString(13,memberInfo.getMatto());
                ps.setInt(14,memberInfo.getLevel());
                Log.d("insert","memberInfo");
                return ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}