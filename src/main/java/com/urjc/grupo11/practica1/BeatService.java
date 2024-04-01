package com.urjc.grupo11.practica1;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class BeatService {
    private ConcurrentMap<Long, Beat> beats = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong(1);

    public BeatService(){
        // TRAP
        save(new Beat("TTRRAP 56", GENERO.TRAP, "Freestyle trap beat", "O0YBgFXqoKk", 15.99, new HashSet<>(Arrays.asList("trap", "trrap56","56", "freestyle", "trap", "energetic")), 3));
        save(new Beat("TTRRAP 55", GENERO.TRAP, "Freestyle trap beat", "hmGMUg8JV6-M", 13.99, new HashSet<>(Arrays.asList("trap", "trrap55", "55", "trrap", "freestyle", "energetic")),3));
        save(new Beat("QUE TENGO QUE HACER", GENERO.TRAP, "Bad Bunny and Anuel style trap beat", "xvw2T2r2qbU", 6.66, new HashSet<>(Arrays.asList("trap", "bunny", "bad", "double","aa", "badbo", "rabbit", "2016", "angry", "energetic")), 3));
        save(new Beat("BLOQUE", GENERO.TRAP, "Myke Towers and Eladio Carrion style trap beat", "JhXLLgVcHI0", 19.99, new HashSet<>(Arrays.asList("trap", "street", "block", "myke","eladio", "towers","easy", "money", "energetic", "angry")), 3));
        // REGGAETON
        save(new Beat("LOCURAS", GENERO.REGGAETON, "Jhay Cortez style reggaeton beat", "9YRfoMccgh0", 8.99, new HashSet<>(Arrays.asList("reggaeton","craziness", "jhay", "cortez", "jhayco", "energetic")),3));
        save(new Beat("CUANDO SERA", GENERO.REGGAETON, "Feid and Jhay Cortez style reggaeton beat", "hTXIAHgKgew", 15.99, new HashSet<>(Arrays.asList("jhay","ferxxo", "cortez", "jhayco", "feid", "jhay", "reggaeton", "when", "will", "happy")), 3));
        save(new Beat("SATEO", GENERO.REGGAETON, "Reggaeton and Perreo beat like DEI V", "gJ63u5CvJIA", 11.99, new HashSet<>(Arrays.asList("reggaeton", "perreo", "dei", "V","sateo", "happy", "energetic")), 3));
        save(new Beat("HACE TIEMPO", GENERO.REGGAETON, "Feid and Mora style reggaeton beat", "SgpY2dspMyY", 9.49, new HashSet<>(Arrays.asList("feid", "ferxxo", "mora", "reggaeton","time", "happy", "energetic")), 3));
        // POP
        save(new Beat("BOUNCE", GENERO.POP, "Funk POP beat", "x8NwnnDyXPc", 6.90, new HashSet<>(Arrays.asList("pop", "funk", "chill", "happy", "disco")), 3));
        save(new Beat("VITAMIN", GENERO.POP, "Urban POP beat like POST MALONE", "AXHgacpaFyE", 16.99, new HashSet<>(Arrays.asList("pop", "urban", "malone", "post","vitamin")), 3));
        save(new Beat("LOVERS", GENERO.POP, "Romantic POP beat like IANN DIOR", "OGNWyPy_4wc", 10.99, new HashSet<>(Arrays.asList("pop", "love", "lovers", "dior","iann", "happy", "inlove")), 3));
        save(new Beat("PORQUE SIN TI", GENERO.POP, "Urban POP beat", "9g2W9eviPBs", 10.99, new HashSet<>(Arrays.asList("pop", "why", "without", "you", "urban")), 3));
        // R&B
        save(new Beat("LOVE ME", GENERO.RB, "Romantic R&B beat", "mW_f8lHxA_4", 14.99, new HashSet<>(Arrays.asList("rb", "r&b", "love", "romantic", "inlove")), 3));
        save(new Beat("LAST KISS", GENERO.RB, "Romantic and sad R&B beat", "ELzlUveJgzU", 16.99, new HashSet<>(Arrays.asList("rb", "r&b", "love","sad","inlove")), 3));
        save(new Beat("OVER N OVER", GENERO.RB, "Romantic R&B beat with guitar", "YwFAHlDP3bQ", 13.99, new HashSet<>(Arrays.asList("rb", "r&b", "over", "guitar","acoustic", "chill", "inlove")), 3));
        save(new Beat("TIME TELLS", GENERO.RB, "Urban R&B beat", "KVVFB7kRQzc", 8.86, new HashSet<>(Arrays.asList("rb", "r&b", "chill", "time","happy")), 3));
        // ELECTRONICA
        save(new Beat("TRUCO", GENERO.ELECTRONICA, "Techno and tech house beat", "e4DXb8oQQzs", 15.89, new HashSet<>(Arrays.asList("techno", "electronica", "electro", "energetic", "house")), 3));
        save(new Beat("CHAOS", GENERO.ELECTRONICA, "Techno and club beat", "2yAQ6mDbkzY", 23.55, new HashSet<>(Arrays.asList("techno", "electronica", "electro","club", "energetic")), 3));
        save(new Beat("GROOVE", GENERO.ELECTRONICA, "Techno and rap beat", "5Y_vsVA1_IE", 28.99, new HashSet<>(Arrays.asList("techno", "electronica", "electro","rap", "energetic")), 3));
        save(new Beat("ASTRO", GENERO.ELECTRONICA, "Techno and tech house beat like YSY A", "G_hT99UZWk4", 11.11, new HashSet<>(Arrays.asList("techno", "electronica", "electro","house", "energetic", "ysy")), 3));
        
        // HIP-HOP-RAP
        save(new Beat("CERBERO", GENERO.RAP, "Boom Bap HIP-HOP-RAP beat", "a_NE74ZQMIU", 16.88, new HashSet<>(Arrays.asList("hip", "hop", "boom", "bap", "rap", "cerbero", "angry")), 3));
        save(new Beat("TIEMPO AL TIEMPO", GENERO.RAP, "Boom Bap HIP-HOP LOFI RAP beat", "vxxgb9WhuvY", 12.77, new HashSet<>(Arrays.asList("hip", "hop", "boom", "bap", "rap", "time", "chill", "lofi")), 3));
        save(new Beat("BLAME ME", GENERO.RAP, "Dark Underground RAP beat", "VPA-1BKocto", 14.59, new HashSet<>(Arrays.asList("hip", "hop", "boom", "free", "rap", "dark", "underground")), 3));
        save(new Beat("SOLITARIO ", GENERO.RAP, "Aggressive Underground RAP beat", "TeIwL2aYFkc", 7.99, new HashSet<>(Arrays.asList("lonely", "free", "rap", "time", "aggressive", "angry")), 3));
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
    public void transferBeats(Long fromUserId, Long toUserId) {
        Collection<Beat> beatsToTransfer = findAll().stream()
            .filter(beat -> beat.getProducerID().equals(fromUserId))
            .collect(Collectors.toList());
        for (Beat beat : beatsToTransfer) {
            beat.setProducerID(toUserId);
            save(beat);
        }
    }
    public Collection<Beat> findByTag(String tag){
        return beats.values().stream()
            .filter(beat -> beat.getTags().contains(tag))
            .collect(Collectors.toList());
    }
}
