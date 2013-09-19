import java.util.ArrayList;
import java.util.Iterator;
public class library_clerk {

	public void add_book(String name,String publisher,String isbn,String category,Double price,String authors[]) {
		int auth_id;
		int pub_id = get_publisher_id(publisher);
		
		
		Book b = new Book();
		b.name = name;
		b.isbn = isbn;
		b.category = category;
		b.price = price;
		b.publisher_id = pub_id;
		b.status = "available";
		
		library_manager<Book> lbb = new library_manager<Book>();
		int book_id = lbb.add(b, "book");
		
		for(int i=0;i<authors.length;i++) {
			auth_id = get_author_id(authors[i]);
			library_manager<written_by> wb = new library_manager<written_by>();
			written_by wbo = new written_by();
			wbo.author_id = auth_id;
			wbo.book_id = book_id;
			int wbid = wb.add(wbo, "written_by");
		}
	}
	
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
	
}
