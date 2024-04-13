package com.urjc.grupo11.practica1;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Profile("local")
public class DatabasePopulator {
    @Autowired
    private UserService users;
    @Autowired 
    private BeatService beats;
    @Autowired
    private LicenseService licenses;


    @PostConstruct
    public void populateDatabase(){
        users.save(new User("Tainy", "Tainy1234", "tainyproducer@gmail.com", new HashSet<>(), new HashSet<>()));
        users.save(new User("Pepito", "PepitoPerez512", "pepitoperez@gmail.com", new HashSet<>(), new HashSet<>()));
        users.save(new User("Juanito", "Juanito8080", "juanito.8080@gmail.com", new HashSet<>(), new HashSet<>()));

        //TRAP
        beats.save(new Beat("TTRRAP 56", GENERO.TRAP, "Beat de trap tipo freestyle", "O0YBgFXqoKk", 15.99, new HashSet<>(Arrays.asList("trap", "trrap56","56", "freestyle", "trap", "enérgico")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("TTRRAP 55", GENERO.TRAP, "Beat de trap estilo freestyle", "mGMUg8JV6-M", 13.99, new HashSet<>(Arrays.asList("trap", "trrap55", "55", "trrap", "freestyle", "enérgico")),users.findById(1L), new HashSet<>()));
        beats.save(new Beat("QUE TENGO QUE HACER", GENERO.TRAP, "Beat de trap tipo Bad Bunny y Anuel", "xvw2T2r2qbU", 6.66, new HashSet<>(Arrays.asList("trap", "bunny", "bad", "la doble","aa", "badbo", "conejo", "2016", "enfadado", "energico")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("BLOQUE", GENERO.TRAP, "Beat de trap tipo Myke Towers y Eladio Carrion", "JhXLLgVcHI0", 19.99, new HashSet<>(Arrays.asList("trap", "calle", "bloque", "myke","eladio", "towers","easy", "money", "sendo", "energico", "enfadado")), users.findById(1L), new HashSet<>()));
        // REGGAETON
        beats.save(new Beat("LOCURAS", GENERO.REGGAETON, "Beat de reggaeton tipo Jhay Cortez", "9YRfoMccgh0", 8.99, new HashSet<>(Arrays.asList("reggaeton","locuras", "jhay", "cortez", "jhayco", "enérgico")),users.findById(1L), new HashSet<>()));
        beats.save(new Beat("CUANDO SERA", GENERO.REGGAETON, "Beat de reggaeton tipo Feid y Jhay Cortez", "hTXIAHgKgew", 15.99, new HashSet<>(Arrays.asList("jhay","ferxxo", "cortez", "jhayco", "feid", "jhay", "reggaeton", "cuando", "sera", "feliz")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("SATEO", GENERO.REGGAETON, "Beat de Reggaeton y Perreo tipo DEI V", "gJ63u5CvJIA", 11.99, new HashSet<>(Arrays.asList("reggaeton", "perreo", "dei", "V","sateo", "feliz", "energico")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("HACE TIEMPO", GENERO.REGGAETON, "Beat de reggaeton tipo Feid y Mora", "SgpY2dspMyY", 9.49, new HashSet<>(Arrays.asList("feid", "ferxxo", "mora", "reggaeton","tiempo", "feliz", "energico")), users.findById(1L), new HashSet<>()));
        // POP
        beats.save(new Beat("BOUNCE", GENERO.POP, "Funk POP beat", "x8NwnnDyXPc", 6.90, new HashSet<>(Arrays.asList("pop", "funk", "chill", "happy", "disco")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("VITAMIN", GENERO.POP, "Beat de POP urbano tipo POST MALONE", "AXHgacpaFyE", 16.99, new HashSet<>(Arrays.asList("pop", "urbano", "malone", "post","vitamin")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("LOVERS", GENERO.POP, "Beat de POP romantico tipo IANN DIOR", "OGNWyPy_4wc", 10.99, new HashSet<>(Arrays.asList("pop", "amor", "lovers", "dior","iann", "feliz", "enamorado")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("PORQUE SIN TI", GENERO.POP, "Beat de POP urbano", "9g2W9eviPBs", 10.99, new HashSet<>(Arrays.asList("pop", "porque", "ain", "ti", "urbano")), users.findById(1L), new HashSet<>()));
        // R&B
        beats.save(new Beat("LOVE ME", GENERO.RB, "Beat de R&B romántico", "mW_f8lHxA_4", 14.99, new HashSet<>(Arrays.asList("rb", "r&b", "amor", "romantico", "enamorado")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("LAST KISS", GENERO.RB, "Beat de R&B romántico y triste", "ELzlUveJgzU", 16.99, new HashSet<>(Arrays.asList("rb", "r&b", "amor","triste","enamorado")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("OVER N OVER", GENERO.RB, "Beat de R&B romantico con guitarra", "YwFAHlDP3bQ", 13.99, new HashSet<>(Arrays.asList("rb", "r&b", "over", "guitar","acustic", "chill", "enamorado")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("TIME TELLS", GENERO.RB, "Beat de R&B urbano", "KVVFB7kRQzc", 8.86, new HashSet<>(Arrays.asList("rb", "r&b", "chill", "time","tiempo", "feliz")), users.findById(1L), new HashSet<>()));
        // ELECTRONICA
        beats.save(new Beat("TRUCO", GENERO.ELECTRONICA, "Beat de techno y tech house", "e4DXb8oQQzs", 15.89, new HashSet<>(Arrays.asList("techno", "electronica", "electro", "energico", "house")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("CHAOS", GENERO.ELECTRONICA, "Beat de techno y club", "2yAQ6mDbkzY", 23.55, new HashSet<>(Arrays.asList("techno", "electronica", "electro","club", "energico")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("GROOVE", GENERO.ELECTRONICA, "Beat de techno y rap", "5Y_vsVA1_IE", 28.99, new HashSet<>(Arrays.asList("techno", "electronica", "electro","rap", "energico")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("ASTRO", GENERO.ELECTRONICA, "Beat de techno y tech house tipo YSY A", "G_hT99UZWk4", 11.11, new HashSet<>(Arrays.asList("techno", "electronica", "electro","house", "energico", "ysy")), users.findById(1L), new HashSet<>()));       
        // HIP-HOP-RAP
        beats.save(new Beat("CERBERO", GENERO.RAP, "Beat de HIP-HOP-RAP BOOM BAP", "a_NE74ZQMIU", 16.88, new HashSet<>(Arrays.asList("hip", "hop", "boom", "bap", "rap", "cerbero", "enfadado")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("TIEMPO AL TIEMPO", GENERO.RAP, "Beat de RAP HIP-HOP BOOM BAP LOFI", "vxxgb9WhuvY", 12.77, new HashSet<>(Arrays.asList("hip", "hop", "boom", "bap", "rap", "tiempo", "chill", "lofi")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("BLAME ME", GENERO.RAP, "Beat de RAP UNDERGROUND DARK", "VPA-1BKocto", 14.59, new HashSet<>(Arrays.asList("hip", "hop", "boom", "free", "rap", "dark", "underground")), users.findById(1L), new HashSet<>()));
        beats.save(new Beat("SOLITARIO ", GENERO.RAP, "Beat de RAP UNDERGROUND Agresivo", "TeIwL2aYFkc", 7.99, new HashSet<>(Arrays.asList("solitario", "free", "rap", "tiempo", "agresivo", "enfadado")), users.findById(1L), new HashSet<>()));


        licenses.save(new License(users.findById(2L), beats.findById(1L), LocalDate.of(2023, 12, 12), LICENSETYPE.COMMERCIAL));
        licenses.save(new License(users.findById(3L), beats.findById(5L), LocalDate.of(2024, 2, 20), LICENSETYPE.PERSONAL));
        licenses.save(new License(users.findById(2L), beats.findById(5L), LocalDate.of(2024, 1, 8), LICENSETYPE.COMMERCIAL));
    }
}
