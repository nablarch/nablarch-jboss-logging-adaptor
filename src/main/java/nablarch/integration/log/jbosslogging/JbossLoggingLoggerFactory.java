package nablarch.integration.log.jbosslogging;

import nablarch.core.log.LogSettings;
import nablarch.core.log.Logger;
import nablarch.core.log.LoggerFactory;

/**
 * {@link JbossLogger}を生成するクラス。
 *
 * @author siosio
 */
public class JbossLoggingLoggerFactory implements LoggerFactory{

    /**
     * この実装では何もしない
     */
    @Override
    public void initialize(final LogSettings settings) {
        // nop
    }

    /**
     * この実装では何もしない
     */
    @Override
    public void terminate() {
        // nop
    }

    @Override
    public Logger get(final String name) {
        return new JbossLogger(org.jboss.logging.Logger.getLogger(name));
    }
}
