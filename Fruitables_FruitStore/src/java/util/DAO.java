package util;

import java.util.List;

public interface DAO<T> {
    T get (Object[] cond);
    List<T> getAll ();
    boolean save (T t);
    boolean update (T t1, String[] params);
    boolean delete (T t);
}
