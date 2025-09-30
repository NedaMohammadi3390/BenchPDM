package file_generation;

import com.sun.istack.internal.NotNull;
import utils.Corrections;

import java.io.File;

class PackageFileGenerator {
    private final String defaultPath;

    /**
     * Spring projects are always generated under src/main/java packages.
     */
    PackageFileGenerator(@NotNull String defaultPath) {
        this.defaultPath = defaultPath;
    }

    String createPackage(String projectName, String... paths) {
        if (Corrections.isInvalid(projectName)) return null;
        String newDefaultPath = defaultPath + "\\" + projectName + "\\src\\main\\java";

        new File(newDefaultPath).mkdirs();
        for (String path : paths) {
            if (Corrections.isInvalid(path)) return null;
            if (path.contains("."))
                path = Corrections.correctPath(path);
            String p = newDefaultPath + "\\" + path;
            new File(p).mkdirs();
            return p;
        }
        return null;
    }
}
