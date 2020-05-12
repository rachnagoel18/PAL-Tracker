package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    HashMap<Long,TimeEntry> repo = new HashMap();
    long counter =1l;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(counter);
        repo.put(counter,timeEntry);

        counter++;
        return timeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {

        return repo.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> list = new ArrayList<>();
        list.addAll(repo.values());
        return list;
    }

    @Override
    public TimeEntry update(long id, TimeEntry entry) {
        if (find(id) == null) return null;
        entry.setId(id);
        repo.replace(id,entry);
        return entry;


       /* if(repo.containsKey(id)) {
            entry.setId(id);
            TimeEntry updtae= repo.replace(id, entry);
            return updtae;
        }*/

        //return null;

    }

    @Override
    public void delete(long id) {
            repo.remove(id);
    }
}
