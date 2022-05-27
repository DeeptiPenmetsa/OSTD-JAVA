package todo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UtilityClass.class})
public class PowerMockitoMockingStaticMethodTest {

    @Mock
    Dependency dependency;
    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test
    public void powerMockito_MockingAStaticMethodCall(){

        Mockito.when(dependency.retrieveAllStats()).thenReturn(Arrays.asList(1,2,3));
        PowerMockito.mockStatic(UtilityClass.class);
        Mockito.when(UtilityClass.staticMethod(anyLong())).thenReturn(150);
        assertEquals(150, systemUnderTest.methodCallingAStaticMethod());
        PowerMockito.verifyStatic();;
        UtilityClass.staticMethod(1+2+3);
        PowerMockito.verifyStatic(Mockito.times(1));

    }
}
