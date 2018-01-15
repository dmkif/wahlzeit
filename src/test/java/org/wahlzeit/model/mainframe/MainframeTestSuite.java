package org.wahlzeit.model.mainframe;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Testsuite for package org.wahlzeit.model
 */

@RunWith(Suite.class)
@Suite.SuiteClasses( {
	org.wahlzeit.model.mainframe.MainframePhotoFactoryTest.class,
	org.wahlzeit.model.mainframe.MainframePhotoTest.class,
	org.wahlzeit.model.mainframe.MainframeTest.class,
	org.wahlzeit.model.mainframe.MainframeTypeTest.class,
	org.wahlzeit.model.mainframe.MainframeManagerTest.class
})

public class MainframeTestSuite {

}
