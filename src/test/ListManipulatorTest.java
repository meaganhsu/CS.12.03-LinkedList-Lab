package test;

import impl.ListManipulator;
import interfaces.IListManipulator;

/**
 * Concrete JUnit test class (subclass of ListManipulatorTest) for testing the IterativeListManipulator implementation.
 *
 */
public class ListManipulatorTest extends AbstractListManipulatorTest {

    @Override
    public IListManipulator makeListManipulator() {
        return new ListManipulator();
    }

}
