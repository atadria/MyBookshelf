package database;

import org.datanucleus.api.jdo.JDOPersistenceManagerFactory;
import org.datanucleus.metadata.PersistenceUnitMetaData;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import java.util.Collection;
import java.util.List;

public class BookPersisting {


    private  PersistenceManager pm;

    public BookPersisting(){
        PersistenceUnitMetaData pumd = new PersistenceUnitMetaData("dynamic-unit", "RESOURCE_LOCAL", null);
//        pumd.addClassName("database.Book");
//        pumd.setExcludeUnlistedClasses();
        pumd.addProperty("javax.jdo.option.ConnectionDriverName", "org.h2.Driver");
        pumd.addProperty("javax.jdo.option.ConnectionURL", "jdbc:h2:file:../mybooks");
        pumd.addProperty("javax.jdo.option.ConnectionUserName", "sa");
        pumd.addProperty("javax.jdo.option.ConnectionPassword", "");

//        pumd.addProperty("datanucleus.autoCreateSchema", "true");


        PersistenceManagerFactory pmf = new JDOPersistenceManagerFactory(pumd, null);
        pm = pmf.getPersistenceManager();
    }

    public void saveBook(Book book){
        Transaction tx = pm.currentTransaction();

        try{
            pm.makePersistent(book);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    public List<Book> getBooks(String word){
        Query query;
        query = pm.newQuery(Book.class, "title.matches(\"(?i).*" + word + ".*\")");

        List<Book> books = (List<Book>) query.execute();
        return books;
    }
    public List<Book> getBooks(){
        Query query = pm.newQuery("SELECT FROM " + Book.class.getName());
        List<Book> books = (List<Book>) query.execute();
        return books;
    }

    public void removeBook(Book book){
        Query query = pm.newQuery(Book.class, "ISBN == \"" + book.getISBN() + "\"");
        Collection result = (Collection) query.execute();
        Book bookToDelete = (Book) result.iterator().next();
        pm.deletePersistent(bookToDelete);
    }
    public void editBook(Book book,String[] bookInfo){
        Query query = pm.newQuery(Book.class, "ISBN == \"" + book.getISBN() + "\"");
        Collection result = (Collection) query.execute();
        Book bookToEdit = (Book) result.iterator().next();
        bookToEdit.setTitle(bookInfo[0]);
        bookToEdit.setSubtitle(bookInfo[1]);
        bookToEdit.setAuthor(bookInfo[2]);
        bookToEdit.setPublisher(bookInfo[3]);
        bookToEdit.setAdditionalInfo(bookInfo[4]);
        bookToEdit.setISBN(bookInfo[5]);
        bookToEdit.setDate(bookInfo[6]);
    }

    public void addBook(String[] list) {

        Book book = new Book(list[0], list[2], list[5]);
        book.setDate(list[6]);
        book.setSubtitle(list[1]);
        book.setPublisher(list[3]);
        book.setAdditionalInfo(list[4]);
        saveBook(book);

    }

    public List<Book> advancedSearch(String[] list){
        String q = "";
        if(list[0] != null && !list[0].trim().isEmpty()){
            q+="title.matches(\"(?i).*"+ list[0]+ ".*\")";
        }
        if(list[1] != null && !list[1].trim().isEmpty()){
            if (!q.isEmpty()){
                q+=" && ";
            }
            q+="subtitle.matches(\"(?i).*"+ list[1]+ ".*\")";
        }
        if(list[2] != null && !list[2].trim().isEmpty()){
            if (!q.isEmpty()){
                q+=" && ";
            }
            q+="author.matches(\"(?i).*"+ list[2]+ ".*\")";
        }
        if(list[3] != null && !list[3].trim().isEmpty()){
            if (!q.isEmpty()){
                q+=" && ";
            }
            q+="publisher.matches(\"(?i).*"+ list[3]+ ".*\")";
        }
        if(list[4] != null && !list[4].trim().isEmpty()){
            if (!q.isEmpty()){
                q+=" && ";
            }
            q+="date.matches(\"(?i).*"+ list[4]+ ".*\")";
        }
        if(list[5] != null && !list[5].trim().isEmpty()){
            if (!q.isEmpty()){
                q+=" && ";
            }
            q+="ISBN.matches(\"(?i).*"+ list[5]+ ".*\")";
        }
        if(list[6] != null && !list[6].trim().isEmpty()){
            if (!q.isEmpty()){
                q+=" && ";
            }
            q+="additionalInfo.matches(\"(?i).*"+ list[6]+ ".*\")";
        }

        Query query = pm.newQuery(Book.class, q);
        List<Book> books = (List<Book>) query.execute();
        return books;
    }
}
