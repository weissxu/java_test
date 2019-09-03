package com.weiss.jdk.jdk9;

import org.junit.Before;
import org.junit.Test;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

import static org.junit.Assert.assertEquals;

/**
 * description
 *
 * @Author: siwei
 * @Date: 2018/7/19
 */
public class VarHandleDemo {

    public class HandleTarget {
        public int count = 1;
    }

    private HandleTarget handleTarget = new HandleTarget();
    private VarHandle varHandle;

    @Before
    public void setUp() throws Exception {
        this.handleTarget = new HandleTarget();
        this.varHandle = MethodHandles
                .lookup()
                .findVarHandle(HandleTarget.class, "count", int.class);
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(1, this.varHandle.get(this.handleTarget));
        assertEquals(1, this.varHandle.getVolatile(this.handleTarget));
        assertEquals(1, this.varHandle.getOpaque(this.handleTarget));
        assertEquals(1, this.varHandle.getAcquire(this.handleTarget));
    }
}
