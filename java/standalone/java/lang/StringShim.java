package standalone.java.lang;

import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

import standalone.java.util.stream.StreamShim;

public final class StringShim {
  public static String stripIndent(String str) {
    int length = str.length();
    if (length == 0) {
      return "";
    }
    char lastChar = str.charAt(length - 1);
    boolean optOut = lastChar == '\n' || lastChar == '\r';
    List<String> lines = StreamShim.toList(str.lines());
    final int outdent = optOut ? 0 : outdent(lines);
    return lines.stream().map(line -> {
      int firstNonWhitespace = indexOfNonWhitespace(line);
      int lastNonWhitespace = lastIndexOfNonWhitespace(line);
      int incidentalWhitespace = Math.min(outdent, firstNonWhitespace);
      return firstNonWhitespace > lastNonWhitespace ? "" : line.substring(incidentalWhitespace,
          lastNonWhitespace);
    }).collect(Collectors.joining("\n", "", optOut ? "\n" : ""));
  }

  private static int outdent(List<String> lines) {
    // Note: outdent is guaranteed to be zero or positive number.
    // If there isn't a non-blank line then the last must be blank
    int outdent = Integer.MAX_VALUE;
    for (String line : lines) {
      int leadingWhitespace = indexOfNonWhitespace(line);
      if (leadingWhitespace != line.length()) {
        outdent = Integer.min(outdent, leadingWhitespace);
      }
    }
    String lastLine = lines.get(lines.size() - 1);
    if (lastLine.isBlank()) {
      outdent = Integer.min(outdent, lastLine.length());
    }
    return outdent;
  }

  public static String translateEscapes(String str) {
    if (str.isEmpty()) {
      return "";
    }
    char[] chars = str.toCharArray();
    int length = chars.length;
    int from = 0;
    int to = 0;
    while (from < length) {
      char ch = chars[from++];
      if (ch == '\\') {
        ch = from < length ? chars[from++] : '\0';
        switch (ch) {
          case 'b':
            ch = '\b';
            break;
          case 'f':
            ch = '\f';
            break;
          case 'n':
            ch = '\n';
            break;
          case 'r':
            ch = '\r';
            break;
          case 's':
            ch = ' ';
            break;
          case 't':
            ch = '\t';
            break;
          case '\'':
          case '\"':
          case '\\':
            // as is
            break;
          case '0':
          case '1':
          case '2':
          case '3':
          case '4':
          case '5':
          case '6':
          case '7':
            int limit = Integer.min(from + (ch <= '3' ? 2 : 1), length);
            int code = ch - '0';
            while (from < limit) {
              ch = chars[from];
              if (ch < '0' || '7' < ch) {
                break;
              }
              from++;
              code = (code << 3) | (ch - '0');
            }
            ch = (char) code;
            break;
          case '\n':
            continue;
          case '\r':
            if (from < length && chars[from] == '\n') {
              from++;
            }
            continue;
          default: {
            String msg = String.format("Invalid escape sequence: \\%c \\\\u%04X", ch, (int) ch);
            throw new IllegalArgumentException(msg);
          }
        }
      }

      chars[to++] = ch;
    }

    return new String(chars, 0, to);
  }

  private static int indexOfNonWhitespace(String str) {
    int length = str.length();
    int left = 0;
    while (left < length) {
      char ch = str.charAt(left);
      if (ch != ' ' && ch != '\t' && !Character.isWhitespace(ch)) {
        break;
      }
      left++;
    }
    return left;
  }

  private static int lastIndexOfNonWhitespace(String str) {
    int length = str.length();
    int right = length;
    while (0 < right) {
      char ch = str.charAt(right - 1);
      if (ch != ' ' && ch != '\t' && !Character.isWhitespace(ch)) {
        break;
      }
      right--;
    }
    return right;
  }

  public static String formatted(String str, Object... args) {
    return new Formatter().format(str, args).toString();
  }
}
