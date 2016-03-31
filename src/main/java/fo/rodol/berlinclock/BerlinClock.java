package fo.rodol.berlinclock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.time.LocalTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * Represents a Berlin Clock.
 * This implementations uses integers to represent whether a position is lit as 1 and as 0 when unlit.
 *
 * From the Wikipedia:
 *
 * The Mengenlehreuhr consists of 24 lights which are divided into one circular blinking yellow light on top to denote
 * the seconds, two top rows denoting the hours and two bottom rows denoting the minutes.
 *
 * The top row of four red fields denote five full hours each, alongside the second row, also of four red fields, which
 * denote one full hour each, displaying the hour value in 24-hour format. The third row consists of eleven
 * yellow-and-red fields, which denote five full minutes each (the red ones also denoting 15, 30 and 45 minutes past),
 * and the bottom row has another four yellow fields, which mark one full minute each. The round yellow light on top
 * blinks to denote even- (when lit) or odd-numbered (when unlit) seconds.
 *
 * Credits to:
 * http://technologyconversations.com/2014/02/25/java-8-tutorial-through-katas-berlin-clock-easy/
 */
public class BerlinClock {

    int evenSeconds; // 1 if even seconds, 0 if odd

    int[] firstRow = new int[4];

    int[] secondRow = new int[4];

    int[] thirdRow = new int[11];

    int[] fourthRow = new int[4];

    static DateTimeFormatter formatter = ofPattern("HH:mm:ss");

    /**
     * Returns a BerlinClock instance based on a time string that conforms to the pattern letters HH:mm:ss
     *
     * @param time The time expressed in a java.time.format.DateTimeFormatter pattern HH:mm:ss
     * @return a BerlinClock instance that corresponds to the time
     */
    public static BerlinClock fromTime(String time) {

        LocalTime date = parse(time, formatter);

        BerlinClock berlinClock = new BerlinClock();
        berlinClock.setEvenSeconds(date.getSecond() % 2);

        berlinClock.setFirstRow(getOnOff(4, getTopNumberOfOnSigns(date.getHour())));
        berlinClock.setSecondRow(getOnOff(4, getBottomNumberOfOnSigns(date.getHour())));
        berlinClock.setThirdRow(getOnOff(11, getTopNumberOfOnSigns(date.getMinute())));
        berlinClock.setFourthRow(getOnOff(4, getBottomNumberOfOnSigns(date.getMinute())));

        return berlinClock;
    }

    private static int[] getOnOff(int lamps, int onSigns) {

        int[] out = new int[lamps];
        int idx;
        for (idx = 0; idx < onSigns; idx++) {
            out[idx] = 1;
        }
        for (; idx < (lamps - onSigns); idx++) {
            out[idx] = 0;
        }
        return out;
    }

    private static int getTopNumberOfOnSigns(int number) {
        return (number - (number % 5)) / 5;
    }

    private static int getBottomNumberOfOnSigns(int number) {
        return number % 5;
    }

    public int getEvenSeconds() {
        return evenSeconds;
    }

    public int[] getFirstRow() {
        return firstRow;
    }

    public int[] getSecondRow() {
        return secondRow;
    }

    public int[] getThirdRow() {
        return thirdRow;
    }

    public int[] getFourthRow() {
        return fourthRow;
    }

    public void setEvenSeconds(int evenSeconds) {
        this.evenSeconds = evenSeconds;
    }

    public void setFirstRow(int[] firstRow) {
        this.firstRow = firstRow;
    }

    public void setSecondRow(int[] secondRow) {
        this.secondRow = secondRow;
    }

    public void setThirdRow(int[] thirdRow) {
        this.thirdRow = thirdRow;
    }

    public void setFourthRow(int[] fourthRow) {
        this.fourthRow = fourthRow;
    }
}
