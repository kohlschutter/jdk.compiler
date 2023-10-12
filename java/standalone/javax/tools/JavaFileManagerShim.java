package standalone.javax.tools;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;

public interface JavaFileManagerShim extends JavaFileManager {

  default JavaFileObject getJavaFileForOutputForOriginatingFiles(Location location,
      String className, Kind kind, FileObject... originatingFiles) throws IOException {
    return getJavaFileForOutput(location, className, kind, siblingFrom(originatingFiles));
  }

  default FileObject getFileForOutputForOriginatingFiles(Location location, String packageName,
      String relativeName, FileObject... originatingFiles) throws IOException {
    return getFileForOutput(location, packageName, relativeName, siblingFrom(originatingFiles));
  }

  private static FileObject siblingFrom(FileObject[] originatingFiles) {
    return originatingFiles != null && originatingFiles.length > 0 ? originatingFiles[0] : null;
  }

  static JavaFileObject getJavaFileForOutputForOriginatingFiles(JavaFileManager mgr,
      Location location, String className, Kind kind, FileObject... originatingFiles)
      throws IOException {
    if (mgr instanceof JavaFileManagerShim) {
      return ((JavaFileManagerShim) mgr).getJavaFileForOutputForOriginatingFiles(location,
          className, kind, originatingFiles);
    }

    try {
      Method m = mgr.getClass().getMethod("getJavaFileForOutputForOriginatingFiles", Location.class,
          String.class, Kind.class, FileObject[].class);
      return (JavaFileObject) m.invoke(mgr, location, className, kind, originatingFiles);
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException
        | IllegalArgumentException e) {
      return mgr.getJavaFileForOutput(location, className, kind, siblingFrom(originatingFiles));
    } catch (InvocationTargetException e) {
      Throwable cause = e.getCause();
      if (cause instanceof RuntimeException) {
        throw ((RuntimeException) cause);
      } else if (cause instanceof Error) {
        throw ((Error) cause);
      } else if (cause instanceof IOException) {
        throw ((IOException) cause);
      } else {
        throw new IllegalStateException(e);
      }
    }
  }

  static FileObject getFileForOutputForOriginatingFiles(JavaFileManager mgr, Location location,
      String packageName, String relativeName, FileObject... originatingFiles) throws IOException {
    if (mgr instanceof JavaFileManagerShim) {
      return ((JavaFileManagerShim) mgr).getFileForOutputForOriginatingFiles(location, packageName,
          relativeName, originatingFiles);
    }

    try {
      Method m = mgr.getClass().getMethod("getFileForOutputForOriginatingFiles", Location.class,
          String.class, String.class, FileObject[].class);
      return (JavaFileObject) m.invoke(mgr, location, packageName, relativeName, originatingFiles);
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException
        | IllegalArgumentException e) {
      return mgr.getFileForOutput(location, packageName, relativeName, siblingFrom(
          originatingFiles));
    } catch (InvocationTargetException e) {
      Throwable cause = e.getCause();
      if (cause instanceof RuntimeException) {
        throw ((RuntimeException) cause);
      } else if (cause instanceof Error) {
        throw ((Error) cause);
      } else if (cause instanceof IOException) {
        throw ((IOException) cause);
      } else {
        throw new IllegalStateException(e);
      }
    }
  }
}
