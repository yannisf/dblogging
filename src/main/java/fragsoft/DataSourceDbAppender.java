package fragsoft;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.jdbc.JDBCAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DataSourceDbAppender extends JDBCAppender {
	
	private static final Logger log = LoggerFactory.getLogger(DataSourceDbAppender.class);
	private static final String DS_JNDI = "???";
	private static DataSource ds = null;
	
	static {
		log.info("Initializing appender. ");
		ds = initializeDatasource();
	}
	
	private static final DataSource initializeDatasource() {
		DataSource dataSource = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup(DS_JNDI);
			log.info("Datasource initialized");
		} catch (NamingException ne) {
			log.error("Error: ", ne);
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
