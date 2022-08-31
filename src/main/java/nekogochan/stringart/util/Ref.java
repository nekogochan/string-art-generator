package nekogochan.stringart.util;

public class Ref<T> {
  private T it;

  private Ref() {}

  public void set(T it_) {
    it = it_;
  }

  public T get() {
    return it;
  }

  public static <T> Ref<T> of(T data) {
    var r = new Ref<T>();
    r.set(data);
    return r;
  }
}
