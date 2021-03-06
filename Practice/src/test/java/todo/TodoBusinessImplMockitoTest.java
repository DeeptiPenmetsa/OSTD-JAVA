package todo;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoBusinessImplMockitoTest {
    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock(){
        TodoService mockTodoService = Mockito.mock(TodoService.class);
        Mockito.when(mockTodoService.retrieveTodos("Ranga"))
                .thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));
        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(mockTodoService);
        List<String> filteredTodos = todoBusiness.retrieveTodosRelatedToSpring("Ranga");
        assertEquals(2, filteredTodos.size());
        assertEquals(Arrays.asList("Learn Spring MVC", "Learn Spring"), filteredTodos);
    }

    @Test
    public void letsTestDeleteNow() {

        TodoService todoService = Mockito.mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");

        Mockito.when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");

        Mockito.verify(todoService).deleteTodo("Learn to Dance");

        Mockito.verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC");

        Mockito.verify(todoService, Mockito.never()).deleteTodo("Learn Spring");

        Mockito.verify(todoService, Mockito.times(1)).deleteTodo("Learn to Dance");
        // atLeastOnce, atLeast

    }

    @Test
    public void captureArgument() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        TodoService todoService = Mockito.mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        Mockito.when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
        Mockito.verify(todoService).deleteTodo(argumentCaptor.capture());

        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }
}
