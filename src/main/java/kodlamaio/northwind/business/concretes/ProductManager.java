package kodlamaio.northwind.business.concretes;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

import org.springframework.stereotype.Service;

@Service
public class ProductManager implements ProductService{

	private ProductDao productDao;
	
	@Autowired
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public DataResult<List<Product>> getAll() {
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(), "Data Listelendi");
	}
	
	@Override
	public DataResult<List<Product>> getAllSorted() {
		 Sort sort = Sort.by(Sort.Direction.DESC,"productName"); // Sort, verileri "DESC" komutu ile birlikte Z'den A'ya gore siralar. "ASC" komutu tersini yapar.  
		 return new SuccessDataResult<List<Product>>(this.productDao.findAll(sort),"Basarili");
		 
	}
	
	@Override
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNo-1, pageSize); // Pageable, verilerimizi sayfalamamizi saglar.(-1 yazilmasinin sebebi, sayfalamaya sifirdan baslatmamak icin.)
		
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(pageable).getContent());
	}

	@Override
	public Result add(Product product) {
		this.productDao.save(product);
		return new SuccessResult("Urun Eklendi.");
	}

	@Override
	public DataResult<Product> getByProductName(String productName) {
		
		// businessCodes
		
		return new SuccessDataResult<Product>(this.productDao.getByProductName(productName), "Data Listelendi");
	}
	@Override
	public DataResult<Product> getByProductNameAndCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<Product>(this.productDao.getByProductNameAndCategory_CategoryId(productName,categoryId), "Data Listelendi"); // Core paketinde bulunan successDataResult sinifi ile birlikte karsi tarafa verinin listendigini soyluyoruz.
	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameOrCategory_CategoryId(productName,categoryId), "Data Listelendi");
	}

	@Override
	public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByCategory_CategoryIdIn(categories), "Data Listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameContains(productName), "Data Listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByProductNameStartsWith(productName), "Data Listelendi");
	}

	@Override
	public DataResult<List<Product>> getByNameAndCategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>(this.productDao.getByNameAndCategory_CategoryId(productName,categoryId), "Data Listelendi");
	}

	@Override
	public DataResult<List<ProductWithCategoryDto>> getByProductWithCategoryDetails() {
		return new SuccessDataResult<List<ProductWithCategoryDto>>(this.productDao.getByProductWithCategoryDetails(), "Data Listelendi");
	}
	

}
