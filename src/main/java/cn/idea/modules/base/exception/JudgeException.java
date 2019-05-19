package cn.idea.modules.base.exception;

import java.util.function.Supplier;

/**
 * 执行具体业务前的判断异常
 */
public class JudgeException extends Exception {
    public JudgeException(String message) {
        super(message);
    }

    public JudgeException(String format, Object... args) {
        super(String.format(format, args));
    }

    /**
     * 当条件为真的时候抛出JudgeException
     */
    public static void when(boolean condition, Supplier<String> msgSupplier) throws JudgeException {
        if (condition) throw new JudgeException(msgSupplier.get());
    }


    /**
     * 当条件为真的时候抛出JudgeException
     */
    public static void when(boolean condition, String msg) throws JudgeException {
        if (condition) throw new JudgeException(msg);
    }
}
