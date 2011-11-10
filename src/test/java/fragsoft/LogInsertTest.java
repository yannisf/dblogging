package fragsoft;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class LogInsertTest {
	
	private static final Logger log = LoggerFactory .getLogger(LogInsertTest.class);
	private static final Logger DB_LOG = LoggerFactory .getLogger("db");
	
	@Test
	public void logSimpleMessage() {
		log.info("About to fire db logging!");
		DB_LOG.debug(StringEscapeUtils.escapeSql("One"));
		DB_LOG.debug(StringEscapeUtils.escapeSql("Two"));
		DB_LOG.debug(StringEscapeUtils.escapeSql("Three"));
		DB_LOG.debug(StringEscapeUtils.escapeSql("Four"));
		DB_LOG.debug(StringEscapeUtils.escapeSql("Hi' Five"));
	}

}
