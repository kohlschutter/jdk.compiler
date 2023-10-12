package standalone.javax.lang.model.element;

import java.util.List;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

public interface TypeElementShim extends TypeElement {
  /**
   * {@return {@code true} if this is an unnamed class and {@code
   * false} otherwise}
   *
   * @implSpec
   * The default implementation of this method returns {@code false}.
   *
   * @jls 7.3 Compilation Units
   * @since 21
   */
//  @PreviewFeature(feature=PreviewFeature.Feature.UNNAMED_CLASSES,
//                  reflective=true)
  default boolean isUnnamed() {
      return false;
  }
  
  /**
   * Returns the permitted classes of this class or interface
   * element in declaration order.
   *
   * @implSpec The default implementations of this method returns an
   * empty and unmodifiable list.
   *
   * @return the permitted classes, or an empty list if there are none
   *
   * @since 15
   */
  default List<? extends TypeMirror> getPermittedSubclasses() {
      return List.of();
  }
  
  default List<? extends RecordComponentElement> getRecordComponentsStandalone() {
    return List.of();
  }
  
  public static boolean isUnnamed(TypeElement elem) {
    return (elem instanceof TypeElementShim && ((TypeElementShim)elem).isUnnamed());
  }
  
  public static List<? extends TypeMirror> getPermittedSubclasses(TypeElement elem) {
    if (elem instanceof TypeElementShim) {
      return ((TypeElementShim)elem).getPermittedSubclasses();
    } else {
      return List.of();
    }
  }

  public static List<? extends RecordComponentElement> getRecordComponentsStandalone(TypeElement elem) {
    if (elem instanceof TypeElementShim) {
      return ((TypeElementShim)elem).getRecordComponentsStandalone();
    } else {
      return List.of();
    }
  }
}
