package nablarch.integration.log.jbosslogging;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;

import nablarch.core.log.Logger;
import nablarch.core.log.LoggerManager;
import nablarch.core.repository.SystemRepository;

import org.junit.Test;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

/**
 * {@link JbossLogger}のテスト。
 */
public class JbossLoggerTest {


    @Test
    public void isFatalEnabled(@Mocked final org.jboss.logging.Logger mockLogger) throws Exception {
        new Expectations() {{
            mockLogger.isEnabled(org.jboss.logging.Logger.Level.FATAL);
            returns(false, true, true, false);
        }};

        final Logger sut = new JbossLoggingLoggerFactory().get("test");
        assertThat(sut.isFatalEnabled(), is(false));
        assertThat(sut.isFatalEnabled(), is(true));
        assertThat(sut.isFatalEnabled(), is(true));
        assertThat(sut.isFatalEnabled(), is(false));
    }

    @Test
    public void logFatal_messageAndOptions(@Mocked final org.jboss.logging.Logger mockLogger) throws Exception {
        final Logger sut = new JbossLoggingLoggerFactory().get("test");
        sut.logFatal("message{0}:{1}", "a", "b");

        new Verifications() {{
            mockLogger.fatalv("message{0}:{1}", new Object[] {"a", "b"});
        }};
    }

    @Test
    public void logFatal_messageAndThrowableAndOptions(@Mocked final org.jboss.logging.Logger mockLogger) throws
            Exception {
        final IllegalArgumentException exception = new IllegalArgumentException("test");
        final Logger sut = new JbossLoggingLoggerFactory().get("test");
        sut.logFatal("エラーが発生しました！", exception, "a");

        new Verifications() {{
            mockLogger.fatalv(exception, "エラーが発生しました！", new Object[] {"a"});
        }};
    }

    @Test
    public void isErrorEnabled(@Mocked final org.jboss.logging.Logger mockLogger) throws Exception {
        new Expectations() {{
            mockLogger.isEnabled(org.jboss.logging.Logger.Level.ERROR);
            returns(true, false, true, false);
        }};

        final Logger sut = new JbossLoggingLoggerFactory().get("test");
        assertThat(sut.isErrorEnabled(), is(true));
        assertThat(sut.isErrorEnabled(), is(false));
        assertThat(sut.isErrorEnabled(), is(true));
        assertThat(sut.isErrorEnabled(), is(false));
    }

    @Test
    public void logError_messageAndOptions(@Mocked final org.jboss.logging.Logger mockLogger) throws Exception {
        final Logger sut = new JbossLoggingLoggerFactory().get("test");

        sut.logError("エラーメッセージ:{0}-{1}", "1", 100);

        new Verifications() {{
            mockLogger.errorv("エラーメッセージ:{0}-{1}", new Object[] {"1", 100});
        }};
    }

    @Test
    public void logError_messageAndThrowableAndOptions(@Mocked final org.jboss.logging.Logger mockLogger) throws
            Exception {
        final Logger sut = new JbossLoggingLoggerFactory().get("test");

        final NullPointerException exception = new NullPointerException("null");

        sut.logError("エラーよ:{0}-{1}", exception, BigDecimal.ONE, BigDecimal.ZERO);

        new Verifications() {{
            mockLogger.errorv(exception, "エラーよ:{0}-{1}",
                    new Object[] {BigDecimal.ONE, BigDecimal.ZERO});
        }};
    }

    @Test
    public void isWarnEnabled(@Mocked final org.jboss.logging.Logger mockLogger) throws Exception {
        new Expectations() {{
            mockLogger.isEnabled(org.jboss.logging.Logger.Level.WARN);
            returns(true, false, false, true);
        }};

        final Logger sut = new JbossLoggingLoggerFactory().get("test");
        assertThat(sut.isWarnEnabled(), is(true));
        assertThat(sut.isWarnEnabled(), is(false));
        assertThat(sut.isWarnEnabled(), is(false));
        assertThat(sut.isWarnEnabled(), is(true));
    }

    @Test
    public void logWarn_messageAndOptions(@Mocked final org.jboss.logging.Logger mockLogger) throws Exception {
        final Logger sut = new JbossLoggingLoggerFactory().get("test");

        sut.logWarn("ワーニング-{0}, {1}, {2}", "a", 1, 100L);

        new Verifications() {{
            mockLogger.warnv("ワーニング-{0}, {1}, {2}", new Object[] {"a", 1, 100L});
        }};
    }

    @Test
    public void logWarn_messageAndThrowableAndOptions(@Mocked final org.jboss.logging.Logger mockLogger) throws
            Exception {
        final Logger sut = new JbossLoggingLoggerFactory().get("test");

        final NumberFormatException exception = new NumberFormatException("a");

        sut.logWarn("ワーニング-{0}, {1}", exception, "a", 1);

        new Verifications() {{
            mockLogger.warnv(exception, "ワーニング-{0}, {1}", new Object[] {"a", 1});
        }};
    }

    @Test
    public void isInfoEnabled(@Mocked final org.jboss.logging.Logger mockLogger) throws Exception {
        new Expectations() {{
            mockLogger.isEnabled(org.jboss.logging.Logger.Level.INFO);
            returns(false, true, false, true);
        }};

        final Logger sut = new JbossLoggingLoggerFactory().get("test");
        assertThat(sut.isInfoEnabled(), is(false));
        assertThat(sut.isInfoEnabled(), is(true));
        assertThat(sut.isInfoEnabled(), is(false));
        assertThat(sut.isInfoEnabled(), is(true));
    }

    @Test
    public void logInfo_messageAndOptions(@Mocked final org.jboss.logging.Logger mockLogger) throws Exception {
        final Logger sut = new JbossLoggingLoggerFactory().get("test");

        sut.logInfo("インフォメーション{0}, {1}", "hoge", "fuga");

        new Verifications() {{
            mockLogger.infov("インフォメーション{0}, {1}", new Object[] {"hoge", "fuga"});
        }};
    }

    @Test
    public void logInfo_messageAndThrowableAndOptions(@Mocked final org.jboss.logging.Logger mockLogger) throws
            Exception {
        final Logger sut = new JbossLoggingLoggerFactory().get("test");
        final OutOfMemoryError error = new OutOfMemoryError();

        sut.logInfo("info{0}{1}", error, "aa", "bb");

        new Verifications() {{
            mockLogger.infov(error, "info{0}{1}", new Object[] {"aa", "bb"});
        }};
    }

    @Test
    public void isDebugEnabled(@Mocked final org.jboss.logging.Logger mockLogger) throws Exception {
        new Expectations() {{
            mockLogger.isEnabled(org.jboss.logging.Logger.Level.DEBUG);
            returns(true, true, false, true);
        }};

        final Logger sut = new JbossLoggingLoggerFactory().get("test");
        assertThat(sut.isDebugEnabled(), is(true));
        assertThat(sut.isDebugEnabled(), is(true));
        assertThat(sut.isDebugEnabled(), is(false));
        assertThat(sut.isDebugEnabled(), is(true));
    }

    @Test
    public void logDebug_messageAndOptions(@Mocked final org.jboss.logging.Logger mockLogger) throws Exception {
        final Logger sut = new JbossLoggingLoggerFactory().get("test");

        sut.logDebug("デバッグ:{0}, {1}", "abc", "def");

        new Verifications() {{
            mockLogger.debugv("デバッグ:{0}, {1}", new Object[] {"abc", "def"});
        }};
    }

    @Test
    public void logDebug_messageAndThrowableAndOptions(@Mocked final org.jboss.logging.Logger mockLogger) throws
            Exception {
        final Logger sut = new JbossLoggingLoggerFactory().get("test");

        final NumberFormatException exception = new NumberFormatException("hoge");

        sut.logDebug("debug:{0}", exception, exception.getMessage());

        new Verifications() {{
            mockLogger.debugv(exception, "debug:{0}", new Object[] {exception.getMessage()});
        }};
    }

    @Test
    public void isTraceEnabled(@Mocked final org.jboss.logging.Logger mockLogger) throws Exception {
        new Expectations() {{
            mockLogger.isEnabled(org.jboss.logging.Logger.Level.TRACE);
            returns(false, true, false, false);
        }};

        final Logger sut = new JbossLoggingLoggerFactory().get("test");
        assertThat(sut.isTraceEnabled(), is(false));
        assertThat(sut.isTraceEnabled(), is(true));
        assertThat(sut.isTraceEnabled(), is(false));
        assertThat(sut.isTraceEnabled(), is(false));
    }

    @Test
    public void logTrace_messageAndOptions(@Mocked final org.jboss.logging.Logger mockLogger) throws Exception {
        final Logger sut = new JbossLoggingLoggerFactory().get("test");
        sut.logTrace("trace-{0}", "aaaa");

        new Verifications() {{
            mockLogger.tracev("trace-{0}", new Object[] {"aaaa"});
        }};
    }

    @Test
    public void logTrace_messageAndThrowableAndOptions(@Mocked final org.jboss.logging.Logger mockLogger) throws
            Exception {
        final Logger sut = new JbossLoggingLoggerFactory().get("test");

        final RuntimeException exception = new RuntimeException();
        sut.logTrace("trace-{0}-{1}", exception, "aaaa", "bbbb");

        new Verifications() {{
            mockLogger.tracev(exception, "trace-{0}-{1}", new Object[] {"aaaa", "bbbb"});
        }};
    }


    public static void main(String[] args) {
        System.setProperty("org.jboss.logging.provider", "slf4j");
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");

        final Logger logger = new JbossLoggingLoggerFactory().get("test");
        logger.logInfo("***** fatal *****");
        logger.logFatal("fatal-{0}-{1}", "a", "b");
        logger.logFatal("fatal-{0}", new IllegalArgumentException("error"), "hoge");
        System.err.println("\n\n");

        logger.logInfo("***** error *****");
        logger.logError("error-{0}-{1}", "a", "b");
        logger.logError("error-{0}", new IllegalArgumentException("error"), "hoge");
        System.err.println("\n\n");
        
        logger.logInfo("***** warn *****");
        logger.logWarn("warn-{0}-{1}", "a", "b");
        logger.logWarn("warn-{0}", new IllegalArgumentException("error"), "hoge");
        System.err.println("\n\n");

        logger.logInfo("***** info *****");
        logger.logInfo("info-{0}-{1}", "a", "b");
        logger.logInfo("info-{0}", new IllegalArgumentException("error"), "hoge");
        System.err.println("\n\n");

        logger.logInfo("***** debug *****");
        logger.logDebug("debug-{0}-{1}", "a", "b");
        logger.logDebug("debug-{0}", new IllegalArgumentException("error"), "hoge");
        System.err.println("\n\n");
        
        logger.logInfo("***** trace *****");
        logger.logTrace("trace-{0}-{1}", "a", "b");
        logger.logTrace("trace-{0}", new IllegalArgumentException("error"), "hoge");
    }
}
