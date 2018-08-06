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

        pumd.addProperty("datanucleus.autoCreateSchema", "true");


        PersistenceManagerFactory pmf = new JDOPersistenceManagerFactory(pumd, null);
        pm = pmf.getPersistenceManager();
    }

    public void saveBook(Book book){
        Transaction tx = pm.currentTransaction();

        try{
            pm.makePersistent(book);
            System.out.println("++++++SAVING++++++");
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
        Query query = pm.newQuery(Book.class, "title.matches(\"(?i).*" + word + ".*\")");
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
}
