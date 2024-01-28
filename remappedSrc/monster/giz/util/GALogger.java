package monster.giz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static monster.giz.GizmosAdditions.NAMESPACE;

public class GALogger {
    public static final Logger LOGGER = LoggerFactory.getLogger(NAMESPACE);

    public static void log(String msg) {
        LOGGER.info(msg);
    }


}
