package com.urjc.grupo11.practica1;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class BeatService {
    private ConcurrentMap<Long, Beat> beats = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong(1);

    public BeatService(){
        save(new Beat("TTRRAP 56", GENERO.TRAP, "Beat de trap tipo freestyle", "O0YBgFXqoKk", 15.99, new HashSet<>(Arrays.asList("trap", "trrap56","56", "freestyle", "trap", "enérgico"))));
        save(new Beat("LOCURAS", GENERO.REGGAETON, "Beat de reggaeton tipo Jhay Cortez", "9YRfoMccgh0", 8.99, new HashSet<>(Arrays.asList("reggaeton","locuras", "jhay", "cortez", "jhayco", "enérgico"))));
        save(new Beat("TTRRAP 55", GENERO.TRAP, "Beat de trap estilo freestyle", "hmGMUg8JV6-M", 13.99, new HashSet<>(Arrays.asList("trap", "trrap55", "55", "trrap", "freestyle", "enérgico"))));
    }
    public void save(Beat beat){
        if (beat.getId() == null || beat.getId()==0){
            long id = nextId.getAndIncrement();
            beat.setId(id);
        }
        beats.put(beat.getId(), beat);
    }
    public Collection<Beat> findAll(){
        return beats.values();
    }
    public Beat findById(Long id){
        return beats.get(id);
    }
    public void deleteById(Long id){
        beats.remove(id);
    }
}
