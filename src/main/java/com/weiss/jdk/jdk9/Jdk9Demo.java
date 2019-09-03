package com.weiss.jdk.jdk9;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * description
 *
 * @Author: siwei
 * @Date: 2018/7/18
 */
public class Jdk9Demo {

    @Test
    public void testDropWhile() throws Exception {
        final long count = Stream.of(1, 2, 3, 4, 5)
                .dropWhile(i -> i % 2 != 0)
                .count();
        System.out.println(count);

        List<Integer> list = Stream.of(1, 2, 3, 4, 5)
                .dropWhile(i -> i % 2 != 0).collect(Collectors.toList());
        System.out.println(list);

        System.out.println(1 % 2);
        System.out.println(2 % 2);
        System.out.println(3 % 2);
        System.out.println(4 % 2);
        System.out.println(5 % 2);

        assertEquals(4, count);
    }

    @Test
    public void testTakeWhile() throws Exception {
        final long count = Stream.of(1, 2, 3, 4, 5)
                .takeWhile(i -> i % 2 != 0)
                .count();
        System.out.println(count);

        assertEquals(4, count);
    }


    @Test
    public void testFlatMapping() throws Exception {
        final Set<Integer> result = Stream.of("a", "ab", "abc")
                .collect(Collectors.flatMapping(v -> v.chars().boxed(),
                        Collectors.toSet()));
        System.out.println(result);
        assertEquals(3, result.size());
    }

    @Test
    public void testStream() throws Exception {
        final long count = Stream.of(
                Optional.of(1),
                Optional.empty(),
                Optional.of(2)
        ).flatMap(Optional::stream)
                .count();
        assertEquals(2, count);
    }
}
