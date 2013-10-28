import java.util.ArrayList;
import java.util.Iterator;


public class library_clerk {

	public boolean add_book(String name,String publisher,String isbn,String category,Double price,String authors[]) {
		
		library_manager<Book> lbb = new library_manager<Book>();
		library_manager<Author> lba = new library_manager<Author>();
		
		sandbox<Book> lsb = new sandbox<Book>();
		
		int flag[] = new int[authors.length];
		
		Book b = new Book();
		
		b.name = name;
		b.isbn = isbn;
		b.category = category;
		b.price = price;
		b.pub = new Publisher(publisher);
		b.status = "available";
		
		int book_id = lbb.get_id("book");
		//System.out.println(book_id);
		
		
		
		
		for(int i=0;i<authors.length;i++) {
			Author a = new Author();
			Author res = new Author();
			ArrayList<Author> result = lba.getObj("name", authors[i], "author",res,Author.class);
			//System.out.println("Result size : " + result.size());
				if(result.size()==1) {
					a = result.get(0);
					a.books.add(book_id);
					flag[i] = 1;
					System.out.println("Author to be Updated");
					if(lba.update(a, "author")==false) {				//if update operation fails, rollback
						lba.rollback();
						return false;
					}
			}else {
			
				a.name = authors[i];
				a.books.add((Integer)book_id);
				flag[i] = 0;
				System.out.println("Author to be Added");
				if(lba.add_j(a, "author")==false) {						//if add operation fails, rollback
					lba.rollback();
					return false;
				}
			}
			b.authors.add(a.get_id());
			
		}
		
		if((lbb.add_j(b, "book")==true)) {								//if no problem, commit
		 lbb.commit();	
		 lba.commit();
		} else {														//else, rollback both book and author
			lbb.rollback();
			lba.rollback();
			return false;
		}
		
		lsb.add_j(b, "book");
		//lsb.commit();
		
		show_book(b);
		//lbb.rollback();
		
		return true;
	}
	
	public void get_book(String b_name) {
		ArrayList<Book> blist;
		Book b = new Book();
		library_manager<Book> lb = new library_manager<Book>();
		blist = lb.getObj("name", b_name, "book", b, Book.class);
		if(blist==null) System.out.println("No book exists");
		else {
		Iterator<Book> it = blist.iterator();
		while(it.hasNext()) {
			b = it.next();
			show_book(b);
		}
		}
	}
	
	public void show_book(Book b) {
		Author at = new Author();
		System.out.println("Name : " + b.name);
		System.out.println("Publisher : " + b.pub.name);
		System.out.println("Authors : ");
		library_manager<Author> lba = new library_manager<Author>();
		for(int i=0;i<b.authors.size(); i++) {
			Author res = new Author();
			res = lba.getOneObj(b.authors.get(i), "author", at, Author.class);
			System.out.println(res.name);
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
