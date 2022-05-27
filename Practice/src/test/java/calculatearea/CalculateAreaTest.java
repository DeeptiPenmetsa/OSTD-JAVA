package calculatearea;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CalculateAreaTest {
    RectangleService rectangleService;
    CircleService circleService;
    SquareService squareService;

    CalculateArea calculateArea;

    @BeforeEach
    public void init(){
        rectangleService = Mockito.mock(RectangleService.class);
        circleService = Mockito.mock(CircleService.class);
        squareService = Mockito.mock(SquareService.class);
        calculateArea = new CalculateArea(squareService, rectangleService, circleService);
    }

    @Test
    public void calculateRectangleAreaTest(){
        Mockito.when(rectangleService.area(5.0d, 4.0d)).thenReturn(20.0d);
        Double calculatedArea = calculateArea.calculateArea(Type.RECTANGLE, 5.0d, 4.0d);
        Assertions.assertEquals(new Double(20.0d), calculatedArea);
    }

}
