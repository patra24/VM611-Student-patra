import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import engine.PriorityScheduler;
import engine.VMThread;

public class Part05Test {
    @Test
    public void testSimpleExec() {
        TestUtil.compileMethod("t1", """
            load_const 5
            store_local x
            load_const 3
            load_local x
            add
            store_local y
            """);
        VMThread t1 = new VMThread("t1");
        Map<String, Integer> t1Symbols = t1.getEntryPointLocals();

        List<VMThread> threadList = new ArrayList<VMThread>();
        threadList.add(t1);

        PriorityScheduler scheduler = new PriorityScheduler(threadList);

        // t1 should be the current thread, and symbols should be empty.
        assertSame(t1, scheduler.getCurrentThread());
        assertEquals(0, t1Symbols.size());

        // load_const 5
        scheduler.run(1);
        assertEquals(0, t1Symbols.size());

        // store_const x
        scheduler.run(1);
        assertEquals(1, t1Symbols.size());
        assertEquals(5, (int) t1Symbols.get("x"));

        // load_const 3, load_local x, add
        scheduler.run(3);
        assertEquals(1, t1Symbols.size());
        assertSame(t1, scheduler.getCurrentThread());

        // store_local y, thread completes
        scheduler.run(1);
        assertEquals(2, t1Symbols.size());
        assertEquals(5, (int) t1Symbols.get("x"));
        assertEquals(8, (int) t1Symbols.get("y"));
        assertNull(scheduler.getCurrentThread());
    }
}