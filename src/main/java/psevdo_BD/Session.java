package psevdo_BD;

/**
 * Created by Юлия on 23.04.2016.
 */
/**
 * Session store daily session for each user and url and it's duration
 */
public class Session {
    private Key key;
    private String fileIn;
    private long duration;
    private long timeStart;


    public Session(long timeStart, String user, String url, String file, long duration) {

        this.key = new Key(timeStart, user, url);
        this.fileIn = file;
        this.duration = duration;
        this.timeStart = timeStart;
    }

    @Override
    public boolean equals(Object b) {
        if (b != null && b instanceof Session) {

            return ((Session) b).getKey().equals(this.key)
                    && ((Session) b).getFileIn() != null && ((Session) b).getFileIn().equals(this.fileIn) &&
                    ((Session) b).duration == this.duration && (((Session) b).timeStart == this.timeStart);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ((key.hashCode()) * 19 + fileIn.hashCode() * 19 + Long.hashCode(this.timeStart) + Long.hashCode(duration));
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public void setFileIn(String fileIn) {
        this.fileIn = fileIn;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Key getKey() {
        return key;
    }

    public String getFileIn() {
        return fileIn;
    }

    public long getDuration() {
        return duration;
    }
}

