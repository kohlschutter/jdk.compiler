package standalone.java.util.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class StreamShim {
  @SuppressWarnings("unchecked")
  public static <T> List<T> toList(Stream<T> stream) {
      return (List<T>) Collections.unmodifiableList(new ArrayList<>(Arrays.asList(stream.toArray())));
  }
}
