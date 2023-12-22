import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import main.java.database.ScorePersistence;
import static org.assertj.core.api.BDDAssertions.then;

public class TestDB {
    @Test
    @DisplayName("Update score in DB")
    public void updateScore() {
        ScorePersistence s = new ScorePersistence();
        s.createSmth(120);

        then(s.getById(1)).isEqualTo("120");
        s.updateScoreById(30, 1);
        then(s.getById(1)).isEqualTo("30");
    }

    @Test
    @DisplayName("Get score in db")
    public void getScore() {
        ScorePersistence s = new ScorePersistence();

        String score = s.getById(1);
        then(score).isNotNull();
        then(score).isEqualTo("30");
    }
}
