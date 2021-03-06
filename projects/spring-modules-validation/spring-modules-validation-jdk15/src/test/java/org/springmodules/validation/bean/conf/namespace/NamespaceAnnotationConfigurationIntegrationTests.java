package org.springmodules.validation.bean.conf.namespace;

import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.BindException;
import org.springmodules.validation.bean.BeanValidator;
import org.apache.commons.lang.ArrayUtils;

/**
 * Tests for {@link org.springmodules.validation.bean.conf.loader.xml.DefaultXmlBeanValidationConfigurationLoader}.
 *
 * @author Uri Boness
 */
public class NamespaceAnnotationConfigurationIntegrationTests extends TestCase {

    private BeanValidator validator;

    private BeanValidator validator2;

    protected void setUp() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("annotation-validation.xml", getClass());
        validator = (BeanValidator) context.getBean("validator");
        validator2 = (BeanValidator) context.getBean("validator2");
    }

    public void testLoadConfiguration() throws Exception {

        AnnotatedPerson person = new AnnotatedPerson();
        person.setFirstName("Uri");
        person.setLastName("Boness");
        person.setAge(-1);
        person.setEmail("uri@b");
        person.setPassword("pa");
        person.setConfirmPassword("pa1");

        BindException errors = new BindException(person, "person");
        validator.validate(person, errors);

        assertEquals(1, errors.getGlobalErrorCount());
        assertEquals(3, errors.getFieldErrorCount());

    }

    public void testLoadConfiguration_WithCustomHandlerFailure() throws Exception {

        AnnotatedPerson person = new AnnotatedPerson();
        person.setFirstName("Uri");
        person.setLastName("boness");
        person.setAge(-1);
        person.setEmail("uri@b");
        person.setPassword("pa");
        person.setConfirmPassword("pa1");

        BindException errors = new BindException(person, "person");
        validator.validate(person, errors);

        assertEquals(1, errors.getGlobalErrorCount());

        // testing the test error code converter
        String[] codes = errors.getGlobalError().getCodes();
        assertTrue(ArrayUtils.contains(codes, "test.passwords.do.not.match"));

        assertEquals(4, errors.getFieldErrorCount());

    }

    public void testLoadConfiguration_2() throws Exception {

        AnnotatedPerson person = new AnnotatedPerson();
        person.setFirstName("Uri");
        person.setLastName("boness");
        person.setAge(-1);
        person.setEmail("uri@b");
        person.setPassword("pa");
        person.setConfirmPassword("pa1");

        BindException errors = new BindException(person, "person");
        validator2.validate(person, errors);

        assertEquals(1, errors.getGlobalErrorCount());

        // testing the test error code converter
        String[] codes = errors.getGlobalError().getCodes();
        assertTrue(ArrayUtils.contains(codes, "test.passwords.do.not.match"));

        assertEquals(4, errors.getFieldErrorCount());

    }

}
