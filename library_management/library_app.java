import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;




public class library_app {

	public static void main(String args[]) throws NumberFormatException, IOException {
		
		int ch=0;
		
		while(ch!=3) {
		
		System.out.println("Select your choice :");
		System.out.println("1. Enter a book");
		System.out.println("2. Search for a book");
		System.out.println("3. Exit");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ch = Integer.parseInt(br.readLine());
		switch(ch) {
		case 1 :  
			System.out.println("Book name :");
			String name = br.readLine();
			System.out.println("Publisher :");
			String publisher = br.readLine();
			System.out.println("Number of authors :");
			int auth_no = Integer.parseInt(br.readLine());
			String authors[] = new String[auth_no];
			System.out.println("Enter authors :");
			for(int i=0;i<auth_no;i++) {
				authors[i] = br.readLine();
			}
			System.out.println("Category :");
			String category = br.readLine();
			System.out.println("ISBN :");
			String isbn = br.readLine();
			System.out.println("Price :");
			Double price = Double.parseDouble(br.readLine());
			
			library_clerk lc = new library_clerk();
			lc.add_book(name, publisher, isbn, category, price, authors);
			System.out.println("Book added!");
			break;
		case 2 : System.out.println("Enter Book name to search");
		        String b_name = br.readLine();
		        library_clerk lcc = new library_clerk();
		        lcc.get_book(b_name);
		/*
				String key = br.readLine();
				String value = br.readLine();
				library_manager<Book> lb1 = new library_manager<Book>();
				ArrayList<Book> result = lb1.get(key, value, "book",new Book());
				int no = result.size();
				System.out.println("Results found : " + no);
				Iterator<Book> it = result.iterator();
				while(it.hasNext()) {
					Book r = (Book) it.next();
					System.out.println("ACCNO :" + r.get_accno());
					System.out.println("Name :" + r.name);
					System.out.println("Publisher :" + r.publisher_id);
				//	System.out.println("Author :" + r.author);
					System.out.println("Category :" + r.category);
					System.out.println("ISBN :" + r.isbn);
					System.out.println("Price :" + r.price);
					
				} */
				break;
		case 3 : System.exit(0);
		default : break;
				
		}
		}
		
		
	}
	
}
