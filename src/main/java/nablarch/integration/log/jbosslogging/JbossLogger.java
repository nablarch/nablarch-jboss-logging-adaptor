package nablarch.integration.log.jbosslogging;

import org.jboss.logging.Logger.Level;

import nablarch.core.log.Logger;

/**
 * jboss-loggingを使用してログ出力を行うクラス。
 * <p>
 * 全てのメソッドで{@link org.jboss.logging.Logger}に処理を委譲する。<br>
 *
 * @author siosio
 */
public class JbossLogger implements Logger {

    /** 委譲先ロガー */
    private final org.jboss.logging.Logger logger;

    /**
     * jboss-loggingに処理を委譲するロガーを生成する。
     * @param logger 委譲先のロガー
     */
    public JbossLogger(final org.jboss.logging.Logger logger) {
        this.logger = logger;
    }

    /**
     * @see org.jboss.logging.Logger#isEnabled(Level)
     */
    @Override
    public boolean isFatalEnabled() {
        return logger.isEnabled(Level.FATAL);
    }

    /**
     * @see org.jboss.logging.Logger#fatalv(String, Object)
     */
    @Override
    public void logFatal(final String message, final Object... options) {
        logger.fatalv(message, options);
    }

    /**
     * @see org.jboss.logging.Logger#fatalv(Throwable, String, Object...)
     */
    @Override
    public void logFatal(final String message, final Throwable error, final Object... options) {
        logger.fatalv(error, message, options);
    }

    /**
     * @see org.jboss.logging.Logger#isEnabled(Level)
     */
    @Override
    public boolean isErrorEnabled() {
        return logger.isEnabled(Level.ERROR);
    }

    /**
     * @see org.jboss.logging.Logger#errorv(String, Object...)
     */
    @Override
    public void logError(final String message, final Object... options) {
        logger.errorv(message, options);
    }

    /**
     * @see org.jboss.logging.Logger#errorv(Throwable, String, Object...)
     */
    @Override
    public void logError(final String message, final Throwable error, final Object... options) {
        logger.errorv(error, message, options);
    }

    /**
     * @see org.jboss.logging.Logger#isEnabled(Level)
     */
    @Override
    public boolean isWarnEnabled() {
        return logger.isEnabled(Level.WARN);
    }

    /**
     * @see org.jboss.logging.Logger#warnv(String, Object...)
     */
    @Override
    public void logWarn(final String message, final Object... options) {
        logger.warnv(message, options);
    }

    /**
     * @see org.jboss.logging.Logger#warnv(Throwable, String, Object...)
     */
    @Override
    public void logWarn(final String message, final Throwable error, final Object... options) {
        logger.warnv(error, message, options);
    }

    /**
     * @see org.jboss.logging.Logger#isEnabled(Level)
     */
    @Override
    public boolean isInfoEnabled() {
        return logger.isEnabled(Level.INFO);
    }

    /**
     * @see org.jboss.logging.Logger#infov(String, Object...)
     */
    @Override
    public void logInfo(final String message, final Object... options) {
        logger.infov(message, options);
    }

    /**
     * @see org.jboss.logging.Logger#infov(Throwable, String, Object...)
     */
    @Override
    public void logInfo(final String message, final Throwable error, final Object... options) {
        logger.infov(error, message, options);
    }

    /**
     * @see org.jboss.logging.Logger#isEnabled(Level)
     */
    @Override
    public boolean isDebugEnabled() {
        return logger.isEnabled(Level.DEBUG);
    }

    /**
     * @see org.jboss.logging.Logger#debugv(String, Object...)
     */
    @Override
    public void logDebug(final String message, final Object... options) {
        logger.debugv(message, options);
    }

    /**
     * @see org.jboss.logging.Logger#debugv(Throwable, String, Object...)
     */
    @Override
    public void logDebug(final String message, final Throwable error, final Object... options) {
        logger.debugv(error, message, options);
    }

    /**
     * @see org.jboss.logging.Logger#isEnabled(Level)
     */
    @Override
    public boolean isTraceEnabled() {
        return logger.isEnabled(Level.TRACE);
    }

    /**
     * @see org.jboss.logging.Logger#tracev(String, Object...)
     */
    @Override
    public void logTrace(final String message, final Object... options) {
        logger.tracev(message, options);

    }

    /**
     * @see org.jboss.logging.Logger#tracev(Throwable, String, Object...)
     */
    @Override
    public void logTrace(final String message, final Throwable error, final Object... options) {
        logger.tracev(error, message, options);
    }
}
