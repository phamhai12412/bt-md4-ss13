package bt.service;

import java.util.List;

public interface IGenericService<T,E> {
    List<T> getall();
    T finbyid(E e);
    void delete(E e);
    void save(T t);




}
