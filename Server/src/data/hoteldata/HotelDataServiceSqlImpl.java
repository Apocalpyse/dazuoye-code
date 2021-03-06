package data.hoteldata;

import java.rmi.RemoteException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataservice.hoteldataservice.HotelDataService;
import po.HotelPO;


/**
 * Created by 甯稿痉闅� on 2016/11/20.
 */
public class HotelDataServiceSqlImpl implements HotelDataService {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;
    String url = "jdbc:mysql://127.0.0.1:3306/DS_HRS";
    String name="root";
    String password="2578";

    private static HotelDataServiceSqlImpl hdssi=null;

    public static HotelDataServiceSqlImpl getInstance() throws RemoteException {
        if(hdssi==null){
            hdssi=new HotelDataServiceSqlImpl();
        }
        return hdssi;
    }

    @Override
    public boolean update(HotelPO po) {
        sql="update 酒店信息 set id='"+po.getID()+"',hotelManager='"+po.getHotelManager()+"',hotelManPhone='"+po.getHotelManPhone()+"',goal='"+po.getGoal()+"',price='"+po.getPrice()
                +"',hotelName='"+po.getHotelName()+"',tradeArea'"+po.getTradeArea()+"',hotelLocation'"+po.getHotelLocation()+"',hotelPhone'"+po.getHotelPhone()+"',stars'"+po.getStars()+"',instruction'"+po.getInstruction()+"',evaluation'"
                +po.getEvaluation()+"',cooperatateCompany'"+po.getCooperatateCompany()+"'where ID='"+po.getID()+"'";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.executeUpdate();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public HotelPO find(long id) {
        sql="SELECT * from 酒店信息 where ID='"+id+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();

            if (resultSet.next()) {
                HotelPO hpo=new HotelPO();
                hpo.setID(Long.parseLong(resultSet.getString(1)));
                hpo.setHotelManager(resultSet.getString(2));
                hpo.setHotelManPhone(resultSet.getString(3));
                hpo.setGoal(resultSet.getDouble(4));
                String str=resultSet.getString(5);
                String[] st=str.split("");
                double[] in = null;
                for(int i=0;i<st.length;i++){
                	in[i]=Double.parseDouble(st[i]);
                }
                hpo.setPrice(in);
                hpo.setHotelName(resultSet.getString(6));
                hpo.setTradeArea(resultSet.getString(7));
                hpo.setHotelLocation(resultSet.getString(8));
                hpo.setHotelPhone(resultSet.getString(9));
                hpo.setStars(resultSet.getString(10));
                hpo.setInstruction(resultSet.getString(11));
                String str1=resultSet.getString(12);
                String[] st1=str1.split("");
                ArrayList b = new ArrayList();
                for(int i=0;i<st1.length;i++){
                	b.add(st1[i]);
                }
                hpo.setEvaluation(b);
                hpo.setCooperatateCompany(resultSet.getString(13));
                return hpo;
            }
            else {
                con.close();
                return null;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(HotelPO po) {
        sql="insert into 酒店信息(hotelManager,hotelManPhone,goal,price,hotelName,tradeArea,hotelLocation,hotelPhone,stars,instruction,evaluation,cooperatateCompany,id," +
                "," + ") values ('"+po.getHotelManager()+"','"+po.getHotelManPhone()+"','"+po.getGoal()+"','"+po.getPrice()+"','"+po.getHotelName()+"','"+po.getTradeArea()+"','"
                +po.getHotelLocation()+"','"+po.getHotelPhone()+"','"+po.getStars()+"','"+po.getInstruction()+"','"+po.getEvaluation()+"','"+po.getCooperatateCompany()+"','"+po.getID()+"')";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            preparedStatement.executeUpdate();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(long id) {
        sql="delete from 酒店信息 where ID='"+id+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            PreparedStatement preparedStatement=con.prepareStatement(sql);
            preparedStatement.executeUpdate();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return true;
    }
    @Override
    public long findMaxId() {
        sql="select max(ID) from 酒店信息";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            PreparedStatement preparedStatement=con.prepareStatement(sql);
            preparedStatement.executeUpdate();
            long result=resultSet.getLong(1);
            con.close();
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
