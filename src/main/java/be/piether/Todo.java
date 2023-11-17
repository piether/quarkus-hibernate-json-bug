package be.piether;

public class Todo {
    public String title;
    public boolean completed;

    Todo() {
    }

    public Todo(String title) {
        this.title = title;
        completed = false;
    }
}
