package pl.tomaja.atbackup.io.facade;

import java.io.File;

/**
 * Created by Adam on 21.07.2016.
 */
public class TestIO extends RealIO {

    @Override
    public boolean exists(File file) {
        return true;
    }

    @Override
    public boolean isDirectory(File file) {
        return true;
    }

    @Override
    public File findRootByName(String name) {
        return new File(name + ":::");
    }
}
