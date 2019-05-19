package cn.idea.modules.base.exception;

import java.util.function.Supplier;

/**
 * 业务异常，业务层抛出给控制层
 */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String format, Object... args) {
        super(String.format(format, args));
    }

    /**
     * 当条件为真的时候抛出ServiceException
     */
    public static void when(boolean condition, Supplier<String> msgSupplier) throws ServiceException {
        if (condition) throw new ServiceException(msgSupplier.get());
    }


    /**
     * 当条件为真的时候抛出ServiceException
     */
    public static void when(boolean condition, String msg) throws ServiceException {
        if (condition) throw new ServiceException(msg);
    }

    /**
     * 将JudgeException转化为ServiceException
     */
    public static void from(JudgeException e) throws ServiceException {
        throw new ServiceException(e.getMessage());
    }
}
