package fragsoft;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.log4j.jdbc.JDBCAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DataSourceDbAppender extends JDBCAppender {
	
	private static final Logger log = LoggerFactory.getLogger(DataSourceDbAppender.class);
	private static DataSource ds = null;
	
	static {
		log.info("Initializing appender. ");
		ds = initializeDatasource();
	}
	
	private static final DataSource initializeDatasource() {
		OracleDataSource dataSource = null;
		try {
			dataSource = new OracleDataSource();
			dataSource.setURL("jdbc:oracle:thin:@dbgr3:1521:EUDRACT");
			dataSource.setUser("EUDRACT_V8_5215_DEV");
			dataSource.setPassword("EUDRACT_V8_5215_DEV");
			log.info("Datasource initialized");
		} catch (SQLException e) {
			log.error("Error: ", e);
		}
		
		return dataSource;
	}

	@Override
	protected void closeConnection(Connection con) {
		if(ds != null) {
			try {
				con.close();
				log.debug("Connection closed successfully. ");
			} catch (SQLException e) {
				log.error("Error: ", e);
			}
		} else {
			super.closeConnection(con);
		}
	}

	@Override
	protected Connection getConnection() throws SQLException {
		Connection con = null;
		if(ds != null) {
			con = ds.getConnection();
			log.debug("Connection acquired successfully. ");
		} else {
			con = super.getConnection();
		}
		
		return con;
	}

}
