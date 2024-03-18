    package com.urjc.grupo11.practica1;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;

    @Entity
    public class Beat {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String name;
        private GENERO genre;
        private Double price;
        private String description;
        private String url;

        public Beat(){}

        public Beat(String name, GENERO genre, String description, String url, Double price){
            super();
            this.name=name;
            this.genre=genre;
            this.description=description;
            this.url=url;
            this.price=price;
        }
        
        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public GENERO getGenre() {
            return this.genre;
        }

        public void setGenre(GENERO genre) {
            this.genre = genre;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        
        public Double getPrice() {
            return this.price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }
