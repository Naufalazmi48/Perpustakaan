package repository;

import model.History;

import java.util.List;

public interface IHistoryRepository {
    List<History> getHistory();
    long countTimeLate(int nim,int idBook);
}
