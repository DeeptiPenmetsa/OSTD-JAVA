package todo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TodoBusinessImplMockitoInjectMocksTest {

   // public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    TodoService todoService;
    @InjectMocks
    TodoBusinessImpl todoBusiness;
    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock(){
        Mockito.when(todoService.retrieveTodos("Ranga"))
                .thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));
        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToSpring("Ranga");
        assertEquals(2, filteredTodos.size());
        assertEquals(Arrays.asList("Learn Spring MVC", "Learn Spring"), filteredTodos);
    }

    @Test
    public void letsTestDeleteNow() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        Mockito.when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
        todoBusiness.deleteTodosNotRelatedToSpring("Ranga");

        Mockito.verify(todoService).deleteTodo("Learn to Dance");

        Mockito.verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC");

        Mockito.verify(todoService, Mockito.never()).deleteTodo("Learn Spring");

        Mockito.verify(todoService, Mockito.times(1)).deleteTodo("Learn to Dance");
        // atLeastOnce, atLeast

    }

    @Test
    public void captureArgument() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        Mockito.when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
        todoBusiness.deleteTodosNotRelatedToSpring("Ranga");
        Mockito.verify(todoService).deleteTodo(stringArgumentCaptor.capture());

        assertEquals("Learn to Dance", stringArgumentCaptor.getValue());
    }
}
