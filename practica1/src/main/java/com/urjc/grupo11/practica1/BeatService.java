package com.urjc.grupo11.practica1;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BeatService {
    @Autowired
    private BeatRepository beats;
    @Autowired
    private UserService users;

    public void save(Beat beat){
        beats.save(beat);
    }
    public Collection<Beat> findAll(){
        return beats.findAll();
    }
    public Beat findById(Long id){
        return beats.findById(id).orElse(null);
    }
    public void deleteById(Long id){
        beats.deleteById(id);
    }
    public void transferBeats(Long fromUserId, Long toUserId) {
        Collection<Beat> beatsToTransfer = findAll().stream()
            .filter(beat -> beat.getProducer().equals(users.findById(fromUserId)))
            .collect(Collectors.toList());
        for (Beat beat : beatsToTransfer) {
            beat.setProducer(users.findById(toUserId));
            save(beat);
        }
    }
    public Collection<Beat> findByTag(String tag){
        return beats.findAll().stream()
            .filter(beat -> beat.getTags().contains(tag))
            .collect(Collectors.toList());
    }
}
