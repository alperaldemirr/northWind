package kodlamaio.northwind.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//getter ve setter parametrelerini otomatik olarak listeler.
@Data 

//Butun degiskenleri zorunlu tutucak sekilde constructor sinifi olusturur.
@AllArgsConstructor 

//Degiskensiz sekilde constructor sinifi olusturur. Sadece istedigimiz degiskenleri tanitmamiza olanak saglar.
@NoArgsConstructor 

// Veritabaninda bulunan tabloyu sisteme baglar.
@Table(name="categories")

@Entity 

//@JsonIgnoreProperties, Tembel yükleme mimarisi (Sadece istenilen veriyi listeler.)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","products"})

public class Category {

	@Id
	@Column(name="category_id")
	private int categoryId; 
	
	@Column(name="category_name")
	private String categoryName;
	
	@OneToMany(mappedBy = "category")
	private List<Product> products;
	
}
