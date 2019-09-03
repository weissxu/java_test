package com.weiss.jdk.jdk9;

import java.io.IOException;

/**
 * description
 *
 * @Author: siwei
 * @Date: 2018/7/18
 */
public class ProcessHandleDemo {

    public static void main(String[] args) throws IOException {
        final ProcessBuilder processBuilder = new ProcessBuilder("top")
                .inheritIO();
        final ProcessHandle processHandle = processBuilder.start().toHandle();
        processHandle.onExit().whenCompleteAsync((handle, throwable) -> {
            if (throwable == null) {
                System.out.println(handle.pid());
            } else {
                throwable.printStackTrace();
            }
        });
    }
}
