package in.ineuron.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import in.ineuron.bo.CustomerBO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

@Repository(value = "dao")
public class CustomerMySQLDAOImpl implements ICustomerDAO {
	
	static {
		System.out.println("CustomerMySQLDAOImpl.class file is loading...");
	}
	
	@Autowired
	private DataSource dataSource;
	private static final String REAL_TIME_CUSTOMER_INSERT_QUERY = "insert into Customer(`cname`, `caddress`, `pamt`, `rate`, `time_rate`, `iamount`) values(?, ?, ?, ?, ?, ?)";
	
//	public CustomerMySQLDAOImpl(DataSource dataSource) {
//		this.dataSource = dataSource;
//		System.out.println("CustomerMySQLDAOImpl :: 1 param constructor --> " + dataSource.getClass().getName());
//	}
	
	public CustomerMySQLDAOImpl() {
		System.out.println("CustomerMySQLDAOImpl :: zero param constructor...");
	}
	
	@Override
	public int save(CustomerBO bo) throws Exception {
		
		int count = 0;
		try (Connection connection = dataSource.getConnection(); PreparedStatement prepareStatement = connection.prepareStatement(REAL_TIME_CUSTOMER_INSERT_QUERY)) {
			prepareStatement.setString(1, bo.getCustomerName());
			prepareStatement.setString(2, bo.getCustomerAddress());
			prepareStatement.setFloat(3, bo.getPrincipalAmount());
			prepareStatement.setFloat(4, bo.getRate());
			prepareStatement.setFloat(5, bo.getTime());
			prepareStatement.setFloat(6, bo.getInterestAmount());
			
			count = prepareStatement.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}
	
	@Override
	public String toString() {
		return "CustomerMySQLDAOImpl [dataSource=" + dataSource + "]";
	}

}
