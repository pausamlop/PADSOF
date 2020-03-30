package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import across.application.Application;
import across.main.Main;

public class MainTest {

    @Test 
	public void testLoadData() {
        Application app = Main.loadData();
        assertNotNull(app);
	}
    
    @Test
	public void testSaveData() {
        File before = new File("app.objectData");
        Main.saveData(Application.getApplication());

        File after = new File("app.objectData");
        assertNotSame(before, after);
	}

}
