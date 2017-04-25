package tech.simter;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple and powerful utils class for share data during the same thread lifecycle
 * <p>This tools use thread-local variables to make each thread has its own initialized share data.
 * You don't need to transfer context data through method arguments, just use `Context.get(key)` to get
 * its value inside the method.
 * <p>For example:
 * <pre>
 * // set share data anywhere
 * Context.set("userId", new Long(0));
 * Context.set("userName", "RJ");
 *
 * void someMethodInOtherClass(){
 *   // get shared data by key anywhere
 *   Long userId = Context.get("userId");
 *   String userName = Context.get("userName");
 *
 *   // or get all shared data
 *   Map&lt;String, Object&gt; all = Context.get();
 *   ...
 * }
 *
 * // delete shared data anywhere
 * Context.remove("userId");
 * </pre>
 * <p>You can see that it just like to get a static constant value. But you need to know the difference.
 * A static constant always has the same value event in different thread.
 * The context data is isolated between each thread.
 *
 * @author RJ
 */
public class Context {
  // containing the thread-local share data
  private static ThreadLocal<Map<String, Object>> share = ThreadLocal.withInitial(HashMap::new);

  /**
   * Get all share date
   *
   * @return all share date during thread lifecycle
   */
  public static Map<String, Object> get() {
    return share.get();
  }

  /**
   * Get the specific key value
   *
   * @param key the key
   * @param <V> the expected return type
   * @return the value or <tt>null</tt> if there was no mapping for <tt>key</tt>
   */
  @SuppressWarnings("unchecked")
  public static <V> V get(String key) {
    return (V) share.get().get(key);
  }

  /**
   * Set the specific key value
   *
   * @param key   the key
   * @param value the value
   */
  public static void set(String key, Object value) {
    share.get().put(key, value);
  }

  /**
   * Remove the specific key
   *
   * @param key the key
   * @param <V> the expected return type
   * @return the previous value associated with <tt>key</tt>, or <tt>null</tt> if there was no mapping for <tt>key</tt>.
   */
  @SuppressWarnings("unchecked")
  public static <V> V remove(String key) {
    return (V) share.get().remove(key);
  }

  /**
   * Removes all of the share data from this context. The context will be empty after this call returns.
   */
  public static void clear() {
    share.get().clear();
  }
}