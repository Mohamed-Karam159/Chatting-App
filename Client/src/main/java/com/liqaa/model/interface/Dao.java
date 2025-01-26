import java.util.List;

public interface Dao<T> {

    // Create
    boolean save(T item);

    boolean saveAll(List<T> items);

    // Read
    T getById(int id);

    List<T> getAll();

    // Update
    boolean update(T item);

    // Delete
    boolean delete(int id);
}
