package psevdo_BD;

import cons.Const;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Юлия on 24.04.2016.
 */
/**
 * Store sessions
 */
public class Storage {
    TreeMap<Key, HashSet<Session>> mapKey;

    public Storage() {
        mapKey = new TreeMap<Key, HashSet<Session>>();
    }

    public void printAll(String fileOut) {
        if (fileOut != null) {
            BufferedWriter bw = null;

            long dayold = -1;
            Key keyCur;
            try {
                bw = new BufferedWriter(new FileWriter(fileOut));
                for (Map.Entry<Key, HashSet<Session>> entry : mapKey.entrySet()) {
                    keyCur = entry.getKey();
                    HashSet<Session> sesionsCur = mapKey.get(keyCur);
                    if (keyCur.getDay() != (dayold)) {
                        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
                        bw.write(format.format(new Date((keyCur.getDay()) * Const.secInDay * Const.millSinS)) + "\n");
                        dayold = keyCur.getDay();
                    }
                    bw.write(keyCur.getUser() + ',' + keyCur.getUrl() + ',' + avr(sesionsCur) + "\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }

        }
    }

    /**
     * Check how much sessions in one line, put them all in storage
     */
    public synchronized void put(String line) {

        if (line != null) {
            Boolean smthNew = false;
            String[] entity = line.split(Const.cvsSplitBy);
            //0 - date, 1 - user, 2 - url, 3 - duration
            Long timeStart = Long.parseLong(entity[0]);
            Long duration = Long.parseLong(entity[3]);

            String file = "";


            if (Const.dayOf(timeStart) != Const.dayOf(timeStart + duration)) {

                Session session = new Session(timeStart, entity[1], entity[2], file, (Const.dayOf(timeStart) + 1) * Const.secInDay - timeStart);
                if (this.put(session)) smthNew = true;
                session = new Session(Const.dayOf(timeStart + duration) * Const.secInDay, entity[1], entity[2], file, (timeStart + duration) - Const.dayOf(timeStart + duration) * Const.secInDay);
                if (this.put(session)) smthNew = true;
                for (long i = Const.dayOf(timeStart) + 1; i < Const.dayOf(timeStart + duration); i++) {
                    session = new Session(i * Const.secInDay, entity[1], entity[2], file, Const.secInDay);
                    if (this.put(session)) smthNew = true;
                }
            } else {
                Session session = new Session(timeStart, entity[1], entity[2], file, duration);
                if (this.put(session)) smthNew = true;
            }
            if (smthNew) {
                printAll(Const.folderFileOut + "/" + Const.fileOut);
            }


        }
    }
    /**
     * if session is new, put it and return true, otherwise return false
     */

    private Boolean put(Session session) {
        HashSet<Session> sessions = mapKey.get(session.getKey());
        if (sessions == null) {
            sessions = new HashSet();
            sessions.add(session);
            mapKey.put(session.getKey(), sessions);
            return true;
        } else {
            if (!sessions.contains(session)) {
                sessions.add(session);
                return true;
            }
        }
        return false;
    }

    /**
     * count average duration
     */
    private static long avr(HashSet<Session> sessions) {
        if (!sessions.isEmpty()) {
            Iterator<Session> iterator = sessions.iterator();
            long sum = 0;
            while (iterator.hasNext()) {
                sum = sum + iterator.next().getDuration();
            }
            return sum / sessions.size();
        } else return 0;
    }


}
