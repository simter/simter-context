package tech.simter;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * The thread-local context test
 *
 * @author RJ
 */
public class ContextTest {
  @Test
  public void detail() {
    // initiate data is empty
    Map<String, Object> thread1data = Context.get();
    assertTrue(thread1data.isEmpty());

    // single instance in the same thread
    assertEquals(Context.get(), Context.get());

    // set data
    Long userId = 1L;
    Context.set("userId", userId);
    assertEquals(userId, Context.get("userId"));
    assertNull(Context.get("k2"));

    // remove data
    Context.remove("userId");
    assertNull(Context.get("userId"));

    Context.set("other", "v2");
    assertEquals(1, thread1data.size());

    // other thread should be a hole new data
    new Thread(() -> {
      Map<String, Object> thread2data = Context.get();
      assertTrue(thread2data.isEmpty());
      assertNotEquals(thread1data, thread2data);
    }).start();
  }

  @Test
  public void direct() {
    // no data
    assertNull(Context.get("userId"));

    // set data
    Long userId = 1L;
    Context.set("userId", userId);
    assertEquals(userId, Context.get("userId"));

    // other thread should be a hole new data
    new Thread(() -> {
      // no data
      assertNull(Context.get("userId"));

      // set data
      Long userId2 = 1L;
      Context.set("userId", userId2);
      assertEquals(userId2, Context.get("userId"));
    }).start();
  }
}