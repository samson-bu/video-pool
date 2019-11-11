package self.samson.bu.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Log4jTest {

    private static final Logger LOGGER = LogManager.getLogger(Log4jTest.class);

    @Test
    public void configTest() {
        LOGGER.trace("trace");
        LOGGER.debug("debug");
        LOGGER.info("info");
        LOGGER.error("error");
        LOGGER.fatal("fatal");


    }
}
