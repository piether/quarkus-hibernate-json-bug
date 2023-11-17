package be.piether;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TodoListRepository implements PanacheRepositoryBase<TodoList, Long> {
}
