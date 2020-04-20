package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import repositories.ProductRepository;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	/**
	 * DEFAULT - read committed
	 * READ_UNCOMMITTED
	 * READ_COMMITTED
	 * REPEATABLE_READ
	 * SERIALIZABLE
	 *
	 * Problems:
	 * -dirty reads
	 * -repeatable reads
	 * -phantom reads
	 *
	 * READ_UNCOMMITTED:
	 * - Dirty Reads
	 * T1 -------10---------20---------------> 20
	 * T2 ------------20-----------R---------> 10
	 *
	 * READ_COMMITTED:
	 * - Repeatable Reads
	 * T1 -------10---------------20---------> 20
	 * T2 ------------20-----C---------------> 10
	 *
	 *
	 * REPEATABLE_READ:
	 * - Phantom Reads
	 * T1 -----s100-----------110------------> 20
	 * T2 -------------a10---C---------------> 10
	 * */
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void addTenProducts(){
		IntStream.rangeClosed(1,10)
				.forEach(i->productRepository.addProduct("produsul"+i,(double)new Random().nextInt(10)));
	}
}
