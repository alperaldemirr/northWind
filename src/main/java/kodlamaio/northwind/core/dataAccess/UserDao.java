package kodlamaio.northwind.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.northwind.core.entities.User;


// Olusturdugumuz userDao sinifinin baska projelerde de kullanilabilmesi icin core paketine ekliyoruz.

public interface UserDao extends JpaRepository<User, Integer>{

	User findByEmail(String email);
	
}
