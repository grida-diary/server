package org.grida.study;

public class TestTimeChecker {

    public static long execute(Runnable action) {
        long startTime = System.currentTimeMillis();
        action.run();
        return System.currentTimeMillis() - startTime;
    }
}
