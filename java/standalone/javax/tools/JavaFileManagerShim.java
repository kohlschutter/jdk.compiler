package standalone.javax.tools;

import java.io.IOException;

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
    } else {
      return mgr.getJavaFileForOutput(location, className, kind, siblingFrom(originatingFiles));
    }
  }

  static FileObject getFileForOutputForOriginatingFiles(JavaFileManager mgr, Location location,
      String packageName, String relativeName, FileObject... originatingFiles) throws IOException {
    if (mgr instanceof JavaFileManagerShim) {
      return ((JavaFileManagerShim) mgr).getFileForOutputForOriginatingFiles(location, packageName,
          relativeName, originatingFiles);
    } else {
      return mgr.getFileForOutput(location, packageName, relativeName,
          siblingFrom(originatingFiles));
    }
  }
}
