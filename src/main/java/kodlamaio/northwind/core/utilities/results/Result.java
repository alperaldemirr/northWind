package kodlamaio.northwind.core.utilities.results;

// Swagger destegi icin Result paketi. (Gelistirici verilerin, varitabanindan dogru bir sekilde geldigine emin olmak icin Swagger ile denemeler yapar. Bu paketde kullaniciya cevap veririz.) 
public class Result {
	
	private boolean success;
	private String message;
 
	public Result(boolean success) {
		this.success = success;
	}
 
	public Result(boolean success, String message) {
		this(success);
		this.message = message;
	}
	
	public boolean isSuccess() {
		return this.success;
	}
	
	public String getMessage() {
		return this.message;
	}
 
}
