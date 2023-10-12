package standalone.javax.lang.model.element;

import java.util.Collections;
import java.util.HashSet;
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
  default Set<Modifier> getModifiersStandalone() {
    Set<javax.lang.model.element.Modifier> modifiers = getModifiers();
    if (modifiers.isEmpty()) {
      return Collections.emptySet();
    }
    Set<Modifier> standalone = new HashSet<>(modifiers.size());
    for (javax.lang.model.element.Modifier m : modifiers) {
      standalone.add(Modifier.valueOf(m.name()));
    }
    return Collections.unmodifiableSet(standalone);
  }

  /**
   * @deprecated
   * @see #getModifiersStandalone()
   * @see #containsModifier(Element, Modifier)
   */
  @Override
  @Deprecated
  Set<javax.lang.model.element.Modifier> getModifiers();
  
  ElementKind getKindStandalone();

  /**
   * @deprecated
   * @see #getKindStandalone()
   */
  @Override
  @Deprecated
  javax.lang.model.element.ElementKind getKind();

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
  
  static ElementKind getKindStandalone(Element elem) {
    if (!(elem instanceof ElementShim)) {
      return ElementKind.valueOf(elem.getKind().name());
    }
    return ((ElementShim)elem).getKindStandalone();
  }
  
  static Set<Modifier> getModifiersStandalone(Element elem) {
    if (!(elem instanceof ElementShim)) {
      Set<javax.lang.model.element.Modifier> modifiers = elem.getModifiers();
      if (modifiers.isEmpty()) {
        return Collections.emptySet();
      }
      Set<Modifier> standalone = new HashSet<>(modifiers.size());
      for (javax.lang.model.element.Modifier m : modifiers) {
        standalone.add(Modifier.valueOf(m.name()));
      }
      return Collections.unmodifiableSet(standalone);
    }
    return ((ElementShim)elem).getModifiersStandalone();
  }
}
