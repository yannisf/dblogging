package fragsoft;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class LogInsertTest {
	
	private static final Logger log = LoggerFactory .getLogger(LogInsertTest.class);
	
	@Test
	public void logSimpleMessage() {
		log.debug(StringEscapeUtils.escapeSql("One"));
		log.debug(StringEscapeUtils.escapeSql("Two"));
		log.debug(StringEscapeUtils.escapeSql("Three"));
		log.debug(StringEscapeUtils.escapeSql("Four"));
		log.debug(StringEscapeUtils.escapeSql("Hi' Five"));
	}

}
