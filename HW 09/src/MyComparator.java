import java.util.Comparator;

class MyComparator implements Comparator<Player> {
@Override

public int compare(Player o1, Player o2) {
    if (o1.getScore() > o2.getScore()) {
        return -1;
    } else if (o1.getScore() < o2.getScore()) {
        return 1;
    }
    return 0;
	}
}