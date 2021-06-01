package kodlamaio.northwind.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.FieldError;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;

@RestController
@RequestMapping("/api/users/")
public class UsersController {

	private UserService userService;

	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	
	// ResponseEntity, kullanici eklerken alinan bir hatayi, result olarak dondurmemezi saglar. 
	// Kullanicidan alinan veriyi @RequestBody araciligiyla okunur.
	// ResponseEntity.ok, Http200 ile es degerdir. Islem gerceklesti anlami tasir.
	// ResponseEntity icin kullanilan "?", islem sonucuna gore otomatik olarak dondur anlamini tasir.
	// @Valid, methodu dogrulamadan gecirmeye yarar.
	
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody User user) {
		return ResponseEntity.ok(this.userService.add(user));
	}
	
	
	
	// Butun siniflarin temelidir object. Projede olusabilecek butun hatalari yakalayabilmek icin object kullanilir.		// MethodArgumentNotValidException, sistemimizde olusturdugumuz nesneler icin yazmis oldugumuz spring validation methodlarini kontrol ederek, olusacak olan hatalarda, bu kod blogunu devreye sokarak bize hatayi anlatir.
	// MethodArgumentNotValidException sonundaki ".class", java dilinde tip olarak adlandirilir.
	// Map, hata aciklamasinda kullanacagimiz birden cok alani tanitmamiz icin imkan saglar. Ilk string, user sinifimizdaki email alani icin, ikinci string ise yazacak olan hata mesaji.
	// ResponseStatus, methodumuzun otomatik olarak bad request(kotu istek) donsun.(500 hatasi)
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		
		// Bu kisimda hatalarimizi tutmasi icin sozluk olusturuyoruz.
		Map<String, String> validationErrors = new HashMap<String, String>();
		
		// Alanlarla ilgili olusan tum hatalari okur. Liste doner.
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Dogrulama Hatalari.");
		return errors;
		
	}
	
}
