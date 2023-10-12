package standalone.javax.lang.model;

import javax.lang.model.element.TypeElement;

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
  
  public static boolean isUnnamed(TypeElement elem) {
    return (elem instanceof TypeElementShim && ((TypeElementShim)elem).isUnnamed());
  }
}
