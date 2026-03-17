package ua.voloschenko.theme9;

public class Library {
    private String name;

    public Library(String name){
        this.name = name;
    }

    public class Book{
        private String title;
        private String author;

        public Book(String title, String author){
            this.title = title;
            this.author = author;
        }

        public String bookLabel(){
            return "Бібліотека: " + name + ", Книга: " + title + " (автор: " + author + ")";
        }
    }

}
