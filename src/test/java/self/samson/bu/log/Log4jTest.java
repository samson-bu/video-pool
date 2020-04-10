package self.samson.bu.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(Log4jTest.class);

    @Test
    public void configTest() {
        LOGGER.trace("trace");
        LOGGER.debug("debug");
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.error("error");
    }
}
