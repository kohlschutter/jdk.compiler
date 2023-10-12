package standalone.javax.lang.model.element;

import java.util.Set;

import javax.lang.model.element.Element;

public interface ElementShim extends Element {
  /**
   * Returns the modifiers of this element, excluding annotations. Implicit modifiers, such as the
   * {@code public} and {@code
   * static} modifiers of interface members (JLS section {@jls 9.3}), are included.
   *
   * @return the modifiers of this element, or an empty set if there are none
   */
  Set<Modifier> getModifiersStandalone();

  @Override
  Set<javax.lang.model.element.Modifier> getModifiers();

  static boolean containsModifier(Element elem, javax.lang.model.element.Modifier m) {
    if (!(elem instanceof ElementShim)) {
      return elem.getModifiers().contains(m);
    }
    return ((ElementShim) elem).getModifiersStandalone().contains(Modifier.valueOf(m.name()));
  }

  static boolean containsModifier(Element elem, Modifier m) {
    if (!(elem instanceof ElementShim)) {
      Set<javax.lang.model.element.Modifier> modifiers = elem.getModifiers();
      try {
        return modifiers.contains(javax.lang.model.element.Modifier.valueOf(m.name()));
      } catch (IllegalArgumentException e) {
        return false;
      }
    }
    return ((ElementShim) elem).getModifiersStandalone().contains(m);
  }
}
