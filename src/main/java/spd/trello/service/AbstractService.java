package spd.trello.service;

public abstract class AbstractService<T> {
    public abstract T create();

    public abstract void update(int index, T t);

    public abstract void print(T t);
}
