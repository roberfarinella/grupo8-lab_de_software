package Modelo;


import Modelo.Image;
import java.util.List;

public class Category {

        private long id ;
        /*private List<Image> image ;*/
        private int level ;
        private String name;

        public Category() {

        }

        public Category(long id, int level, String name) {
            this.id = id;
           /* this.image = image;*/
            this.level = level;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        /*public Image getImage() {
           return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }*/
    }
