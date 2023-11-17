package be.piether;

import io.quarkus.narayana.jta.QuarkusTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class TodoIntegrationTest {

    @Inject
    TodoListRepository repository;

    @Test
    public void testChangedToDoIsPersisted() {
        QuarkusTransaction.requiringNew().run(() -> {
            TodoList todoList = new TodoList(1L);
            todoList.title = "My Todo List";
            todoList.todos.add(new Todo("Fix bug"));
            repository.persist(todoList);
        });
        QuarkusTransaction.requiringNew().run(() -> {
            TodoList todoList = repository.findById(1L);
            todoList.todos.get(0).completed = true;
            repository.persist(todoList);
        });
        QuarkusTransaction.requiringNew().run(() -> {
            TodoList todoList = repository.findById(1L);
            assertTrue(todoList.todos.get(0).completed, "The todo is complete");
        });
    }

    @Test
    public void testChangedToDoIsPersistedWhenThereAreOtherChangesToTheEntity() {
        QuarkusTransaction.requiringNew().run(() -> {
            TodoList todoList = new TodoList(2L);
            todoList.title = "My Todo List";
            todoList.todos.add(new Todo("Fix bug"));
            repository.persist(todoList);
        });
        QuarkusTransaction.requiringNew().run(() -> {
            TodoList todoList = repository.findById(2L);
            todoList.todos.get(0).completed = true;
            todoList.title = "Changed title";
            repository.persist(todoList);
        });
        QuarkusTransaction.requiringNew().run(() -> {
            TodoList todoList = repository.findById(2L);
            assertTrue(todoList.todos.get(0).completed);
        });
    }
}
