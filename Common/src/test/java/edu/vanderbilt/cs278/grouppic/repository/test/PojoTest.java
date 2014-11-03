package edu.vanderbilt.cs278.grouppic.repository.test;

/**
 * Created by andrewbachman on 10/28/14.
 */

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.PojoValidator;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsRule;
import com.openpojo.validation.rule.impl.NoStaticExceptFinalRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.DefaultValuesNullTester;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import edu.vanderbilt.cs278.grouppic.repository.Picture;

/**
 * This is a test that uses a helper library to find all Plain Old Java Objects (Pojos)
 * and test them. The class automates the testing of simpler getter/setter classes so
 * that you can have full code coverage without much work. This test also enforces a
 * variety of design rules, such as no public non-final member variables.
 *
 * @author jules
 *
 */
public class PojoTest {
    // Configured for expectation, so we know when a class gets added or removed.
    private static final int EXPECTED_CLASS_COUNT = 3;

    // The package to com.magnum.videoup.test
    private static final String POJO_PACKAGE = Picture.class.getPackage().getName();

    private List<PojoClass> pojoClasses;
    private PojoValidator pojoValidator;

    @Before
    public void setup() {
        pojoClasses = PojoClassFactory.getPojoClasses(POJO_PACKAGE, new FilterPackageInfo());

        pojoValidator = new PojoValidator();

        // Create Rules to validate structure for POJO_PACKAGE
        pojoValidator.addRule(new NoPublicFieldsRule());
        pojoValidator.addRule(new NoStaticExceptFinalRule());
        pojoValidator.addRule(new GetterMustExistRule());
        pojoValidator.addRule(new SetterMustExistRule());

        // Create Testers to validate behaviour for POJO_PACKAGE
        // pojoValidator.addTester(new DefaultValuesNullTester());
        pojoValidator.addTester(new SetterTester());
        pojoValidator.addTester(new GetterTester());
    }

    @Test
    public void ensureExpectedPojoCount() {
        Affirm.affirmEquals("Classes added / removed?", EXPECTED_CLASS_COUNT, pojoClasses.size());
    }

    @Test
    public void testPojoStructureAndBehavior() {
        for (PojoClass pojoClass : pojoClasses) {
            pojoValidator.runValidation(pojoClass);
        }
    }
}