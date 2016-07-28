package utils.files;

import boot.EncryptModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.hamcrest.Matcher;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.internal.matchers.Equals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.core.Every.*;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

/**
 * Created by mzeus on 26/07/16.
 */
public class DirectoryFilesManagerTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private DirectoryFilesManager manager;

    @Before
    public void warmUp() throws IOException {
        folder.create();
        Injector injector = Guice.createInjector(new EncryptModule(folder.getRoot()));
        manager = injector.getInstance(DirectoryFilesManager.class);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void getOutputFile() throws Exception {
        for (int i = 0; i < 100; i++) {
            folder.newFile();
        }
        assertThat(manager.size(), not(0));
        manager.getOutputFile(101);
        CountDownLatch latch = new CountDownLatch(100);
        ArrayList<File> files = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ExecutorService service = Executors.newFixedThreadPool(100);
            int finalI = i;
            service.submit(() -> {
                latch.countDown();
                try {
                    latch.await();
                    files.add(manager.getOutputFile(finalI));
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (File file :
                files) {
            assertThat(Collections.frequency(files, file), Is.is(1));
        }
    }

}