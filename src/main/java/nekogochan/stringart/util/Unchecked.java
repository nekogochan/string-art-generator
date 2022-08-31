package nekogochan.stringart.util;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class Unchecked {
  @FunctionalInterface
  private interface SilentInvoker {
    MethodType SIGNATURE = MethodType.methodType(Object.class, Callable.class);//сигнатура метода INVOKE

    <V> V invoke(final Callable<V> callable);
  }

  private static final SilentInvoker SILENT_INVOKER;

  static {
    SilentInvoker si = null;
    final MethodHandles.Lookup lookup = MethodHandles.lookup();
    try {
      final CallSite site = LambdaMetafactory.metafactory(lookup,
                                                          "invoke",
                                                          MethodType.methodType(SilentInvoker.class),
                                                          SilentInvoker.SIGNATURE,
                                                          lookup.findVirtual(Callable.class, "call", MethodType.methodType(Object.class)),
                                                          SilentInvoker.SIGNATURE);
      si = (SilentInvoker) site.getTarget().invokeExact();
    } catch (Throwable e) {
      throw new Error();
    }
    SILENT_INVOKER = si;
  }

  public static <T> T call(Callable<T> callable) {
    return SILENT_INVOKER.invoke(callable);
  }

  public interface ExRunnable {
    void run() throws Exception;
  }

  public static void run(ExRunnable runnable) {
    call(() -> {
      runnable.run();
      return null;
    });
  }

  public interface ExConsumer<T> {
    void accept(T t) throws Exception;
  }

  public static <T> Consumer<T> accept(ExConsumer<T> consumer) {
    return t -> run(() -> consumer.accept(t));
  }
}
