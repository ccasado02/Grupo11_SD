package com.urjc.grupo11.practica1;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/beats")
public class BeatAPIController {
    // Autowired BeatService to handle Beat operations
    @Autowired
    private BeatService beats;

    // Get all Beats
    @GetMapping("/")
    public Collection<Beat> getBeats(){
        return beats.findAll();
    }

    // Get a specific Beat by ID
    @GetMapping("/{id}")
    public ResponseEntity<Beat> getBeat(@PathVariable Long id){
        Beat beat = beats.findById(id);
        if(beat != null){
            return ResponseEntity.ok(beat);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new Beat
    @PostMapping("/") 
    public ResponseEntity<Beat> createBeat(@RequestBody Beat beat){
        beats.save(beat);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(beat.getId()).toUri();
        return ResponseEntity.created(location).body(beat);
    }

    // Delete a Beat by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Beat> deleteBeat(@PathVariable Long id){
        Beat beat = beats.findById(id);
        if(beat!=null){
            beats.deleteById(id);
            return ResponseEntity.ok(beat);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    // Replace a Beat with a new one
    @PutMapping("/{id}")
    public ResponseEntity<Beat> replaceBeat(@PathVariable Long id, @RequestBody Beat newBeat){
        Beat beat = beats.findById(id);
        if(beat!=null){
            newBeat.setId(id);
            beats.save(newBeat);
            return ResponseEntity.ok(beat);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    // Update a Beat with new information
    @PatchMapping("/{id}")
    public ResponseEntity<Beat> updateBeat(@PathVariable Long id, @RequestBody Beat updatedBeat){
        Beat beat = beats.findById(id);
        if(beat != null){
            if(updatedBeat.getName() != null){
                beat.setName(updatedBeat.getName());
            }
            if(updatedBeat.getGenre() != null){
                beat.setGenre(updatedBeat.getGenre());
            }
            if(updatedBeat.getDescription() != null){
                beat.setDescription(updatedBeat.getDescription());
            }
            if(updatedBeat.getUrl() != null){
                beat.setUrl(updatedBeat.getUrl());
            }
            if(updatedBeat.getPrice() != null){
                beat.setPrice(updatedBeat.getPrice());
            }
            if(updatedBeat.getTags() != null){
                beat.setTags(updatedBeat.getTags());
            }
            if(updatedBeat.getProducer()!= null){
                beat.setProducer(updatedBeat.getProducer());;
            }
            beats.save(beat);
            return ResponseEntity.ok(beat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
