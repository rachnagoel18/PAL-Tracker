package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    TimeEntryRepository repo;

    public TimeEntryController(TimeEntryRepository repository) {
        repo = repository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry created = repo.create(timeEntryToCreate);
        return new ResponseEntity(created, HttpStatus.CREATED);
    }
    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {

        TimeEntry entry= repo.find(timeEntryId);
        if(entry==null){
            return new ResponseEntity(entry,HttpStatus.NOT_FOUND);
        }

            return new ResponseEntity(entry,HttpStatus.OK);


    }
    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list= repo.list();
        if(list!=null) {
            return new ResponseEntity(list, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId,@RequestBody TimeEntry expected) {
       TimeEntry entry = repo.update(timeEntryId, expected);
       if(entry!=null)
       return new ResponseEntity(entry, HttpStatus.OK);
       else
           return new ResponseEntity(entry, HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        repo.delete(timeEntryId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
