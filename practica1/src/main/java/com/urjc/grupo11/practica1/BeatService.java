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
        save(new Beat("TTRRAP 56", GENERO.TRAP, "Beat de trap tipo freestyle", "O0YBgFXqoKk", 15.99, new HashSet<>(Arrays.asList("trap", "trrap56","56", "freestyle", "trap", "enérgico")), 3));
        save(new Beat("TTRRAP 55", GENERO.TRAP, "Beat de trap estilo freestyle", "mGMUg8JV6-M", 13.99, new HashSet<>(Arrays.asList("trap", "trrap55", "55", "trrap", "freestyle", "enérgico")),3));
        save(new Beat("QUE TENGO QUE HACER", GENERO.TRAP, "Beat de trap tipo Bad Bunny y Anuel", "xvw2T2r2qbU", 6.66, new HashSet<>(Arrays.asList("trap", "bunny", "bad", "la doble","aa", "badbo", "conejo", "2016", "enfadado", "energico")), 3));
        save(new Beat("BLOQUE", GENERO.TRAP, "Beat de trap tipo Myke Towers y Eladio Carrion", "JhXLLgVcHI0", 19.99, new HashSet<>(Arrays.asList("trap", "calle", "bloque", "myke","eladio", "towers","easy", "money", "sendo", "energico", "enfadado")), 3));
        // REGGAETON
        save(new Beat("LOCURAS", GENERO.REGGAETON, "Beat de reggaeton tipo Jhay Cortez", "9YRfoMccgh0", 8.99, new HashSet<>(Arrays.asList("reggaeton","locuras", "jhay", "cortez", "jhayco", "enérgico")),3));
        save(new Beat("CUANDO SERA", GENERO.REGGAETON, "Beat de reggaeton tipo Feid y Jhay Cortez", "hTXIAHgKgew", 15.99, new HashSet<>(Arrays.asList("jhay","ferxxo", "cortez", "jhayco", "feid", "jhay", "reggaeton", "cuando", "sera", "feliz")), 3));
        save(new Beat("SATEO", GENERO.REGGAETON, "Beat de Reggaeton y Perreo tipo DEI V", "gJ63u5CvJIA", 11.99, new HashSet<>(Arrays.asList("reggaeton", "perreo", "dei", "V","sateo", "feliz", "energico")), 3));
        save(new Beat("HACE TIEMPO", GENERO.REGGAETON, "Beat de reggaeton tipo Feid y Mora", "SgpY2dspMyY", 9.49, new HashSet<>(Arrays.asList("feid", "ferxxo", "mora", "reggaeton","tiempo", "feliz", "energico")), 3));
        // POP
        save(new Beat("BOUNCE", GENERO.POP, "Funk POP beat", "x8NwnnDyXPc", 6.90, new HashSet<>(Arrays.asList("pop", "funk", "chill", "happy", "disco")), 3));
        save(new Beat("VITAMIN", GENERO.POP, "Beat de POP urbano tipo POST MALONE", "AXHgacpaFyE", 16.99, new HashSet<>(Arrays.asList("pop", "urbano", "malone", "post","vitamin")), 3));
        save(new Beat("LOVERS", GENERO.POP, "Beat de POP romantico tipo IANN DIOR", "OGNWyPy_4wc", 10.99, new HashSet<>(Arrays.asList("pop", "amor", "lovers", "dior","iann", "feliz", "enamorado")), 3));
        save(new Beat("PORQUE SIN TI", GENERO.POP, "Beat de POP urbano", "9g2W9eviPBs", 10.99, new HashSet<>(Arrays.asList("pop", "porque", "ain", "ti", "urbano")), 3));
        // R&B
        save(new Beat("LOVE ME", GENERO.RB, "Beat de R&B romántico", "mW_f8lHxA_4", 14.99, new HashSet<>(Arrays.asList("rb", "r&b", "amor", "romantico", "enamorado")), 3));
        save(new Beat("LAST KISS", GENERO.RB, "Beat de R&B romántico y triste", "ELzlUveJgzU", 16.99, new HashSet<>(Arrays.asList("rb", "r&b", "amor","triste","enamorado")), 3));
        save(new Beat("OVER N OVER", GENERO.RB, "Beat de R&B romantico con guitarra", "YwFAHlDP3bQ", 13.99, new HashSet<>(Arrays.asList("rb", "r&b", "over", "guitar","acustic", "chill", "enamorado")), 3));
        save(new Beat("TIME TELLS", GENERO.RB, "Beat de R&B urbano", "KVVFB7kRQzc", 8.86, new HashSet<>(Arrays.asList("rb", "r&b", "chill", "time","tiempo", "feliz")), 3));
        // ELECTRONICA
        save(new Beat("TRUCO", GENERO.ELECTRONICA, "Beat de techno y tech house", "e4DXb8oQQzs", 15.89, new HashSet<>(Arrays.asList("techno", "electronica", "electro", "energico", "house")), 3));
        save(new Beat("CHAOS", GENERO.ELECTRONICA, "Beat de techno y club", "2yAQ6mDbkzY", 23.55, new HashSet<>(Arrays.asList("techno", "electronica", "electro","club", "energico")), 3));
        save(new Beat("GROOVE", GENERO.ELECTRONICA, "Beat de techno y rap", "5Y_vsVA1_IE", 28.99, new HashSet<>(Arrays.asList("techno", "electronica", "electro","rap", "energico")), 3));
        save(new Beat("ASTRO", GENERO.ELECTRONICA, "Beat de techno y tech house tipo YSY A", "G_hT99UZWk4", 11.11, new HashSet<>(Arrays.asList("techno", "electronica", "electro","house", "energico", "ysy")), 3));
        
        // HIP-HOP-RAP
        save(new Beat("CERBERO", GENERO.RAP, "Beat de HIP-HOP-RAP BOOM BAP", "a_NE74ZQMIU", 16.88, new HashSet<>(Arrays.asList("hip", "hop", "boom", "bap", "rap", "cerbero", "enfadado")), 3));
        save(new Beat("TIEMPO AL TIEMPO", GENERO.RAP, "Beat de RAP HIP-HOP BOOM BAP LOFI", "vxxgb9WhuvY", 12.77, new HashSet<>(Arrays.asList("hip", "hop", "boom", "bap", "rap", "tiempo", "chill", "lofi")), 3));
        save(new Beat("BLAME ME", GENERO.RAP, "Beat de RAP UNDERGROUND DARK", "VPA-1BKocto", 14.59, new HashSet<>(Arrays.asList("hip", "hop", "boom", "free", "rap", "dark", "underground")), 3));
        save(new Beat("SOLITARIO ", GENERO.RAP, "Beat de RAP UNDERGROUND Agresivo", "TeIwL2aYFkc", 7.99, new HashSet<>(Arrays.asList("solitario", "free", "rap", "tiempo", "agresivo", "enfadado")), 3));
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
