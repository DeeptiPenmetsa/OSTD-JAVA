package todo;

import org.junit.jupiter.api.Test;
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
}
