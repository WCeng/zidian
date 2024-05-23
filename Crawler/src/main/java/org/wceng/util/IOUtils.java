package org.wceng.util;

import java.io.InputStream;
import java.util.Scanner;

public class IOUtils {

    public static String toString(InputStream in) {
        Scanner scanner = new Scanner(in).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
