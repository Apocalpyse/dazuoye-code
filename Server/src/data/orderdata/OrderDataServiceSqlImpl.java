package data.orderdata;

import dataservice.orderdataservice.OrderDataService;
import po.OrderPO;
import po.OrderState;

import java.rmi.RemoteException;
import java.sql.*;

/**
 * Created by 常德隆 on 2016/11/20.
 */
public class OrderDataServiceSqlImpl implements OrderDataService {
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;
    String url = "jdbc:mysql://127.0.0.1:3306/DS_HRS";
    String name="root";
    String password="2578";

    private static OrderDataServiceSqlImpl odssi=null;

    public static OrderDataServiceSqlImpl getInstance() throws RemoteException {
        if(odssi==null){
            odssi=new OrderDataServiceSqlImpl();
        }
        return odssi;
    }

    @Override
    public boolean update(OrderPO po) {
        sql="update 订单信息 set customerName='"+po.getCustomerName()+"',customerPhone='"+po.getCustomerPhone()+"',hotelName='"+po.getHotelName()+"',hotelPhone='"+po.getHotelPhone()+"',hotelLocation='"+po.getHotelLocation()
                +"',roomType='"+po.getRoomType()+"',roomNumber'"+po.getRoomNumber()+"',roomPrice'"+po.getRoomPrice()+"',discount'"+po.getDiscount()+"',startTime'"+po.getStartTime()+"',endTime'"+po.getEndTime()+"',executeTime'"
                +po.getExecuteTime()+"',totalPrice'"+po.getTotalPrice()+"',orderState'"+po.getOs()+"',isExistChild'"+po.getIsExistChild()+"',customerNumber'"+po.getCustomerNumber()+"',masterId'"+po.getMasterId()+"',id'"+po.getId()+"' where ID='"+po.getId()+"'";

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
    public OrderPO find(long id) {
        sql="SELECT * from 订单信息 where ID='"+id+"'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(this.url,this.name,this.password);
            preparedStatement=con.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();

            if (resultSet.next()) {
                OrderPO opo=new OrderPO();
                opo.setCustomerName(resultSet.getString(1));
                opo.setCustomerPhone(resultSet.getString(2));
                opo.setHotelName(resultSet.getString(3));
                opo.setHotelPhone(resultSet.getString(4));
                opo.setHotelLocation(resultSet.getString(5));
                opo.setRoomType(resultSet.getString(6));
                opo.setRoomNumber(Integer.parseInt(resultSet.getString(7)));
                opo.setRoomPrice(Double.parseDouble(resultSet.getString(8)));
                opo.setDiscount(Double.parseDouble(resultSet.getString(9)));
                opo.setStartTime(resultSet.getString(10));
                opo.setEndTime(resultSet.getString(11));
                opo.setExecuteTime(resultSet.getString(12));
                opo.setTotalPrice(Double.parseDouble(resultSet.getString(13)));
                switch (resultSet.getString(14)){
                    case "normal": opo.setOs(OrderState.normal);
                    case "abnormal": opo.setOs(OrderState.abnormal);
                    case "canceled": opo.setOs(OrderState.canceled);
                    case "unexecute": opo.setOs(OrderState.unexecute);
                }
                opo.setIsExistChild(resultSet.getBoolean(15));
                opo.setCustomerNumber(resultSet.getInt(16));
                opo.setMasterId(resultSet.getLong(17));
                opo.setId(resultSet.getLong(18));
                return  opo;
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
    public boolean insert(OrderPO po) {
        sql="insert into 订单信息(customerName,customerPhone,hotelName,hotelPhone,hotelLocation,roomType,roomNumber,roomPrice,discount,startTime,endTime,executeTime,totalPrice,orderState,isExistChild,customerNumber,masterId,id," +
                "," + ") values ('"+po.getCustomerName()+"','"+po.getCustomerPhone()+"','"+po.getHotelName()+"','"+po.getHotelPhone()+"','"+po.getHotelLocation()+"','"+po.getRoomType()+"','"
                +po.getRoomNumber()+"','"+po.getRoomPrice()+"','"+po.getDiscount()+"','"+po.getStartTime()+"','"+po.getEndTime()+"','"+po.getExecuteTime()+"','"+po.getTotalPrice()+"','"+po.getOs()+"','"
                +po.getIsExistChild()+"','"+po.getCustomerNumber()+"','"+po.getMasterId()+"','"+po.getId()+"')";

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
        sql="delete from 订单信息 where ID='"+id+"'";
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
        sql="select max(ID) from 订单信息";
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
