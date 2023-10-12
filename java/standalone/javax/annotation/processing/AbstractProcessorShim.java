package standalone.javax.annotation.processing;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.SupportedSourceVersion;
import javax.tools.Diagnostic;

import standalone.javax.lang.model.SourceVersion;

public abstract class AbstractProcessorShim extends AbstractProcessor {
  /**
   * If the processor class is annotated with {@link
   * SupportedSourceVersion} or {@link SupportedSourceVersionStandalone}, return the source version in the
   * annotation.  If the class is not so annotated, {@link
   * SourceVersion#RELEASE_6} is returned.
   *
   * @return the latest source version supported by this processor
   */
  public SourceVersion getSupportedSourceVersionStandalone() {
    SupportedSourceVersionStandalone ssvsa = this.getClass().getAnnotation(SupportedSourceVersionStandalone.class);
    if (ssvsa != null) {
      return ssvsa.value();
    }

    SupportedSourceVersion ssv = this.getClass().getAnnotation(SupportedSourceVersion.class);
    SourceVersion sv = null;
      if (ssv == null) {
          sv = SourceVersion.RELEASE_6;
          if (isInitialized())
              processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING,
                                                       "No SupportedSourceVersion annotation " +
                                                       "found on " + this.getClass().getName() +
                                                       ", returning " + sv + ".");
      } else {
          sv = SourceVersion .values()[ssv.value().ordinal()];
      }
      return sv;
  }
}
