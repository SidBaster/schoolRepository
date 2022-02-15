package jdbc.schoolAplication.util;

import java.util.List;

public interface Generator<T> {
    public List<T> generate();
}
