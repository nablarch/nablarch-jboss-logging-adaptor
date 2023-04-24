package nablarch.integration.log.jbosslogging;

import nablarch.core.log.Logger;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@link JbossLogger}のテスト。
 */
public class JbossLoggerTest {


    @Test
    public void isFatalEnabled() throws Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);
            
            when(mockLogger.isEnabled(org.jboss.logging.Logger.Level.FATAL)).thenReturn(false, true, true, false);

            final Logger sut = new JbossLoggingLoggerFactory().get("test");
            assertThat(sut.isFatalEnabled(), is(false));
            assertThat(sut.isFatalEnabled(), is(true));
            assertThat(sut.isFatalEnabled(), is(true));
            assertThat(sut.isFatalEnabled(), is(false));
        }
    }

    @Test
    public void logFatal_messageAndOptions() throws Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);
            
            final Logger sut = new JbossLoggingLoggerFactory().get("test");
            sut.logFatal("message{0}:{1}", "a", "b");

            verify(mockLogger, atLeastOnce()).fatalv("message{0}:{1}", new Object[] {"a", "b"});
        }
    }

    @Test
    public void logFatal_messageAndThrowableAndOptions() throws
            Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);
            
            final IllegalArgumentException exception = new IllegalArgumentException("test");
            final Logger sut = new JbossLoggingLoggerFactory().get("test");
            sut.logFatal("エラーが発生しました！", exception, "a");

            verify(mockLogger, atLeastOnce()).fatalv(exception, "エラーが発生しました！", new Object[] {"a"});
        }
    }

    @Test
    public void isErrorEnabled() throws Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);
            when(mockLogger.isEnabled(org.jboss.logging.Logger.Level.ERROR)).thenReturn(true, false, true, false);
            final Logger sut = new JbossLoggingLoggerFactory().get("test");
            assertThat(sut.isErrorEnabled(), is(true));
            assertThat(sut.isErrorEnabled(), is(false));
            assertThat(sut.isErrorEnabled(), is(true));
            assertThat(sut.isErrorEnabled(), is(false));
        }
    }

    @Test
    public void logError_messageAndOptions() throws Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);
            
            final Logger sut = new JbossLoggingLoggerFactory().get("test");

            sut.logError("エラーメッセージ:{0}-{1}", "1", 100);

            verify(mockLogger, atLeastOnce()).errorv("エラーメッセージ:{0}-{1}", new Object[] {"1", 100});
        }
    }

    @Test
    public void logError_messageAndThrowableAndOptions() throws
            Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);
            
            final Logger sut = new JbossLoggingLoggerFactory().get("test");

            final NullPointerException exception = new NullPointerException("null");

            sut.logError("エラーよ:{0}-{1}", exception, BigDecimal.ONE, BigDecimal.ZERO);

            verify(mockLogger, atLeastOnce()).errorv(exception, "エラーよ:{0}-{1}",
                    new Object[] {BigDecimal.ONE, BigDecimal.ZERO});
        }
    }

    @Test
    public void isWarnEnabled() throws Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);
            
            when(mockLogger.isEnabled(org.jboss.logging.Logger.Level.WARN)).thenReturn(true, false, false, true);

            final Logger sut = new JbossLoggingLoggerFactory().get("test");
            assertThat(sut.isWarnEnabled(), is(true));
            assertThat(sut.isWarnEnabled(), is(false));
            assertThat(sut.isWarnEnabled(), is(false));
            assertThat(sut.isWarnEnabled(), is(true));
        }
    }

    @Test
    public void logWarn_messageAndOptions() throws Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);

            final Logger sut = new JbossLoggingLoggerFactory().get("test");

            sut.logWarn("ワーニング-{0}, {1}, {2}", "a", 1, 100L);

            verify(mockLogger, atLeastOnce()).warnv("ワーニング-{0}, {1}, {2}", new Object[] {"a", 1, 100L});
        }
    }

    @Test
    public void logWarn_messageAndThrowableAndOptions() throws
            Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);
            
            final Logger sut = new JbossLoggingLoggerFactory().get("test");

            final NumberFormatException exception = new NumberFormatException("a");

            sut.logWarn("ワーニング-{0}, {1}", exception, "a", 1);

            verify(mockLogger, atLeastOnce()).warnv(exception, "ワーニング-{0}, {1}", new Object[] {"a", 1});
        }
    }

    @Test
    public void isInfoEnabled() throws Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);

            when(mockLogger.isEnabled(org.jboss.logging.Logger.Level.INFO)).thenReturn(false, true, false, true);

            final Logger sut = new JbossLoggingLoggerFactory().get("test");
            assertThat(sut.isInfoEnabled(), is(false));
            assertThat(sut.isInfoEnabled(), is(true));
            assertThat(sut.isInfoEnabled(), is(false));
            assertThat(sut.isInfoEnabled(), is(true));
        }
    }

    @Test
    public void logInfo_messageAndOptions() throws Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);

            final Logger sut = new JbossLoggingLoggerFactory().get("test");

            sut.logInfo("インフォメーション{0}, {1}", "hoge", "fuga");

            verify(mockLogger, atLeastOnce()).infov("インフォメーション{0}, {1}", new Object[] {"hoge", "fuga"});
        }
    }

    @Test
    public void logInfo_messageAndThrowableAndOptions() throws
            Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);

            final Logger sut = new JbossLoggingLoggerFactory().get("test");
            final OutOfMemoryError error = new OutOfMemoryError();

            sut.logInfo("info{0}{1}", error, "aa", "bb");

            verify(mockLogger, atLeastOnce()).infov(error, "info{0}{1}", new Object[] {"aa", "bb"});
        }
    }

    @Test
    public void isDebugEnabled() throws Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);

            when(mockLogger.isEnabled(org.jboss.logging.Logger.Level.DEBUG)).thenReturn(true, true, false, true);

            final Logger sut = new JbossLoggingLoggerFactory().get("test");
            assertThat(sut.isDebugEnabled(), is(true));
            assertThat(sut.isDebugEnabled(), is(true));
            assertThat(sut.isDebugEnabled(), is(false));
            assertThat(sut.isDebugEnabled(), is(true));
        }
    }

    @Test
    public void logDebug_messageAndOptions() throws Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);
            
            final Logger sut = new JbossLoggingLoggerFactory().get("test");

            sut.logDebug("デバッグ:{0}, {1}", "abc", "def");

            verify(mockLogger, atLeastOnce()).debugv("デバッグ:{0}, {1}", new Object[] {"abc", "def"});
        }
    }

    @Test
    public void logDebug_messageAndThrowableAndOptions() throws
            Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);

            final Logger sut = new JbossLoggingLoggerFactory().get("test");

            final NumberFormatException exception = new NumberFormatException("hoge");

            sut.logDebug("debug:{0}", exception, exception.getMessage());

            verify(mockLogger, atLeastOnce()).debugv(exception, "debug:{0}", new Object[] {exception.getMessage()});
        }
    }

    @Test
    public void isTraceEnabled() throws Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);

            when(mockLogger.isEnabled(org.jboss.logging.Logger.Level.TRACE)).thenReturn(false, true, false, false);

            final Logger sut = new JbossLoggingLoggerFactory().get("test");
            assertThat(sut.isTraceEnabled(), is(false));
            assertThat(sut.isTraceEnabled(), is(true));
            assertThat(sut.isTraceEnabled(), is(false));
            assertThat(sut.isTraceEnabled(), is(false));
        }
    }

    @Test
    public void logTrace_messageAndOptions() throws Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);

            final Logger sut = new JbossLoggingLoggerFactory().get("test");
            sut.logTrace("trace-{0}", "aaaa");

            verify(mockLogger, atLeastOnce()).tracev("trace-{0}", new Object[] {"aaaa"});
        }
    }

    @Test
    public void logTrace_messageAndThrowableAndOptions() throws
            Exception {
        final org.jboss.logging.Logger mockLogger = mock(org.jboss.logging.Logger.class);

        try (final MockedStatic<org.jboss.logging.Logger> mocked = mockStatic(org.jboss.logging.Logger.class)) {
            mocked.when(() -> org.jboss.logging.Logger.getLogger(anyString())).thenReturn(mockLogger);

            final Logger sut = new JbossLoggingLoggerFactory().get("test");

            final RuntimeException exception = new RuntimeException();
            sut.logTrace("trace-{0}-{1}", exception, "aaaa", "bbbb");

            verify(mockLogger, atLeastOnce()).tracev(exception, "trace-{0}-{1}", new Object[] {"aaaa", "bbbb"});
        }
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
