import java.util.ArrayList;
import java.util.Iterator;


public class library_clerk {

	public void add_book(String name,String publisher,String isbn,String category,Double price,String authors[]) {
		
		
		
		Book b = new Book();
		
		b.name = name;
		b.isbn = isbn;
		b.category = category;
		b.price = price;
		b.pub = new Publisher(publisher);
		b.status = "available";
		
		Author auth[] = new Author[authors.length];
		
		for(int i=0;i<authors.length;i++) {
			auth[i] = new Author(authors[i]);	
		}
		
		b.authors = auth;
		
		library_manager<Book> lbb = new library_manager<Book>();
		b.id = lbb.get_id("book");
		lbb.add_j(b, "book");
		
		/*
		
		*/
	}
	
	public void get_book(String b_name) {
		ArrayList<Book> blist;
		Book b = new Book();
		library_manager<Book> lb = new library_manager<Book>();
		blist = lb.getObj("name", b_name, "book", b, Book.class);
		Iterator<Book> it = blist.iterator();
		while(it.hasNext()) {
			b = it.next();
			show_book(b);
		}
	}
	
	public void show_book(Book b) {
		System.out.println("Name : " + b.name);
		System.out.println("Publisher : " + b.pub.name);
		System.out.println("Authors : ");
		for(int i=0;i<b.authors.length; i++) {
			System.out.println(b.authors[i].name);
		}
	}
	
	/*
	
	private int get_author_id(String name) {
		library_manager<author> lba = new library_manager<author>();
		ArrayList<author> at = lba.get("name", name, "author", new author());
		if(!at.isEmpty()) {
			int id = at.get(0).get_id();
			return id;
		}else {
			author att = new author();
			att.name = name;
			int id = lba.add(att, "author");
			return id;
		}
		
	}
	
	private int get_publisher_id(String name) {
		library_manager<publisher> lba = new library_manager<publisher>();
		ArrayList<publisher> at = lba.get("name", name, "publisher", new publisher());
		if(!at.isEmpty()) {
			int id = at.get(0).get_id();
			return id;
		}else {
			publisher pub = new publisher();
			pub.name = name;
			
			int id = lba.add(pub, "publisher");
			return id;
		}
		
	}
	*/
}
