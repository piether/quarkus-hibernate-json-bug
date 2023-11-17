package be.piether;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
public class TodoList {

    protected TodoList() {
        todos = new ArrayList<>();
    }

    public TodoList(Long id) {
        this();
        this.id = id;
    }

    @Id
    public Long id;
    public String title;

    @JdbcTypeCode(SqlTypes.JSON)
    public List<Todo> todos;
}
