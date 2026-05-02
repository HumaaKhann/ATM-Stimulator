import java.util.Random;

public class GenerateId {

    public  int generateRandomId() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); // 6-digit ID
    }
}