package kodlamaio.northwind.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data Transfer Object(Dto) klasoru olusturup icerisine Product ve Category nesnelerini bir araya getirecek sinif olusturduk. Veritabanina ihtiyac duymadan yeni bir tablo.(firstCode.)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithCategoryDto {
	private int id;
	private String productName;
	private String categoryName;
}
