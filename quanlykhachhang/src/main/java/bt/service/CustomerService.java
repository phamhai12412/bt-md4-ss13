package bt.service;

import bt.model.Customer;
import bt.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements IGenericService<Customer,Integer>{
    private final String getall="select *from customers";
    private final String delete="delete from customers where id=?";
    private final String finby="select *from customers where id=?";
    private final String addd="insert into customers(name,email,address,phone,sex,age) values (?,?,?,?,?,?)";
    private final  String update="update customers set name=?,email=?,address=?,phone=?,sex=?,age=? where id=?";
    @Override
    public List<Customer> getall() {
        List<Customer> customerList=new ArrayList<>();
        Connection connection=null;
        try {
            connection= ConnectDB.getconnection();
            PreparedStatement prest= connection.prepareStatement(getall);
            ResultSet rs= prest.executeQuery();
            while (rs.next()){
                Customer c=new Customer();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));
                c.setPhone(rs.getString("phone"));
                c.setSex(rs.getBoolean("sex"));
                c.setAge(rs.getInt("age"));
                customerList.add(c);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return customerList;
    }

    @Override
    public Customer finbyid(Integer integer) {
        Customer c=new Customer();
        Connection connection=null;
        try {
            connection=ConnectDB.getconnection();
            PreparedStatement prest= connection.prepareStatement(finby);
            prest.setInt(1,integer);
            ResultSet rs= prest.executeQuery();
            while (rs.next()){
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));
                c.setPhone(rs.getString("phone"));
                c.setSex(rs.getBoolean("sex"));
                c.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return c;
    }

    @Override
    public void delete(Integer integer) {
Connection connection=null;
try {
    connection=ConnectDB.getconnection();
    PreparedStatement prest= connection.prepareStatement(delete);
    prest.setInt(1,integer);
    prest.executeUpdate();
} catch (SQLException e) {
    throw new RuntimeException(e);
} finally {
    ConnectDB.closeConnection(connection);
}
    }

    @Override
    public void save(Customer customer) {
Connection connection=null;
PreparedStatement prest=null;
try {
    if(customer.getId()==0){
        connection=ConnectDB.getconnection();
       prest= connection.prepareStatement(addd);
       prest.setString(1, customer.getName());
       prest.setString(2, customer.getEmail());
       prest.setString(3, customer.getAddress());
       prest.setString(4, customer.getPhone());
       prest.setBoolean(5, customer.isSex());
       prest.setInt(6, customer.getAge());
       prest.executeUpdate();
    } else {
        connection=ConnectDB.getconnection();
        prest= connection.prepareStatement(update);
        prest.setString(1, customer.getName());
        prest.setString(2, customer.getEmail());
        prest.setString(3, customer.getAddress());
        prest.setString(4, customer.getPhone());
        prest.setBoolean(5, customer.isSex());
        prest.setInt(6, customer.getAge());
        prest.setInt(7, customer.getId());
        prest.executeUpdate();
    }
} catch (SQLException e) {
    throw new RuntimeException(e);
} finally {
    ConnectDB.closeConnection(connection);
}
    }
}
