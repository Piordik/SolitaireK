package main.java.database;

import java.util.Map;

public class ScorePersistence {

    private static final String TABLE_NAME = "game1";
    private static final String ID_NAME = "id";
    private static final String SCORE = "score";

    private final MyDataBase db = MyDataBase.getInstance();

    public void newScore(int score) {
        String sql = """
                insert into game.game1
                (score)
                values
                (%d)
                """;
        db.execute(String.format(sql, score));
    }

    public String getById(int id) {
        Map<String, String> fromDB = db.selectById(
                id,
                TABLE_NAME,
                ID_NAME,
                SCORE
        );
        if (fromDB == null || fromDB.isEmpty()) {
            return null;
        }
        return fromDB.get(SCORE);
    }

    public void deleteById(int id) {
        String sql = """
                delete from game.game1 where id = %d;
                """;
        db.execute(String.format(sql, id));
    }

    public void updateScoreById(int score, int id) {
        String sql = """
                UPDATE game.game1 SET score = %d
                WHERE id = %d;
                """;
        db.execute(String.format(sql, score, id));
    }
}
