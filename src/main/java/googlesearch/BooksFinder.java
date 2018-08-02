package googlesearch;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;
import gui.Book;
import javafx.collections.ObservableList;

public class BooksFinder {
    final static String appName = "Bookshelf";

    public static void queryGoogleBooks(JsonFactory jsonFactory, String query, ObservableList<Book> booksList) throws Exception {

        final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null).setApplicationName(appName).build();
        System.out.println("Query: [" + query + "]");
        List volumesList = books.volumes().list(query);

        Volumes volumes = volumesList.execute();
        if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
            System.out.println("No matches found.");
            return;
        }

        for (Volume volume : volumes.getItems()) {
            Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();

            // Title.
            System.out.println("Title: " + volumeInfo.getTitle());
            // Author(s).
            java.util.List<String> authors = volumeInfo.getAuthors();
            String author = " ";
            if(authors != null && !authors.isEmpty()){
                author = String.join(", ",authors);
            }

            java.util.List<Volume.VolumeInfo.IndustryIdentifiers> isbns = volumeInfo.getIndustryIdentifiers();
            String identifier = "";
            if(isbns !=null && !isbns.isEmpty()){
                for(Volume.VolumeInfo.IndustryIdentifiers isbn:isbns){
                    identifier += isbn.getType()+": " + isbn.getIdentifier() + ", ";
                }
            }

            System.out.println("Author: " + author);
            System.out.println("ISBN: " + identifier);
            System.out.println("Date: " + volumeInfo.getPublishedDate());
            booksList.add(new Book(volumeInfo.getTitle(), author, identifier));
        }
    }

}



