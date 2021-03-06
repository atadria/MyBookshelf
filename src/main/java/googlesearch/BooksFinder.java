package googlesearch;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import database.Book;
import javafx.collections.ObservableList;

public class BooksFinder {
    final static String appName = "Bookshelf";

    public static void queryGoogleBooks(JsonFactory jsonFactory, String query, ObservableList<Book> booksList) throws Exception {

        final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null).setApplicationName(appName).build();
        List volumesList = books.volumes().list(query);

        Volumes volumes = volumesList.execute();
        if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
            System.out.println("No matches found.");
            return;
        }

        for (Volume volume : volumes.getItems()) {
            Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();

            java.util.List<String> authors = volumeInfo.getAuthors();
            String author = " ";
            if(authors != null && !authors.isEmpty()){
                author = String.join(", ",authors);
            }
            java.util.List<Volume.VolumeInfo.IndustryIdentifiers> isbns = volumeInfo.getIndustryIdentifiers();
            String identifier = "";
            String isbn13=null;
            if(isbns !=null && !isbns.isEmpty()){
                for(Volume.VolumeInfo.IndustryIdentifiers isbn:isbns){
                    if (isbn.getType().equals("ISBN_13")){
                        isbn13 = isbn.getIdentifier();
                    }
                    identifier += isbn.getType()+": " + isbn.getIdentifier() + ", ";
                }
            }


            String subtitle = volumeInfo.getSubtitle();
            String publisher = volumeInfo.getPublisher();
            String date = volumeInfo.getPublishedDate();


            Book book;

            if(isbn13!=null){
                book = new Book(volumeInfo.getTitle(), author, isbn13);
            }
            else {
                book = new Book(volumeInfo.getTitle(), author, identifier);
            }

            if(subtitle != null ){
                book.setSubtitle(subtitle);
            }
            if(publisher != null ){
                book.setPublisher(publisher);
            }
            if(date != null ){
                book.setDate(date);
            }
            booksList.add(book);
        }
    }

}



