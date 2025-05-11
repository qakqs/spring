package cn.mini.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class FileSystemResource implements Resource{

    private final File file;
    private final String path;
    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    public FileSystemResource(String path) {
        this.file = new File(path);
        this.path = file.getPath();
    }



    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    public String getPath() {
        return path;
    }
}
