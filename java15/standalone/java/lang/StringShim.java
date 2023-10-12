package standalone.java.lang;

import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

public final class StringShim {
  public static String stripIndent(String str) {
    return str.stripIndent();
  }

  public static String translateEscapes(String str) {
    return str.translateEscapes();
  }
  
  public static String formatted(String str, Object... args) {
    return str.formatted(args);
  }
}
