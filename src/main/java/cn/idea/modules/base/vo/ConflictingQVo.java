package cn.idea.modules.base.vo;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 加入了条件冲突字段的QVo
 */
@Log4j2
public class ConflictingQVo extends BaseQVo {
    public boolean isConflicting; // 标记条件是否互相冲突

    protected ConflictingQVo(boolean isConflicting) {
        this.isConflicting = isConflicting;
    }

    public static class Builder<Q extends ConflictingQVo> {
        protected Q q;
        private Class<Q> clazz;

        protected Builder(Class<Q> clazz) {
            try {
                this.clazz = clazz;

                Constructor<Q> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                q = constructor.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 构建查询对象
         *
         * @return null-如果查询对象的getXxx方法均返回null，则不新建对象；q-可能为冲突的对象
         */
        public Q build() {
            Method[] methods = clazz.getMethods();
            long numOfContainsValue = Arrays.stream(methods).filter(method -> method.getName().startsWith("get"))
                                          .map(method -> {
                                              try {
                                                  return method.invoke(q);
                                              } catch (ReflectiveOperationException e) {
                                                  log.error("通过反射执行get方法有误，q = {}", q, e);
                                              }
                                              return null;
                                          }).distinct().filter(Objects::nonNull).count();
            if (numOfContainsValue == 0) return null;

            return q;
        }
    }
}
