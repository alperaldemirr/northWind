package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product, Integer>{
	
	Product getByProductName(String productName); // Product sinifindaki urun ismini ister.
	
	Product getByProductNameAndCategory_CategoryId(String productName, int categoryId); // Product sinifindaki urun ismi ve kategori alanlarini ister.
	
	List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId); // Product sinifindaki urun ismi veya kategori alanini liste halinde ister.
	
	List<Product> getByCategory_CategoryIdIn(List<Integer> categories);  // Product sinifindaki kategorileri liste halinde ister.
	
	List<Product> getByProductNameContains(String productName); // Product sinifindaki urun isimlerinin icerigini ister.
	
	List<Product> getByProductNameStartsWith(String productName); // Product sinifindaki urun isimlerinin baslangic icerigini ister.
	
	
	@Query("From Product where productName=:productName and category_id=:categoryId") // Projede, SQL komutlari yerine kendi alanlarimizi kullanmamiza olanak saglar.(Query) 
	List<Product> getByNameAndCategory_CategoryId(String productName, int categoryId);
	
	
	@Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id, p.productName, c.categoryName) From Category c Inner Join c.products p") // Olusturdugumuz dtos(Data Transfer Object) icin ilgili tablolardan, ilgili verileri cekerek tek bir tablo haline getirdik.(Kisisellestirilmis tablo.)
	List<ProductWithCategoryDto> getByProductWithCategoryDetails(); 
	
	
	
	
	
	
	
}
